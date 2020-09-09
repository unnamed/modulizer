package team.unnamed.modulizer.universal.internal;

import team.unnamed.modulizer.universal.bind.ModuleBinderBuilder;
import team.unnamed.modulizer.universal.exception.ModuleLoadException;
import team.unnamed.modulizer.universal.provider.ModuleProvider;
import team.unnamed.modulizer.universal.provider.SimpleModuleProvider;
import team.unnamed.modulizer.universal.type.TypeReference;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleModuleBinder<E extends Enum<E>> implements InternalModuleBinder<E> {

    private final Map<TypeReference<?>, ModuleProvider<?, ? extends Enum<?>>> providers;

    public SimpleModuleBinder() {
        providers = new LinkedHashMap<>();
    }

    @Override
    public <T> void set(TypeReference<T> abstractionType, Key<T, E> key) {
        ModuleProvider<T, E> versionProvider = new SimpleModuleProvider<>(abstractionType);

        versionProvider.registerVersion(key);
        providers.putIfAbsent(abstractionType, versionProvider);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> ModuleProvider<T, E> getProvider(TypeReference<T> abstractType) {
        ModuleProvider<T, E> moduleProvider = (ModuleProvider<T, E>) providers.get(abstractType);

        if (moduleProvider == null) {
            throw new ModuleLoadException("Couldn't find any module for " + abstractType + ".");
        }

        return moduleProvider;
    }

    @Override
    public <T> ModuleBinderBuilder.Linkable<T, E> bind(TypeReference<T> abstractionType) {
        return new SimpleModuleBinderBuilder<>(this, abstractionType);
    }

}