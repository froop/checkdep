package checkdep.util;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class StringValueBase implements Comparable<StringValueBase> {

  @NonNull
  private final String value;

  protected StringValueBase(@NonNull String value) {
    this.value = value.trim();
  }

  @Override
  public int compareTo(StringValueBase other) {
    return getValue().compareTo(other.getValue());
  }

  @Override
  public String toString() {
    return value;
  }
}
