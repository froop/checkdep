package checkdep.value.source;

import checkdep.util.StringValueBase;
import lombok.NonNull;

/**
 * 元ディレクトリ.
 * 解析するclasspath.
 */
public final class SourceDirectory extends StringValueBase {

  public static SourceDirectory of(@NonNull String value) {
    return new SourceDirectory(value);
  }

  private SourceDirectory(String value) {
    super(value);
  }
}
