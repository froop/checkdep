package checkdep.value.depend;

public interface Dependency extends Comparable<Dependency> {

  static final Dependency NULL = new Dependency() {

    @Override
    public PackageName getName() {
      return PackageName.NULL;
    }

    @Override
    public PackageNames getEfferents() {
      return PackageNames.EMPTY;
    }
  };

  PackageName getName();

  PackageNames getEfferents();

  @Override
  default int compareTo(Dependency other) {
    return getName().compareTo(other.getName());
  }

  static Dependency of(PackageName name, PackageNames efferents) {
    return new DefaultDependency(name, efferents);
  }
}
