package checkdep.value.exclude;

import checkdep.util.ImmutableSetBase;

public class ExcludePackages extends ImmutableSetBase<ExcludePackage> {

  public static ExcludePackages of(String... packages) {
    return new ExcludePackages(packages);
  }

  private ExcludePackages(String... packages) {
    super(packages, ExcludePackage::of);
  }
}
