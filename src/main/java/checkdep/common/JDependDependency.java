package checkdep.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jdepend.framework.JavaPackage;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

  public static Dependencies toDependencies(Collection<JavaPackage> packages) {
    List<Dependency> res = new ArrayList<Dependency>();
    for (JavaPackage item : packages) {
      res.add(new JDependDependency(item));
    }
    return new Dependencies(res);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(getName()).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof JDependDependency) {
      JDependDependency other = (JDependDependency) obj;
      return new EqualsBuilder().append(getName(), other.getName()).isEquals();
    } else {
      return false;
    }
  }
}
