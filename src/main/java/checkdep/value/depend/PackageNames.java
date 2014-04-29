package checkdep.value.depend;

import java.util.Collection;
import java.util.Collections;

import checkdep.util.CollectionBase;

public class PackageNames extends CollectionBase<PackageName> {
  protected static final PackageNames EMPTY =
      new PackageNames(Collections.emptyList());

  public PackageNames(Collection<PackageName> raw) {
    super(raw);
  }

  public boolean contains(PackageName target) {
    return stream().anyMatch(target::matches);
  }
}
