package checkdep.value.depend;

import lombok.NonNull;
import lombok.Value;

@Value
public class PackageName implements Comparable<PackageName> {
  public static final PackageName NULL = new PackageName("");

  @NonNull
  private final String value;

  public PackageName(String value) {
    this.value = value.trim();
  }

  @Override
  public int compareTo(PackageName other) {
    return getValue().compareTo(other.getValue());
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

  @Override
  public String toString() {
      return value;
  }
}
