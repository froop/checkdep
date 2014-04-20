package checkdep.value.depend;

import java.util.Collection;

import checkdep.util.ListBase;

public class Dependencies extends ListBase<Dependency> {

  public Dependencies(Collection<Dependency> list) {
    super(list);
  }
}
