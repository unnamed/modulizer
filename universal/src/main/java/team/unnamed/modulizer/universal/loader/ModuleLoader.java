package team.unnamed.modulizer.universal.loader;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.internal.repository.ModuleFormat;

import java.util.Optional;

/**
 * The loader of the corresponding module.
 *
 * This class only have the responsibility of loading the
 * correct {@linkplain SimpleModule} to the provided identifier.
 *
 * @param <E> The type of the enum identifier.
 */
public interface ModuleLoader<E extends Enum<E>> {

    /**
     * This method is used to load the corresponding module.
     *
     * NOTE: The method will throw an exception if any error has occurred.
     *       while getting the corresponding module, for example:
     *       - If the class is not founded.
     *       - If the provided class is not assignable from {@linkplain SimpleModule}.
     *       - If the class can't be initialized.
     *
     * @param binder        The corresponding binder to configure the gotten {@linkplain SimpleModule}.
     * @param modulePath    The path format of the module.
     * @param moduleFormat  The {@linkplain ModuleFormat} configured.
     * @param className     The class name of the implemented {@linkplain SimpleModule}.
     * @param packageName   The package name of the implemented {@linkplain SimpleModule}.
     */
    void loadCurrentModule(ModuleBinder<E> binder,
                           String modulePath,
                           ModuleFormat moduleFormat,
                           String className,
                           String packageName);

    /**
     * This method gets the loaded module.
     *
     * @return  An unmodifiable instance of {@linkplain Optional}
     *          that contains an nullable instance of the {@linkplain SimpleModule}
     *          loaded.
     */
    Optional<SimpleModule<E>> getCurrentModule();

}