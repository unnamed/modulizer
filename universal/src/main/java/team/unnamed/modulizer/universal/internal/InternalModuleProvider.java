package team.unnamed.modulizer.universal.internal;

import team.unnamed.modulizer.universal.provider.ModuleProvider;
import team.unnamed.modulizer.universal.type.TypeReference;

public interface InternalModuleProvider<E extends Enum<E>> {

    <T> ModuleProvider<T, E> getProvider(TypeReference<T> abstractType);

    default <T> ModuleProvider<T, E> getProvider(Class<T> abstractClass) {
        return getProvider(TypeReference.of(abstractClass));
    }

}