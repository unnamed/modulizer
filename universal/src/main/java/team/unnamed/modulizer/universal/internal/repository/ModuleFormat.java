package team.unnamed.modulizer.universal.internal.repository;

/**
 * This is a simple model of the placeholders that the modulePath will
 * be replaced.
 */
public interface ModuleFormat {

  /**
   * @return The selected placeholder to the package.
   */
  String getPackagePlaceholder();

  /**
   * @return The selected placeholder to the class name.
   */
  String getClassNamePlaceholder();

  /**
   * @return The selected placeholder to the identifier.
   */
  String getIdentifierPlaceholder();

  /**
   * Creates an instance of the builder, to modify the default
   * settings.
   * <p>
   * NOTE: The properties selected in the builder can't be null.
   *
   * @return An instance of the {@linkplain ModuleFormatBuilder}.
   */
  static ModuleFormatBuilder builder() {
    return new ModuleFormatBuilder();
  }

}