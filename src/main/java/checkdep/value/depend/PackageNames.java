package checkdep.value.depend;

import java.util.Collection;
import java.util.Collections;

import checkdep.util.CollectionBase;

import com.google.common.collect.ImmutableSet;

public class PackageNames extends CollectionBase<PackageName> {
  public static final PackageNames EMPTY =
      new PackageNames(Collections.emptySet());

  public static PackageNames of(Collection<PackageName> raw) {
    return new PackageNames(ImmutableSet.copyOf(raw));
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
    return PackageNames.of(ImmutableSet.<PackageName>builder()
        .addAll(this)
        .addAll(adding)
        .build());
  }
}
