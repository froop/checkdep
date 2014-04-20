package checkdep.parse;

import jdepend.framework.JavaPackage;
import checkdep.value.depend.Dependency;

class JDependDependency implements Dependency {
  private final JavaPackage raw;

  public JDependDependency(JavaPackage raw) {
    this.raw = raw;
  }

  @Override
  public String getName() {
    return raw.getName();
  }
}
