package team.unnamed.modulizer.universal.internal;

import team.unnamed.modulizer.universal.bind.InternalModuleBinder;
import team.unnamed.modulizer.universal.bind.ModuleBinderBuilder;
import team.unnamed.modulizer.universal.provider.ModuleProvider;
import team.unnamed.modulizer.universal.provider.SimpleModuleProvider;
import team.unnamed.modulizer.universal.type.TypeReference;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleModuleBinder implements InternalModuleBinder {

    private final Map<TypeReference<?>, ModuleProvider<?, ? extends Enum<?>>> providers;

    public SimpleModuleBinder() {
        providers = new LinkedHashMap<>();
    }

    @Override
    public <T, E extends Enum<E>> void set(TypeReference<T> abstractionType,
                                           Key<T, E> key) {

        ModuleProvider<T, E> versionProvider = new SimpleModuleProvider<>(abstractionType);

        versionProvider.registerVersion(key);
        providers.putIfAbsent(abstractionType, versionProvider);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T, E extends Enum<E>> ModuleProvider<T, E> getProvider(TypeReference<T> abstractType,
                                                                   String implementationIdentifier,
                                                                   Enum<E> enumType) {

        ModuleProvider<T, E> moduleProvider = (ModuleProvider<T, E>) providers.get(abstractType);

        if (moduleProvider == null) {
            throw new IllegalArgumentException("Not module founded to " + abstractType + " with type " + enumType + " and the identifier " + implementationIdentifier);
        }

        Key<T, E> key = moduleProvider.getKey(implementationIdentifier, enumType);

        if (key.getIdentifier().equals(implementationIdentifier)) {
            return moduleProvider;
        }

        return null;
    }

    @Override
    public <T, E extends Enum<E>> ModuleBinderBuilder.Linkable<T, E> bind(TypeReference<T> abstractionType,
                                                                          Class<E> enumType) {

        return new SimpleModuleBinderBuilder<>(this, abstractionType);
    }

}