package team.unnamed.uversions.internal;

import team.unnamed.uversions.MinecraftVersion;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Key<T> {

    private final Class<? extends T> implementation;
    private final Map<String, Constructor<T>> constructors;

    private MinecraftVersion version;

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

    public MinecraftVersion getVersion() {
        return version;
    }

    void setVersion(MinecraftVersion version) {
        this.version = version;
    }

    public Optional<Constructor<T>> getConstructor(String identifier) {
        return Optional.ofNullable(constructors.get(identifier));
    }

}