package checkdep.check;

import checkdep.value.depend.PackageName;

public class Constraint {
  private final PackageName afferent;
  private final PackageName efferent;

  public Constraint(PackageName afferent, PackageName efferent) {
    this.afferent = afferent;
    this.efferent = efferent;
  }

  public Constraint(String afferent, String efferent) {
    this(new PackageName(afferent), new PackageName(efferent));
  }

  public PackageName getAfferent() {
    return afferent;
  }

  public PackageName getEfferent() {
    return efferent;
  }
}
