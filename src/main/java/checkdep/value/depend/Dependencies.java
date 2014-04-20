package checkdep.value.depend;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class Dependencies implements Iterable<Dependency> {
  private final ImmutableList<Dependency> list;

  public Dependencies(List<Dependency> list) {
    this.list = new ImmutableList.Builder<Dependency>().addAll(list).build();
  }

  @Override
  public Iterator<Dependency> iterator() {
    return list.iterator();
  }
}
