package team.unnamed.modulizer.universal.provider;

import team.unnamed.modulizer.universal.internal.Key;
import team.unnamed.modulizer.universal.type.TypeReference;

public interface ModuleProvider<T, E extends Enum<E>> {

    TypeReference<T> getAbstraction();

    Key<T, E> getKey(String implementationIdentifier, Enum<E> enumType);

    T getInstance(E enumType, String implementationIdentifier, String constructorIdentifier, Object... values);

    void registerVersion(Key<T, E> key);

    default Class<? extends T> getImplementation(String implementationIdentifier, E enumType) {
        return getKey(implementationIdentifier, enumType).getImplementation();
    }

}