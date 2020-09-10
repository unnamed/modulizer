package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.util.ValidateUtil;

public final class ModuleFormatBuilder {

    private String packagePlaceholder;
    private String classNamePlaceholder;
    private String identifierPlaceholder;

    ModuleFormatBuilder() {
        packagePlaceholder = "%package%";
        classNamePlaceholder = "%class_name%";
        identifierPlaceholder = "%identifier%";
    }

    public ModuleFormatBuilder setPackagePlaceholder(String packagePlaceholder) {
        this.packagePlaceholder = ValidateUtil.checkNotNull(packagePlaceholder, "The package placeholder can't be null");

        return this;
    }

    public ModuleFormatBuilder setClassNamePlaceholder(String classNamePlaceholder) {
        this.classNamePlaceholder = ValidateUtil.checkNotNull(classNamePlaceholder, "The class name placeholder can't be null");

        return this;
    }

    public ModuleFormatBuilder setIdentifierPlaceholder(String identifierPlaceholder) {
        this.identifierPlaceholder = ValidateUtil.checkNotNull(identifierPlaceholder, "The identifier placeholder can't be null");

        return this;
    }

    public ModuleFormat build() {
        return new SimpleModuleFormat(
                packagePlaceholder,
                classNamePlaceholder,
                identifierPlaceholder
        );
    }

}