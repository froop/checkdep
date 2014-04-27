package checkdep.value.depend;

import java.util.Collections;
import java.util.Set;

public interface Dependency {

  static final Dependency NULL = new Dependency() {

    @Override
    public PackageName getName() {
      return PackageName.NULL;
    }

    @Override
    public Set<PackageName> getEfferents() {
      return Collections.emptySet();
    }
  };

  PackageName getName();

  Set<PackageName> getEfferents();
}
