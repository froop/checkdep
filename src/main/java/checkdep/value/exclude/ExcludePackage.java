package checkdep.value.exclude;

import lombok.NonNull;
import lombok.Value;

/**
 * 除外するパッケージ.
 */
@Value
public class ExcludePackage {

  @NonNull
  private final String value;
}
