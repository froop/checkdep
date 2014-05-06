package checkdep.util;

import com.google.common.collect.ImmutableMap;
import lombok.NonNull;
import lombok.Value;

@Value
class ReadOnlyMapImpl<K, V> implements ReadOnlyMap<K, V> {

  @NonNull
  private final ImmutableMap<K, V> raw;

  @Override
  public String toString() {
    return raw.toString();
  }
}
