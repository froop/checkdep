package checkdep.util;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

public abstract class CollectionBase<E> extends ValueBase implements Iterable<E> {
  private final ImmutableCollection<E> raw;

  protected CollectionBase(Collection<E> list) {
    this.raw = new ImmutableList.Builder<E>().addAll(list).build();
  }

  @Override
  public Iterator<E> iterator() {
    return raw.iterator();
  }
}
