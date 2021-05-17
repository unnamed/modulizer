package team.unnamed.modulizer.universal.internal;

import team.unnamed.modulizer.universal.MinecraftVersion;
import team.unnamed.modulizer.universal.bind.ModuleBinderBuilder;
import team.unnamed.modulizer.universal.type.TypeReference;

import java.lang.reflect.Constructor;

public class SimpleModuleBinderBuilder<T> implements ModuleBinderBuilder.Partial<T> {

  private final InternalModuleBinder binder;

  private final TypeReference<T> abstractionType;
  private Key<T> key;

  public SimpleModuleBinderBuilder(InternalModuleBinder binder,
                                   TypeReference<T> abstractionType) {
    this.binder = binder;
    this.abstractionType = abstractionType;
  }

  @Override
  public ModuleBinderBuilder to(Class<? extends T> implementation) {
    key = new Key<>(implementation);

    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public ModuleBinderBuilder withConstructor(String identifier,
                                             Class<?>... extraParameters) {
    try {
      key.addConstructor(
              identifier,
              (Constructor<T>) key.getImplementation().getConstructor(extraParameters)
      );
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    return this;
  }

  @Override
  public Qualified withIdentifier(String identifier) {
    key.setIdentifier(identifier);

    return this;
  }

  @Override
  public void withVersion(MinecraftVersion version) {
    key.setVersion(version);

    binder.set(abstractionType, key);
  }

}