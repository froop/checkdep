package checkdep.util;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import lombok.NonNull;

public abstract class CollectionBase<E> implements Iterable<E> {
  private final ImmutableCollection<E> raw;

  protected CollectionBase(@NonNull Iterable<E> raw) {
    this.raw = ImmutableList.copyOf(raw);
  }

  protected CollectionBase(@NonNull E raw) {
    this.raw = ImmutableList.of(raw);
  }

  protected CollectionBase(@NonNull String[] raw, @NonNull Function<String, E> mapper) {
    this(Arrays.stream(raw).map(mapper).collect(toList()));
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
    CollectionBase<E> other = (CollectionBase<E>) obj;
    return Objects.equals(raw, other.raw);
  }

  @Override
  public String toString() {
    return raw.toString();
  }
}
