package checkdep.value.depend;

public interface Dependency extends Comparable<Dependency> {
  static final Dependency NULL = DefaultDependency.of(PackageName.NULL, PackageNames.EMPTY);

  PackageName getName();

  PackageNames getEfferents();

  @Override
  default int compareTo(Dependency other) {
    return getName().compareTo(other.getName());
  }

  static Dependency of(PackageName name, PackageNames efferents) {
    return DefaultDependency.of(name, efferents);
  }
}
