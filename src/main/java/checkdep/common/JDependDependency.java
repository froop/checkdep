package checkdep.common;

import static java.util.stream.Collectors.*;

import java.util.Collection;
import java.util.Set;

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
    return getRawEfferents().stream()
        .map(efferent -> new PackageName(efferent.getName()))
        .collect(toSet());
  }

  @SuppressWarnings("unchecked")
  private Collection<JavaPackage> getRawEfferents() {
    return raw.getEfferents();
  }

  public static Dependencies toDependencies(Collection<JavaPackage> packages) {
    return Dependencies.of(packages.stream()
        .map(JDependDependency::new)
        .collect(toSet()));
  }

  @Override
  public int compareTo(Dependency other) {
    return getName().compareTo(other.getName());
  }
}
