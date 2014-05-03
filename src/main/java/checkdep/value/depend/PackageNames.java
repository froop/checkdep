package checkdep.value.depend;

import static com.google.common.collect.Sets.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import checkdep.util.CollectionBase;

public class PackageNames extends CollectionBase<PackageName> {
  protected static final PackageNames EMPTY =
      new PackageNames(Collections.emptySet());

  public static PackageNames of(Collection<PackageName> raw) {
    return new PackageNames(new TreeSet<>(raw));
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

  public PackageNames merge(PackageNames adding) {
    Set<PackageName> res = newHashSet(this);
    res.addAll(newHashSet(adding));
    return PackageNames.of(res);
  }
}
