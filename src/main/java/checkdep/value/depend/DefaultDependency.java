package checkdep.value.depend;

class DefaultDependency implements Dependency {
  private final PackageName name;
  private final PackageNames efferents;

  public DefaultDependency(PackageName name, PackageNames efferents) {
    this.name = name;
    this.efferents = efferents;
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
