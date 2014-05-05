package checkdep.util;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableCollection;
import lombok.NonNull;

abstract class ImmutableCollectionBase<E> implements Iterable<E> {
  private final ImmutableCollection<E> raw;

  protected ImmutableCollectionBase(@NonNull ImmutableCollection<E> raw) {
    this.raw = raw;
  }

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
  public int hashCode() {
    return raw.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    @SuppressWarnings("unchecked")
    ImmutableCollectionBase<E> other = (ImmutableCollectionBase<E>) obj;
    return Objects.equals(raw, other.raw);
  }

  @Override
  public String toString() {
    return raw.toString();
  }
}
