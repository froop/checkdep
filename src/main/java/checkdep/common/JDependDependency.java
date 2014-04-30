package checkdep.common;

import static java.util.stream.Collectors.*;

import java.util.Collection;

import jdepend.framework.JavaPackage;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;
import checkdep.value.depend.PackageNames;

public class JDependDependency implements Dependency {
  private final JavaPackage raw;

  public JDependDependency(JavaPackage raw) {
    this.raw = raw;
  }

  @Override
  public PackageName getName() {
    return new PackageName(raw.getName());
  }

  @Override
  public PackageNames getEfferents() {
    return new PackageNames(getRawEfferents().stream()
        .map(item -> new PackageName(item.getName()))
        .collect(toSet()));
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
}
