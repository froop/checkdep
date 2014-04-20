package checkdep.util;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.ImmutableList;

public abstract class ListBase<T> implements Iterable<T> {
  private final ImmutableList<T> list;

  public ListBase(Collection<T> list) {
    this.list = new ImmutableList.Builder<T>().addAll(list).build();
  }

  @Override
  public Iterator<T> iterator() {
    return list.iterator();
  }
}
