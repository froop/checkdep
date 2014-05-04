package checkdep.value.exclude;

import checkdep.util.StringValueBase;

/**
 * 除外するパッケージ.
 */
public class ExcludePackage extends StringValueBase {

  public static ExcludePackage of(String value) {
    return new ExcludePackage(value);
  }

  private ExcludePackage(String value) {
    super(value);
  }
}
