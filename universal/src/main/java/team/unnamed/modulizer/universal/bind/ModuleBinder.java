package team.unnamed.modulizer.universal.bind;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.type.TypeReference;

public interface ModuleBinder {

    <T, E extends Enum<E>> ModuleBinderBuilder.Linkable<T, E> bind(TypeReference<T> abstractionType, Class<E> enumType);

    default <T, E extends Enum<E>> ModuleBinderBuilder.Linkable<T, E> bind(Class<T> abstractClass, Class<E> enumType) {
        return bind(TypeReference.of(abstractClass), enumType);
    }

    default void installModule(SimpleModule module) {
        module.configure(this);
    }

}