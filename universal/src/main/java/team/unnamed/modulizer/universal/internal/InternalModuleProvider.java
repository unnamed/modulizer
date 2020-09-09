package team.unnamed.modulizer.universal.internal;

import team.unnamed.modulizer.universal.provider.ModuleProvider;
import team.unnamed.modulizer.universal.type.TypeReference;

/**
 * This is the basic model that's responsible for providing
 * the {@linkplain ModuleProvider}'s.
 *
 * @param <E> The type of the enum identifier.
 */
public interface InternalModuleProvider<E extends Enum<E>> {

    /**
     * Get's a provider corresponding by the {@linkplain TypeReference} of
     * the abstract class provided in the parameter.
     *
     * @param abstractType  The {@linkplain TypeReference} of the abstraction.
     * @param <T>           The type of the abstraction.
     * @return A nullable instance of the corresponding {@linkplain ModuleProvider}.
     */
    <T> ModuleProvider<T, E> getProvider(TypeReference<T> abstractType);

    /**
     * Get's a provider corresponding by the {@linkplain Class} of
     * the abstract class provided in the parameter.
     *
     * @param abstractClass  The {@linkplain Class} of the abstraction.
     * @param <T>           The type of the abstraction.
     * @return A nullable instance of the corresponding {@linkplain ModuleProvider}.
     */
    default <T> ModuleProvider<T, E> getProvider(Class<T> abstractClass) {
        return getProvider(TypeReference.of(abstractClass));
    }

}