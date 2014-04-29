package checkdep.value.exclude;

import checkdep.util.CollectionBase;

public class ExcludePackages extends CollectionBase<ExcludePackage> {

  public static ExcludePackages of(String... packages) {
    return new ExcludePackages(packages);
  }

  private ExcludePackages(String... packages) {
    super(packages, ExcludePackage::new);
  }
}
