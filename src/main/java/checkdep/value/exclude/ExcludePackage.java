package checkdep.value.exclude;

import lombok.NonNull;
import lombok.Value;

/**
 * 除外するパッケージ.
 */
@Value(staticConstructor = "of")
public class ExcludePackage {

  @NonNull
  private final String value;
}
