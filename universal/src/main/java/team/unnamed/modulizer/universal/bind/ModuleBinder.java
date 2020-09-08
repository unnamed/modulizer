package team.unnamed.modulizer.universal.bind;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.type.TypeReference;

public interface ModuleBinder<E extends Enum<E>> {

    <T> ModuleBinderBuilder.Linkable<T, E> bind(TypeReference<T> abstractionType);

    default <T> ModuleBinderBuilder.Linkable<T, E> bind(Class<T> abstractClass) {
        return bind(TypeReference.of(abstractClass));
    }

    default void installModule(SimpleModule<E> module) {
        module.configure(this);
    }

}