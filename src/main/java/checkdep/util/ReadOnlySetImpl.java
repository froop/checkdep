package checkdep.util;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;

@Value
class ReadOnlySetImpl<E> implements ReadOnlySet<E> {

  @NonNull
  private final ImmutableSet<E> raw;

  @Override
  public String toString() {
    return raw.toString();
  }
}
