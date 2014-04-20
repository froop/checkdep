package checkdep.parse;

import jdepend.framework.JavaPackage;
import checkdep.value.depend.Dependency;

class JDependDependency implements Dependency {
  private final String name;

  public JDependDependency(JavaPackage raw) {
    this.name = raw.getName();
  }

  @Override
  public String getName() {
    return name;
  }
}
