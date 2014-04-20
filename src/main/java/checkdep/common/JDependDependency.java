package checkdep.common;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import jdepend.framework.JavaPackage;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;

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
  public Set<PackageName> getEfferents() {
    Set<PackageName> res = new LinkedHashSet<PackageName>();
    @SuppressWarnings("unchecked")
    Collection<JavaPackage> efferents = raw.getEfferents();
    for (JavaPackage efferent : efferents) {
      res.add(new PackageName(efferent.getName()));
    }
    return res;
  }

  public static Dependencies toDependencies(Collection<JavaPackage> packages) {
    Set<Dependency> res = new LinkedHashSet<Dependency>();
    for (JavaPackage item : packages) {
      res.add(new JDependDependency(item));
    }
    return Dependencies.of(res);
  }
}
