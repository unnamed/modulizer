package team.unnamed.uversions.internal;

import team.unnamed.uversions.MinecraftVersion;
import team.unnamed.uversions.bind.InternalVersionBinder;
import team.unnamed.uversions.bind.VersionBinderBuilder;
import team.unnamed.uversions.type.TypeReference;

import java.lang.reflect.Constructor;

public class SimpleVersionBinderBuilder<T> implements VersionBinderBuilder.Partial<T> {

    private final InternalVersionBinder binder;

    private final TypeReference<T> abstraction;
    private Key<T> key;

    public SimpleVersionBinderBuilder(InternalVersionBinder binder, TypeReference<T> abstraction) {
        this.binder = binder;
        this.abstraction = abstraction;
    }

    @Override
    public VersionBinderBuilder to(Class<? extends T> implementation) {
        key = new Key<>(implementation);

        return this;
    }

    @Override
    public void withVersion(MinecraftVersion version) {
        key.setVersion(version);

        binder.set(abstraction, key);
    }

    @Override
    public VersionBinderBuilder withConstructor(String identifier, Class<?>... extraParameters) {
        try {
            key.addConstructor(identifier, (Constructor<T>) key.getImplementation().getConstructor(extraParameters));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return this;
    }

    @Override
    public VersionBinderBuilder withDefaultConstructor() {
        try {
            key.addConstructor(DEFAULT_NAME, (Constructor<T>) key.getImplementation().getConstructor());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return this;
    }

}