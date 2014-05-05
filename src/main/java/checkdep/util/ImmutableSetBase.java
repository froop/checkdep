package checkdep.util;

import static java.util.stream.Collectors.*;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;

import java.util.Arrays;
import java.util.function.Function;

public abstract class ImmutableSetBase<E> extends ImmutableCollectionBase<E> {

  protected ImmutableSetBase(@NonNull Iterable<E> raw) {
    super(ImmutableSet.copyOf(raw));
  }

  protected ImmutableSetBase(@NonNull E raw) {
    this(ImmutableSet.of(raw));
  }

  protected ImmutableSetBase(@NonNull String[] raw, @NonNull Function<String, E> mapper) {
    this(Arrays.stream(raw).map(mapper).collect(toSet()));
  }
}
