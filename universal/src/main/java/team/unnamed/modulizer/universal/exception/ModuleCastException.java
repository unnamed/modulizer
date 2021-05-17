package team.unnamed.modulizer.universal.exception;

public class ModuleCastException extends IllegalArgumentException {

  public ModuleCastException(String s) {
    super(s);
  }

  public ModuleCastException(String message, Throwable cause) {
    super(message, cause);
  }

}