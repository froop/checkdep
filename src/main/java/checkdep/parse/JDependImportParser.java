package checkdep.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;
import jdepend.framework.PackageFilter;
import checkdep.common.JDependDependency;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;

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

  private Dependencies toDependencies(JDepend jdepend) {
    List<Dependency> res = new ArrayList<Dependency>();
    @SuppressWarnings("unchecked")
    Collection<JavaPackage> items = jdepend.getPackages();
    for (JavaPackage item : items) {
      res.add(new JDependDependency(item));
    }
    return new Dependencies(res);
  }
}
