package checkdep.value.depend;

import java.util.Set;

public interface Dependency {

  PackageName getName();

  Set<PackageName> getEfferents();
}
