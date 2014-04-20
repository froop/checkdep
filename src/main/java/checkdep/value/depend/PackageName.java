package checkdep.value.depend;

import checkdep.util.ValueBase;

public class PackageName extends ValueBase implements Comparable<PackageName> {
  private final String value;

  public PackageName(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public int compareTo(PackageName other) {
    return getValue().compareTo(other.getValue());
  }
}
