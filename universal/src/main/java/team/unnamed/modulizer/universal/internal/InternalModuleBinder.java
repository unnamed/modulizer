package team.unnamed.modulizer.universal.internal;

import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.type.TypeReference;

public interface InternalModuleBinder<E extends Enum<E>> extends ModuleBinder<E>, InternalModuleProvider<E> {

    <T> void set(TypeReference<T> abstractionType, Key<T, E> key);

}