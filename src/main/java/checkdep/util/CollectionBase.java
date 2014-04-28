package checkdep.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

public abstract class CollectionBase<E> extends ValueBase implements Iterable<E> {
  private final ImmutableCollection<E> raw;

  protected CollectionBase(Collection<E> raw) {
    this.raw = ImmutableList.copyOf(raw);
  }

  public CollectionBase(String[] raw, Function<String, E> map) {
    this(Arrays.stream(raw)
        .map(map)
        .collect(Collectors.toList()));
  }

  @Override
  public Iterator<E> iterator() {
    return raw.iterator();
  }

  public int size() {
    return raw.size();
  }

  public boolean isEmpty() {
    return raw.isEmpty();
  }

  public Stream<E> stream() {
    return raw.stream();
  }
}
