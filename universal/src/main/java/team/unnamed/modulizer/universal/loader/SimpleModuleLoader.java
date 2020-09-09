package team.unnamed.modulizer.universal.loader;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.exception.ModuleCastException;
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
        className = className.replace(moduleFormat.getIdentifierPlaceholder(), currentType.name());

        try {
            Class<?> clazz = Class.forName(
                    modulePath
                            .replace(moduleFormat.getIdentifierPlaceholder(), currentType.name())
                            .replace(moduleFormat.getClassNamePlaceholder(), className)
                            .replace(moduleFormat.getPackagePlaceholder(), packageName)
            );

            if (clazz == null) {
                throw new IllegalArgumentException("Couldn't find any module for " + currentType.name() + ".");
            }

            if (!clazz.isAssignableFrom(SimpleModule.class)) {
                throw new ModuleCastException("Class " + clazz.getName() + " isn't assignable from SimpleModule.");
            }

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