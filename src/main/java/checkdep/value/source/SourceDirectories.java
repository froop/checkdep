package checkdep.value.source;

import checkdep.util.MyImmutableSet;

public final class SourceDirectories extends MyImmutableSet<SourceDirectory> {

  public static SourceDirectories of(String... directories) {
    return new SourceDirectories(directories);
  }

  private SourceDirectories(String... directories) {
    super(directories, SourceDirectory::of);
  }
}
