package checkdep.parse;

import java.io.IOException;

import jdepend.framework.JDepend;
import jdepend.framework.PackageFilter;
import checkdep.common.JDependDependency;
import checkdep.value.depend.Dependencies;

public class JDependImportParser implements ImportParser {
  private final SourceDirectories source;
  private final ExcludePackages exclude;

  public JDependImportParser(SourceDirectories source, ExcludePackages exclude) {
    this.source = source;
    this.exclude = exclude;
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
      for (SourceDirectory item : source) {
        jdepend.addDirectory(item.getValue());
      }
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private void setup(PackageFilter filter) {
    for (ExcludePackage item : exclude) {
      filter.addPackage(item.getValue());
    }
  }

  @SuppressWarnings("unchecked")
  private Dependencies toDependencies(JDepend jdepend) {
    return JDependDependency.toDependencies(jdepend.getPackages());
  }
}
