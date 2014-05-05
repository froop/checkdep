package checkdep.util;

import static java.util.stream.Collectors.*;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;

import java.util.Arrays;
import java.util.function.Function;

public class MyImmutableSet<E> extends MyImmutableCollection<E> {

  public MyImmutableSet(@NonNull Iterable<E> raw) {
    super(ImmutableSet.copyOf(raw));
  }

  public MyImmutableSet(@NonNull E raw) {
    this(ImmutableSet.of(raw));
  }

  public MyImmutableSet(@NonNull String[] raw, @NonNull Function<String, E> mapper) {
    this(Arrays.stream(raw).map(mapper).collect(toSet()));
  }
}
