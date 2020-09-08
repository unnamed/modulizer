package team.unnamed.modulizer.universal.internal;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Key<T, E extends Enum<E>> {

    private final Class<? extends T> implementation;
    private final Map<String, Constructor<T>> constructors;

    private String identifier;
    private Enum<E> enumType;

    public Key(Class<? extends T> implementation) {
        this.implementation = implementation;

        constructors = new HashMap<>();
    }

    public void addConstructor(String identifier, Constructor<T> constructor) {
        constructors.put(identifier, constructor);
    }

    public Class<? extends T> getImplementation() {
        return implementation;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Enum<E> getEnumType() {
        return enumType;
    }

    void setEnumType(Enum<E> enumType) {
        this.enumType = enumType;
    }

    public Optional<Constructor<T>> getConstructor(String identifier) {
        return Optional.ofNullable(constructors.get(identifier));
    }

}