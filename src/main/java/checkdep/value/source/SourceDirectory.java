package checkdep.value.source;

import checkdep.util.StringValueBase;

/**
 * 元ディレクトリ.
 * 解析するclasspath.
 */
public class SourceDirectory extends StringValueBase {

  public static SourceDirectory of(String value) {
    return new SourceDirectory(value);
  }

  private SourceDirectory(String value) {
    super(value);
  }
}
