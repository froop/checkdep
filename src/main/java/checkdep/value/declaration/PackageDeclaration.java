package checkdep.value.declaration;

public class PackageDeclaration {
  private final String name;
  private final ImportDeclarations imports;

  public PackageDeclaration(String name, ImportDeclarations imports) {
    this.name = name;
    this.imports = imports;
  }

  public String getName() {
    return name;
  }

  public ImportDeclarations getImports() {
    return imports;
  }
}
