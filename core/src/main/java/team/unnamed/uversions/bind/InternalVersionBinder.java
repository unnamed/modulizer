package team.unnamed.uversions.bind;

import team.unnamed.uversions.VersionProvider;
import team.unnamed.uversions.internal.Key;
import team.unnamed.uversions.type.TypeReference;

public interface InternalVersionBinder extends VersionBinder {

    <T> void set(TypeReference<T> abstractionType, Key<T> key);

    <T> VersionProvider<T> getProvider(TypeReference<T> abstractType);

    default <T> VersionProvider<T> getProvider(Class<T> abstractClass) {
        return getProvider(TypeReference.of(abstractClass));
    }

}