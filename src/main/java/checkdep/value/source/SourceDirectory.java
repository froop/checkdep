package checkdep.value.source;

import lombok.NonNull;
import lombok.Value;

/**
 * 元ディレクトリ.
 * 解析するclasspath.
 */
@Value
public class SourceDirectory {

  @NonNull
  private final String value;
}
