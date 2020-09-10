package team.unnamed.modulizer.universal.loader;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.exception.ModuleLoadException;
import team.unnamed.modulizer.universal.internal.repository.ModuleFormat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class SimpleModuleLoader<E extends Enum<E>> implements ModuleLoader<E> {

    private SimpleModule<E> currentModule;

    private final Enum<E> currentType;

    public SimpleModuleLoader(Enum<E> currentType) {
        this.currentType = currentType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadCurrentModule(ModuleBinder<E> binder, String modulePath, ModuleFormat moduleFormat, String className, String packageName) {
        String identifierPlaceholder = moduleFormat.getIdentifierPlaceholder();

        String replacedClassName = className.replace(identifierPlaceholder, currentType.name());
        String replacedPackageName = packageName.replace(identifierPlaceholder, currentType.name());

        try {
            Class<? extends SimpleModule<E>> clazz = (Class<? extends SimpleModule<E>>) Class.forName(
                    modulePath
                            .replace(moduleFormat.getClassNamePlaceholder(), replacedClassName)
                            .replace(moduleFormat.getPackagePlaceholder(), replacedPackageName)
            );

            Constructor<?> defaultConstructor = clazz.getConstructor();

            boolean accessible = defaultConstructor.isAccessible();

            defaultConstructor.setAccessible(true);

            SimpleModule<E> module = (SimpleModule<E>) defaultConstructor.newInstance();

            defaultConstructor.setAccessible(accessible);

            module.configure(binder);

            currentModule = module;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new ModuleLoadException("An error has occurred while getting the corresponding module to " + currentType.name(), e);
        }
    }

    @Override
    public Optional<SimpleModule<E>> getCurrentModule() {
        return Optional.ofNullable(currentModule);
    }

}