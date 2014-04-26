package checkdep.parse;

import checkdep.util.ValueBase;

/**
 * 元ディレクトリ.
 * 解析するclasspath.
 */
public class SourceDirectory extends ValueBase {
  private final String raw;

  public SourceDirectory(String raw) {
    this.raw = raw;
  }

  public String getRaw() {
    return raw;
  }
}
