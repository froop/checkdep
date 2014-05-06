package checkdep.util;

import static java.util.stream.Collectors.*;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

public interface ReadOnlySet<E> extends ReadOnlyCollection<E> {

  static <E> ReadOnlySet<E> of(@NonNull ImmutableSet<E> raw) {
    return new ReadOnlySetImpl<>(raw);
  }

  static <E> ReadOnlySet<E> of(@NonNull E raw) {
    return of(ImmutableSet.of(raw));
  }

  static <E> ReadOnlySet<E> of(@NonNull String[] raw, @NonNull Function<String, E> mapper) {
    return copyOf(Arrays.stream(raw).map(mapper).collect(toSet()));
  }

  static <E> ReadOnlySet<E> copyOf(@NonNull Iterable<? extends E> raw) {
    return of(ImmutableSet.copyOf(raw));
  }

  // for Lombok @Delegate
  @Override
  default Iterator<E> iterator() {
    return ReadOnlyCollection.super.iterator();
  }

  // for Lombok @Delegate
  @Override
  default boolean isEmpty() {
    return ReadOnlyCollection.super.isEmpty();
  }

  // for Lombok @Delegate
  @Override
  default Stream<E> stream() {
    return ReadOnlyCollection.super.stream();
  }
}
