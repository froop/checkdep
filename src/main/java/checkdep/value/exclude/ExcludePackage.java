package checkdep.value.exclude;

import checkdep.util.ValueBase;

/**
 * 除外するパッケージ.
 */
public class ExcludePackage extends ValueBase {
  private final String value;

  public ExcludePackage(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
