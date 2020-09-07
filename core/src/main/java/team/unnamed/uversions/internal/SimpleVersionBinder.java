package team.unnamed.uversions.internal;

import team.unnamed.uversions.SimpleVersionProvider;
import team.unnamed.uversions.VersionProvider;
import team.unnamed.uversions.bind.InternalVersionBinder;
import team.unnamed.uversions.bind.VersionBinderBuilder;
import team.unnamed.uversions.type.TypeReference;

import java.util.HashMap;
import java.util.Map;

public class SimpleVersionBinder implements InternalVersionBinder {

    private final Map<TypeReference<?>, VersionProvider<?>> versions;

    public SimpleVersionBinder() {
        versions = new HashMap<>();
    }

    @Override
    public <T> VersionBinderBuilder.Linkable<T> bind(TypeReference<T> abstractionType) {
        return new SimpleVersionBinderBuilder<>(this, abstractionType);
    }

    @Override
    public <T> void set(TypeReference<T> abstractionType, Key<T> key) {
        VersionProvider<T> versionProvider = new SimpleVersionProvider<>(abstractionType);

        versionProvider.registerVersion(key);
        versions.putIfAbsent(abstractionType, versionProvider);
    }

    @Override
    public <T> VersionProvider<T> getProvider(TypeReference<T> abstractType) {
        return (VersionProvider<T>) versions.get(abstractType);
    }

}