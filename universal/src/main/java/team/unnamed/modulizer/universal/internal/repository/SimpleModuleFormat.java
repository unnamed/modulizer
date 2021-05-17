package team.unnamed.modulizer.universal.internal.repository;

public class SimpleModuleFormat implements ModuleFormat {

  private final String packagePlaceholder;
  private final String classNamePlaceholder;
  private final String identifierPlaceholder;

  SimpleModuleFormat(String packagePlaceholder, String classNamePlaceholder, String identifierPlaceholder) {
    this.packagePlaceholder = packagePlaceholder;
    this.classNamePlaceholder = classNamePlaceholder;
    this.identifierPlaceholder = identifierPlaceholder;
  }

  @Override
  public String getPackagePlaceholder() {
    return packagePlaceholder;
  }

  @Override
  public String getClassNamePlaceholder() {
    return classNamePlaceholder;
  }

  @Override
  public String getIdentifierPlaceholder() {
    return identifierPlaceholder;
  }

}