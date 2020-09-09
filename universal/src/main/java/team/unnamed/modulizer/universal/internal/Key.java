package team.unnamed.modulizer.universal.internal;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This is the principal model of the implementation properties. Will be used
 * while binding the abstraction to an implementation, this is the final
 * result of these binding.
 *
 * Here will be stored the properties configured in the binding process,
 * for example:
 *      - The identifier of the implementation.
 *      - The enum identifier of the implementation.
 *      - The constructors selected.
 *      - An unmodifiable {@linkplain Class} instance of the implementation.
 *
 * @param <T> The type of the abstraction.
 * @param <E> The type of the enum identifier.
 */
public final class Key<T, E extends Enum<E>> {

    private final Class<? extends T> implementation;
    private final Map<String, Constructor<T>> constructors;

    private String identifier;
    private Enum<E> enumType;

    public Key(Class<? extends T> implementation) {
        this.implementation = implementation;

        constructors = new HashMap<>();
    }

    /**
     * Adds an new constructor to the cache.
     *
     * NOTE: If the constructor isn't found, will throw an exception.
     *
     * @param identifier    The identifier to save the constructor.
     * @param constructor   The constructor to save.
     */
    public void addConstructor(String identifier, Constructor<T> constructor) {
        constructors.put(identifier, constructor);
    }

    /**
     * Gets the unmodifiable instance of the {@linkplain Class} implementation.
     *
     * @return A not null instance of the {@linkplain Class} implementation.
     */
    public Class<? extends T> getImplementation() {
        return implementation;
    }

    /**
     * Gets the identifier of the implementation.
     *
     * @return A not null identifier of the implementation.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of the implementation.
     *
     * @param identifier The new identifier.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return The enum identifier of the implementation.
     */
    public Enum<E> getEnumType() {
        return enumType;
    }

    /**
     * Sets the enum identifier of the implementation.
     *
     * @param enumType The new enum identifier.
     */
    void setEnumType(Enum<E> enumType) {
        this.enumType = enumType;
    }

    /**
     * Gets an constructor by the identifier provided.
     *
     * NOTE: If the constructor identifier is null, that will be
     *       the default identifier.
     *
     * @param identifier The identifier of the constructor.
     * @return An instance of {@linkplain Optional} with the nullable instance of
     *          the gotten constructor.
     */
    public Optional<Constructor<T>> getConstructor(String identifier) {
        return Optional.ofNullable(constructors.get(identifier));
    }

}