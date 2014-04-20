package checkdep.util;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.ImmutableList;

public abstract class ListBase<E> implements Iterable<E> {
  private final ImmutableList<E> list;

  public ListBase(Collection<E> list) {
    this.list = new ImmutableList.Builder<E>().addAll(list).build();
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }

  public boolean contains(E element) {
    return list.contains(element);
  }
}
