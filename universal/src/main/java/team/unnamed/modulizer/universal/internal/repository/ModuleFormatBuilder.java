package team.unnamed.modulizer.universal.internal.repository;

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
        this.packagePlaceholder = packagePlaceholder;

        return this;
    }

    public ModuleFormatBuilder setClassNamePlaceholder(String classNamePlaceholder) {
        this.classNamePlaceholder = classNamePlaceholder;

        return this;
    }

    public ModuleFormatBuilder setIdentifierPlaceholder(String identifierPlaceholder) {
        this.identifierPlaceholder = identifierPlaceholder;

        return this;
    }

    public ModuleFormat build() {
        return new SimpleModuleFormat(packagePlaceholder, classNamePlaceholder, identifierPlaceholder);
    }

}