package team.unnamed.modulizer.universal.internal;

import team.unnamed.modulizer.universal.bind.ModuleBinderBuilder;
import team.unnamed.modulizer.universal.type.TypeReference;

import java.lang.reflect.Constructor;

public class SimpleModuleBinderBuilder<T, E extends Enum<E>> implements ModuleBinderBuilder.Partial<T, E> {

    private final InternalModuleBinder<E> binder;

    private final TypeReference<T> abstractionType;
    private Key<T, E> key;

    public SimpleModuleBinderBuilder(InternalModuleBinder<E> binder, TypeReference<T> abstractionType) {
        this.binder = binder;
        this.abstractionType = abstractionType;
    }

    @Override
    public ModuleBinderBuilder<E> to(Class<? extends T> implementation) {
        key = new Key<>(implementation);

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ModuleBinderBuilder<E> withConstructor(String identifier, Class<?>... extraParameters) {
        try {
            key.addConstructor(identifier, (Constructor<T>) key.getImplementation().getConstructor(extraParameters));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ModuleBinderBuilder<E> withDefaultConstructor() {
        try {
            key.addConstructor(DEFAULT_NAME, (Constructor<T>) key.getImplementation().getConstructor());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return this;
    }

    @Override
    public Qualified<E> withIdentifier(String identifier) {
        key.setIdentifier(identifier);

        return this;
    }

    @Override
    public Qualified<E> defaultIdentifier() {
        key.setIdentifier(DEFAULT_NAME);

        return this;
    }

    @Override
    public void withType(E enumType) {
        key.setEnumType(enumType);

        binder.set(abstractionType, key);
    }

}