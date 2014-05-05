package checkdep.value.depend;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class PackageName implements Comparable<PackageName> {

  @NonNull
  private final String value;

  public boolean matches(@NonNull PackageName other) {
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
  public int compareTo(@NonNull PackageName other) {
    return value.compareTo(other.value);
  }

  @Override
  public String toString() {
    return value;
  }
}
