package checkdep.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jdepend.framework.JavaPackage;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;

public class JDependDependency implements Dependency {
  private final JavaPackage raw;

  public JDependDependency(JavaPackage raw) {
    this.raw = raw;
  }

  @Override
  public String getName() {
    return raw.getName();
  }

  public static Dependencies toDependencies(Collection<JavaPackage> packages) {
    List<Dependency> res = new ArrayList<Dependency>();
    for (JavaPackage item : packages) {
      res.add(new JDependDependency(item));
    }
    return new Dependencies(res);
  }
}
