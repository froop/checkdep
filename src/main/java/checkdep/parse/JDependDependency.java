package checkdep.parse;

import static java.util.stream.Collectors.*;

import java.util.Collection;

import jdepend.framework.JavaPackage;
import lombok.NonNull;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;
import checkdep.value.depend.PackageNames;

class JDependDependency implements Dependency {
  private final JavaPackage raw;

  private JDependDependency(@NonNull JavaPackage raw) {
    this.raw = raw;
  }

  @Override
  public PackageName getName() {
    return PackageName.of(raw.getName());
  }

  @Override
  public PackageNames getEfferents() {
    return PackageNames.of(getRawEfferents().stream()
        .map(item -> PackageName.of(item.getName()))
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
