package checkdep.parse;

import java.io.IOException;

import jdepend.framework.JDepend;
import jdepend.framework.PackageFilter;
import checkdep.value.depend.Dependencies;
import checkdep.value.exclude.ExcludePackages;
import checkdep.value.source.SourceDirectories;
import checkdep.value.source.SourceDirectory;

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
    source.forEach(item -> setup(jdepend, item));
  }

  private void setup(JDepend jdepend, SourceDirectory directory) {
    try {
      jdepend.addDirectory(directory.getValue());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private void setup(PackageFilter filter) {
    exclude.forEach(item -> filter.addPackage(item.getValue()));
  }

  @SuppressWarnings("unchecked")
  private Dependencies toDependencies(JDepend jdepend) {
    return JDependDependency.toDependencies(jdepend.getPackages());
  }
}
