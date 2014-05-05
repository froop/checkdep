package checkdep.value.exclude;

import checkdep.util.MyImmutableSet;

public final class ExcludePackages extends MyImmutableSet<ExcludePackage> {

  public static ExcludePackages of(String... packages) {
    return new ExcludePackages(packages);
  }

  private ExcludePackages(String... packages) {
    super(packages, ExcludePackage::of);
  }
}
