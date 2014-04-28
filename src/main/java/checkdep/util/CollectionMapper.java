package checkdep.util;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionMapper<T, R> {
  private final Collection<T> raw;

  public CollectionMapper(Collection<T> raw) {
    this.raw = raw;
  }

  public Set<R> toSet(Function<T, R> toParticular) {
    return raw.stream()
        .map(toParticular)
        .collect(Collectors.toSet());
  }
}
