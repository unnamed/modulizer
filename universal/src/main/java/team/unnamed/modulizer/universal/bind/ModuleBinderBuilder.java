package team.unnamed.modulizer.universal.bind;

public interface ModuleBinderBuilder<E extends Enum<E>> {

    String DEFAULT_NAME = "!!_DEFAULT_!!";

    ModuleBinderBuilder<E> withConstructor(String identifier, Class<?>... extraParameters);

    ModuleBinderBuilder<E> withDefaultConstructor();

    Qualified<E> withIdentifier(String identifier);

    Qualified<E> defaultIdentifier();

    interface Linkable<T, E extends Enum<E>> {

        ModuleBinderBuilder<E> to(Class<? extends T> implementation);

    }

    interface Qualified<E extends Enum<E>> {

        void withType(E enumType);

    }

    interface Partial<T, E extends Enum<E>> extends ModuleBinderBuilder<E>, Linkable<T, E>, Qualified<E> { }

}