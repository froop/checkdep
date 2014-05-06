package checkdep.util;

import com.google.common.collect.ImmutableCollection;

import java.util.Iterator;
import java.util.stream.Stream;

public interface ReadOnlyCollection<E> extends Iterable<E> {

  @Override
  default Iterator<E> iterator() {
    return getRaw().iterator();
  }

  default boolean isEmpty() {
    return getRaw().isEmpty();
  }

  default Stream<E> stream() {
    return getRaw().stream();
  }

  ImmutableCollection<E> getRaw();
}
