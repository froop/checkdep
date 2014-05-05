package checkdep.value.depend;

import lombok.NonNull;

public interface Dependency extends Comparable<Dependency> {

  PackageName getName();

  PackageNames getEfferents();

  @Override
  default int compareTo(@NonNull Dependency other) {
    return getName().compareTo(other.getName());
  }

  static Dependency of(@NonNull PackageName name, @NonNull PackageNames efferents) {
    return DefaultDependency.of(name, efferents);
  }
}
