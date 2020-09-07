package team.unnamed.uversions;

import team.unnamed.uversions.internal.Key;
import team.unnamed.uversions.type.TypeReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleVersionProvider<T> implements VersionProvider<T> {

    private final TypeReference<T> abstractionType;
    private final Map<MinecraftVersion, Key<T>> implementations;

    public SimpleVersionProvider(TypeReference<T> abstractionType) {
        this.abstractionType = abstractionType;

        implementations = new LinkedHashMap<>();
    }

    @Override
    public TypeReference<T> getAbstraction() {
        return abstractionType;
    }

    @Override
    public Key<T> getKey(MinecraftVersion version) {
        Key<T> implementation = implementations.get(version);

        if (implementation == null) {
            throw new IllegalArgumentException("Your server version isn't supported.");
        }

        return implementation;
    }

    @Override
    public T getInstance(MinecraftVersion version, String constructorIdentifier, Object... values) {
        Key<T> implementation = getKey(version);

        try {
            Optional<Constructor<T>> constructorOptional = implementation.getConstructor(constructorIdentifier);

            if (!constructorOptional.isPresent()) {
                throw new IllegalArgumentException(
                        "An error has occurred while getting the constructor " + constructorIdentifier + " of " + abstractionType.getRawType()
                );
            }

            return constructorOptional.get().newInstance(values);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("An error has occurred while initializing an instance of " + abstractionType.getRawType());
        }
    }

    @Override
    public void registerVersion(Key<T> key) {
        implementations.putIfAbsent(key.getVersion(), key);
    }

}