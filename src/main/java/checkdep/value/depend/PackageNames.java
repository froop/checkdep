package checkdep.value.depend;

import static java.util.Collections.*;

import checkdep.util.CollectionBase;
import com.google.common.collect.Iterables;

import com.google.common.collect.ImmutableSet;

public class PackageNames extends CollectionBase<PackageName> {
  public static final PackageNames EMPTY = new PackageNames(emptySet());

  public static PackageNames of(Iterable<PackageName> raw) {
    return new PackageNames(ImmutableSet.copyOf(raw));
  }

  public static PackageNames of(PackageName raw) {
    return new PackageNames(raw);
  }

  private PackageNames(Iterable<PackageName> raw) {
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
