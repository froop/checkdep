package checkdep.value.exclude;

import checkdep.util.StringValueBase;
import lombok.NonNull;

/**
 * 除外するパッケージ.
 */
public final class ExcludePackage extends StringValueBase {

  public static ExcludePackage of(@NonNull String value) {
    return new ExcludePackage(value);
  }

  private ExcludePackage(String value) {
    super(value);
  }
}
