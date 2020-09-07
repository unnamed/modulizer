package team.unnamed.uversions.bind;

import team.unnamed.uversions.MinecraftVersion;

public interface VersionBinderBuilder {

    String DEFAULT_NAME = "!!_DEFAULT_CONSTRUCTOR_!!";

    VersionBinderBuilder withConstructor(String identifier, Class<?>... extraParameters);

    VersionBinderBuilder withDefaultConstructor();

    void withVersion(MinecraftVersion version);

    interface Linkable<T> {

        VersionBinderBuilder to(Class<? extends T> implementation);

    }

    interface Partial<T> extends VersionBinderBuilder, Linkable<T> { }

}