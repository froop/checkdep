package checkdep.value.declaration;

public class ImportDeclaration {
  private final String name;
  private final boolean isStatic;

  public ImportDeclaration(String name, boolean isStatic) {
    this.name = name;
    this.isStatic = isStatic;
  }

  public String getName() {
    return name;
  }

  public boolean isStatic() {
    return isStatic;
  }
}
