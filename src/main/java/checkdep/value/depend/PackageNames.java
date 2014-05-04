package checkdep.value.depend;

import static java.util.Collections.*;
import static com.google.common.collect.Sets.*;

import java.util.Collection;

import checkdep.util.CollectionBase;
import com.google.common.collect.Iterables;

public class PackageNames extends CollectionBase<PackageName> {
  protected static final PackageNames EMPTY = new PackageNames(emptySet());

  public static PackageNames of(Iterable<PackageName> raw) {
    return new PackageNames(newTreeSet(raw));
  }

  public static PackageNames of(PackageName raw) {
    return new PackageNames(raw);
  }

  private PackageNames(Collection<PackageName> raw) {
    super(raw);
  }

  private PackageNames(PackageName raw) {
    super(raw);
  }

  public boolean contains(PackageName target) {
    return stream().anyMatch(target::matches);
  }

  public PackageNames concat(PackageNames adding) {
    return of(Iterables.concat(this, adding));
  }
}
