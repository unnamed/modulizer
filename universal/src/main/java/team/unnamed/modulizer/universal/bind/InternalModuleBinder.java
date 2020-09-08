package team.unnamed.modulizer.universal.bind;

import team.unnamed.modulizer.universal.internal.Key;
import team.unnamed.modulizer.universal.provider.ModuleProvider;
import team.unnamed.modulizer.universal.type.TypeReference;

public interface InternalModuleBinder<E extends Enum<E>> extends ModuleBinder<E> {

    <T> void set(TypeReference<T> abstractionType, Key<T, E> key);

    <T> ModuleProvider<T, E> getProvider(TypeReference<T> abstractType);

    default <T> ModuleProvider<T, E> getProvider(Class<T> abstractClass) {
        return getProvider(TypeReference.of(abstractClass));
    }

}