package checkdep.util;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

public abstract class CollectionBase<E> extends ValueBase implements Iterable<E> {
  private final ImmutableCollection<E> raw;

  protected CollectionBase(Collection<E> raw) {
    this.raw = ImmutableList.copyOf(raw);
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
}
