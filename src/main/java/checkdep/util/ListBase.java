package checkdep.util;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.ImmutableList;

public abstract class ListBase<E> extends ValueBase implements Iterable<E> {
  private final ImmutableList<E> list;

  protected ListBase(Collection<E> list) {
    this.list = new ImmutableList.Builder<E>().addAll(list).build();
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }
}
