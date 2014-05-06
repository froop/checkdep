package checkdep.util;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import lombok.NonNull;
import lombok.Value;

//@Value(staticConstructor = "of") なぜかIntelliJだとエラーになるのでof()を手動化
@Value
public class ReadOnlyMap<K, V> {

  public static <K, V> ReadOnlyMap<K, V> of(ImmutableMap<K, V> raw) {
    return new ReadOnlyMap<>(raw);
  }

  public static <K, V> ReadOnlyMap<K, V> copyOf(Map<? extends K, ? extends V> raw) {
    return of(ImmutableMap.copyOf(raw));
  }

  @NonNull
  private final ImmutableMap<K, V> raw;

  public Optional<V> get(@NonNull K key) {
    return Optional.ofNullable(raw.get(key));
  }

  public Set<K> keySet() {
    return raw.keySet();
  }

  public Collection<V> values() {
    return raw.values();
  }

  public Set<Map.Entry<K, V>> entrySet() {
    return raw.entrySet();
  }

  @Override
  public String toString() {
    return raw.toString();
  }
}
