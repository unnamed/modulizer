package team.unnamed.modulizer.universal.internal.repository;

public interface ModuleFormat {

    String getPackagePlaceholder();

    String getClassNamePlaceholder();

    String getIdentifierPlaceholder();

    static ModuleFormatBuilder build() {
        return new ModuleFormatBuilder();
    }

}