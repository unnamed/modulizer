package team.unnamed.modulizer.universal.bind;

import team.unnamed.modulizer.universal.internal.Key;
import team.unnamed.modulizer.universal.provider.ModuleProvider;
import team.unnamed.modulizer.universal.type.TypeReference;

public interface InternalModuleBinder extends ModuleBinder {

    <T, E extends Enum<E>> void set(TypeReference<T> abstractionType, Key<T, E> key);

    <T, E extends Enum<E>> ModuleProvider<T, E> getProvider(TypeReference<T> abstractType,
                                                            String implementationIdentifier,
                                                            Enum<E> enumType);

    default <T, E extends Enum<E>> ModuleProvider<T, E> getProvider(Class<T> abstractClass,
                                                                    String implementationIdentifier,
                                                                    Enum<E> enumType) {
        return getProvider(TypeReference.of(abstractClass), implementationIdentifier, enumType);
    }

    default <T, E extends Enum<E>> ModuleProvider<T, E> getProvider(TypeReference<T> abstractType,
                                                                    Enum<E> enumType) {
        return getProvider(abstractType, ModuleBinderBuilder.DEFAULT_NAME, enumType);
    }

    default <T, E extends Enum<E>> ModuleProvider<T, E> getProvider(Class<T> abstractClass,
                                                                    Enum<E> enumType) {
        return getProvider(abstractClass, ModuleBinderBuilder.DEFAULT_NAME, enumType);
    }

}