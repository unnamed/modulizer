package team.unnamed.modulizer.universal.provider;


import team.unnamed.modulizer.universal.MinecraftVersion;
import team.unnamed.modulizer.universal.bind.ModuleBinderBuilder;
import team.unnamed.modulizer.universal.internal.Key;
import team.unnamed.modulizer.universal.type.TypeReference;
import team.unnamed.modulizer.universal.util.ValidateUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleModuleProvider<T> implements ModuleProvider<T> {

  private final TypeReference<T> abstractionType;
  private final Map<MinecraftVersion, Map<String, Key<T>>> implementations;

  public SimpleModuleProvider(TypeReference<T> abstractionType) {
    this.abstractionType = abstractionType;

    implementations = new LinkedHashMap<>();
  }

  @Override
  public TypeReference<T> getAbstraction() {
    return abstractionType;
  }

  @Override
  public Key<T> getKey(String implementationIdentifier, MinecraftVersion version) {
    Map<String, Key<T>> implementation = implementations.get(ValidateUtil.checkNotNull(version, "The enum identifier can't be null"));

    if (implementation == null) {
      throw new IllegalArgumentException(
              "An error has occurred while getting key's for "
                      + abstractionType + " with the identifier "
                      + implementationIdentifier + " with type "
                      + version + "."
      );
    }

    if (implementationIdentifier == null) {
      return implementation.get(ModuleBinderBuilder.DEFAULT_NAME);
    }

    if (!implementation.containsKey(implementationIdentifier)) {
      throw new IllegalArgumentException(
              "An error has occurred while searching a key for "
                      + abstractionType + " with the identifier "
                      + implementationIdentifier + "."
      );
    }

    return implementation.get(implementationIdentifier);
  }

  @Override
  public T getInstance(MinecraftVersion version,
                       String implementationIdentifier,
                       String constructorIdentifier,
                       Object... values) {
    Key<T> implementation = getKey(
            implementationIdentifier,
            ValidateUtil.checkNotNull(
                    version,
                    "The enum identifier can't be null"
            )
    );

    if (constructorIdentifier == null) {
      constructorIdentifier = ModuleBinderBuilder.DEFAULT_NAME;
    }

    try {
      Optional<Constructor<T>> constructorOptional = implementation
              .getConstructor(constructorIdentifier);

      if (!constructorOptional.isPresent()) {
        throw new IllegalArgumentException(
                "An error has occurred while getting the constructor "
                        + constructorIdentifier + " of " + abstractionType.getRawType() + "."
        );
      }

      return constructorOptional.get().newInstance(values);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(
              "An error has occurred while initializing an instance of "
                      + abstractionType.getRawType() + ".", e.getCause()
      );
    }
  }

  @Override
  public void registerVersion(Key<T> key) {
    Map<String, Key<T>> implementation = implementations
            .getOrDefault(key.getVersion(), new LinkedHashMap<>());

    implementation.putIfAbsent(
            key.getIdentifier(),
            ValidateUtil.checkNotNull(
                    key,
                    "The key to register can't be null"
            )
    );

    implementations.put(key.getVersion(), implementation);
  }

}