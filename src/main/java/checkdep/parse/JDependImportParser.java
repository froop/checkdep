package checkdep.parse;

import java.io.IOException;

import jdepend.framework.JDepend;
import jdepend.framework.PackageFilter;
import checkdep.common.JDependDependency;
import checkdep.value.depend.Dependencies;

public class JDependImportParser implements ImportParser {
  private final SourceDirectories source;
  private final Filter filter;

  public JDependImportParser(SourceDirectories source, Filter filter) {
    this.source = source;
    this.filter = filter;
  }

  @Override
  public Dependencies parse() {
    JDepend jdepend = new JDepend();
    setup(jdepend);
    setup(jdepend.getFilter());
    jdepend.analyze();
    return toDependencies(jdepend);
  }

  private void setup(JDepend jdepend) {
    try {
      // TODO
      jdepend.addDirectory("target/classes");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private void setup(PackageFilter filter) {
    // TODO
    filter.addPackage("java.lang");
    filter.addPackage("java.util");
  }

  @SuppressWarnings("unchecked")
  private Dependencies toDependencies(JDepend jdepend) {
    return JDependDependency.toDependencies(jdepend.getPackages());
  }
}
