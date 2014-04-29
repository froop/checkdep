package checkdep.value.source;

import checkdep.util.ValueBase;

/**
 * 元ディレクトリ.
 * 解析するclasspath.
 */
public class SourceDirectory extends ValueBase {
  private final String value;

  public SourceDirectory(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
