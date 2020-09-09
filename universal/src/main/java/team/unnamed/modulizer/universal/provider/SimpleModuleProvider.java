package team.unnamed.modulizer.universal.provider;

import team.unnamed.modulizer.universal.internal.Key;
import team.unnamed.modulizer.universal.type.TypeReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleModuleProvider<T, E extends Enum<E>> implements ModuleProvider<T, E> {

    private final TypeReference<T> abstractionType;
    private final Map<Enum<E>, Key<T, E>> implementations;

    public SimpleModuleProvider(TypeReference<T> abstractionType) {
        this.abstractionType = abstractionType;

        implementations = new LinkedHashMap<>();
    }

    @Override
    public TypeReference<T> getAbstraction() {
        return abstractionType;
    }

    @Override
    public Key<T, E> getKey(String implementationIdentifier, Enum<E> enumType) {
        Key<T, E> implementation = implementations.get(enumType);

        if (implementation == null) {
            throw new IllegalArgumentException(
                    "An error has occurred while getting a key for " + abstractionType + " with the identifier " + implementationIdentifier + " with type " + enumType + "."
            );
        }

        if (implementationIdentifier == null) {
            return implementation;
        }

        if (!implementation.getIdentifier().equals(implementationIdentifier)) {
            throw new IllegalArgumentException(
                    "An error has occurred while searching a key for "  + abstractionType + " with the identifier " + implementationIdentifier + "."
            );
        }

        return implementation;
    }

    @Override
    public T getInstance(E enumType, String implementationIdentifier, String constructorIdentifier, Object... values) {
        Key<T, E> implementation = getKey(implementationIdentifier, enumType);

        try {
            Optional<Constructor<T>> constructorOptional = implementation.getConstructor(constructorIdentifier);

            if (!constructorOptional.isPresent()) {
                throw new IllegalArgumentException(
                        "An error has occurred while getting the constructor " + constructorIdentifier + " of " + abstractionType.getRawType() + "."
                );
            }

            return constructorOptional.get().newInstance(values);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(
                    "An error has occurred while initializing an instance of " + abstractionType.getRawType() + ".", e
            );
        }
    }

    @Override
    public void registerVersion(Key<T, E> key) {
        implementations.putIfAbsent(key.getEnumType(), key);
    }

}