package team.unnamed.uversions.bind;

import team.unnamed.uversions.VersionModule;
import team.unnamed.uversions.type.TypeReference;

public interface VersionBinder {

    <T> VersionBinderBuilder.Linkable<T> bind(TypeReference<T> abstractionType);

    default <T> VersionBinderBuilder.Linkable<T> bind(Class<T> abstraction) {
        return bind(TypeReference.of(abstraction));
    }

    default void installModule(VersionModule module) {
        module.configure(this);
    }

}