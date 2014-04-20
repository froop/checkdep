package checkdep.value.depend;

import checkdep.util.ValueBase;

public class PackageName extends ValueBase {
  private final String value;

  public PackageName(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
