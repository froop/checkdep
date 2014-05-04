package checkdep.value.depend;

import checkdep.util.StringValueBase;

public class PackageName extends StringValueBase {

  public static PackageName of(String value) {
    return new PackageName(value);
  }

  private PackageName(String value) {
    super(value);
  }

  public boolean matches(PackageName other) {
    String wildcard = ".*";
    String str1 = this.getValue();
    String str2 = other.getValue();
    if (str1.endsWith(wildcard)) {
      return matchesWithWildcard(str1, str2);
    }
    if (str2.endsWith(wildcard)) {
      return matchesWithWildcard(str2, str1);
    }
    return str2.equals(str1);
  }

  private boolean matchesWithWildcard(String str1, String str2) {
    return str2.startsWith(str1.replaceAll("\\.?\\*$", ""));
  }
}
