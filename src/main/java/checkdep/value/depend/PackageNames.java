package checkdep.value.depend;

import java.util.Collection;
import java.util.Collections;

import checkdep.util.CollectionBase;

public class PackageNames extends CollectionBase<PackageName> {
  protected static final PackageNames EMPTY =
      new PackageNames(Collections.emptyList());

  public PackageNames(Collection<PackageName> raw) {
    super(raw);
  }

  public boolean contains(PackageName target) {
    return stream().anyMatch(item -> matches(target, item));
  }

  private boolean matches(PackageName name1, PackageName name2) {
    String wildcardPattern = "^.*\\*$";
    String str1 = name1.getValue();
    String str2 = name2.getValue();
    if (str1.matches(wildcardPattern)) {
      return matchesWithWildcard(str1, str2);
    }
    if (str2.matches(wildcardPattern)) {
      return matchesWithWildcard(str2, str1);
    }
    return str2.equals(str1);
  }

  private boolean matchesWithWildcard(String str1, String str2) {
    return str2.startsWith(str1.replaceAll("\\.?\\*$", ""));
  }
}
