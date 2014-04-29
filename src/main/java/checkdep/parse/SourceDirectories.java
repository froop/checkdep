package checkdep.parse;

import checkdep.util.CollectionBase;

public class SourceDirectories extends CollectionBase<SourceDirectory> {

  public static SourceDirectories of(String... directories) {
    return new SourceDirectories(directories);
  }

  private SourceDirectories(String... directories) {
    super(directories, SourceDirectory::new);
  }
}
