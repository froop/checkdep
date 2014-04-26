package checkdep.parse;

import checkdep.util.ValueBase;

/**
 * 除外するパッケージ.
 */
public class ExcludePackage extends ValueBase {
  private final String raw;

  public ExcludePackage(String raw) {
    this.raw = raw;
  }

  public String getRaw() {
    return raw;
  }
}
