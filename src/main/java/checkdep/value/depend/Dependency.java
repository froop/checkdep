package checkdep.value.depend;

import java.util.Collection;

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

  static Dependency of(PackageName name, Collection<PackageName> efferents) {
    return new Default(name, efferents);
  }

  static class Default implements Dependency {
    private final PackageName name;
    private final PackageNames efferents;

    public Default(PackageName name, Collection<PackageName> efferents) {
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
}
