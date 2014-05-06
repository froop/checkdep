package checkdep.util;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import lombok.NonNull;

public interface ReadOnlyMap<K, V> {

  static <K, V> ReadOnlyMap<K, V> of(ImmutableMap<K, V> raw) {
    return new ReadOnlyMapImpl<>(raw);
  }

  static <K, V> ReadOnlyMap<K, V> copyOf(Map<? extends K, ? extends V> raw) {
    return of(ImmutableMap.copyOf(raw));
  }

  default Optional<V> get(@NonNull K key) {
    return Optional.ofNullable(getRaw().get(key));
  }

  default Set<K> keySet() {
    return getRaw().keySet();
  }

  default Collection<V> values() {
    return getRaw().values();
  }

  default Set<Map.Entry<K, V>> entrySet() {
    return getRaw().entrySet();
  }

  ImmutableMap<K, V> getRaw();
}
