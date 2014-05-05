package checkdep.util;

import com.google.common.collect.ImmutableCollection;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.util.Iterator;
import java.util.stream.Stream;

@Value
@NonFinal
class MyImmutableCollection<E> implements Iterable<E> {

  @NonNull
  private final ImmutableCollection<E> raw;

  @Override
  public Iterator<E> iterator() {
    return raw.iterator();
  }

  public boolean isEmpty() {
    return raw.isEmpty();
  }

  public Stream<E> stream() {
    return raw.stream();
  }

  @Override
  public String toString() {
    return raw.toString();
  }
}
