package checkdep.value.depend;

public interface Dependency {

  static final Dependency NULL = new Dependency() {

    @Override
    public PackageName getName() {
      return PackageName.NULL;
    }

    @Override
    public PackageNames getEfferents() {
      return PackageNames.EMPTY;
    }
  };

  PackageName getName();

  PackageNames getEfferents();
}
