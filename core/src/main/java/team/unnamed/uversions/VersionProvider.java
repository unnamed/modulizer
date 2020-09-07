package team.unnamed.uversions;

import team.unnamed.uversions.bind.VersionBinderBuilder;
import team.unnamed.uversions.internal.Key;
import team.unnamed.uversions.type.TypeReference;

public interface VersionProvider<T> {

    TypeReference<T> getAbstraction();

    Key<T> getKey(MinecraftVersion version);

    T getInstance(MinecraftVersion version, String constructorIdentifier, Object... values);

    void registerVersion(Key<T> key);

    default Class<? extends T> getImplementation(MinecraftVersion version) {
        return getKey(version).getImplementation();
    }

    default T getInstance(MinecraftVersion version) {
        return getInstance(version, VersionBinderBuilder.DEFAULT_NAME);
    }

}