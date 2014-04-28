package checkdep.common;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import jdepend.framework.JavaPackage;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;

public class JDependDependency implements Dependency, Comparable<Dependency> {
  private final JavaPackage raw;

  public JDependDependency(JavaPackage raw) {
    this.raw = raw;
  }

  @Override
  public PackageName getName() {
    return new PackageName(raw.getName());
  }

  @Override
  public Set<PackageName> getEfferents() {
    @SuppressWarnings("unchecked")
    Collection<JavaPackage> efferents = raw.getEfferents();
    return efferents.stream()
        .map(efferent -> new PackageName(efferent.getName()))
        .collect(Collectors.toSet());
  }

  public static Dependencies toDependencies(Collection<JavaPackage> packages) {
    return Dependencies.of(packages.stream()
        .map(item -> new JDependDependency(item))
        .collect(Collectors.toSet()));
  }

  @Override
  public int compareTo(Dependency other) {
    return getName().compareTo(other.getName());
  }
}
