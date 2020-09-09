package team.unnamed.modulizer.universal.util;

public final class Validate {

    private Validate() {
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the provided object is null,
     * if it's null, the method results in a
     * {@link NullPointerException} being thrown.
     *
     * @param object    The checked object
     * @param <T>       The type of the object
     * @param message   The message to send at exception.
     * @return The object, if not null
     */
    public static <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException("[unnamed-modulizer] " + message);
        }

        return object;
    }

    public static void checkState(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException("[unnamed-modulizer] " + message);
        }
    }

}