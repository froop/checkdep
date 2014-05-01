package checkdep.value.depend;

import java.util.Collection;

class DefaultDependency implements Dependency {
  private final PackageName name;
  private final PackageNames efferents;

  public DefaultDependency(PackageName name, Collection<PackageName> efferents) {
    this.name = name;
    this.efferents = new PackageNames(efferents);
  }

  @Override
  public PackageName getName() {
    return name;
  }

  @Override
  public PackageNames getEfferents() {
    return efferents;
  }
}
