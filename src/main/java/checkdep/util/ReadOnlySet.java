package checkdep.util;

import static java.util.stream.Collectors.*;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

//TODO @Value(staticConstructor = "of")
@Value
public class ReadOnlySet<E> implements Iterable<E> {

  public static <E> ReadOnlySet<E> of(@NonNull ImmutableSet<E> raw) {
    return new ReadOnlySet<>(raw);
  }

  public static <E> ReadOnlySet<E> of(@NonNull E raw) {
    return of(ImmutableSet.of(raw));
  }

  public static <E> ReadOnlySet<E> of(@NonNull String[] raw, @NonNull Function<String, E> mapper) {
    return copyOf(Arrays.stream(raw).map(mapper).collect(toSet()));
  }

  public static <E> ReadOnlySet<E> copyOf(@NonNull Iterable<? extends E> raw) {
    return of(ImmutableSet.copyOf(raw));
  }

  @NonNull
  private final ImmutableSet<E> raw;

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
