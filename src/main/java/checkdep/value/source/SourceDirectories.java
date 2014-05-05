package checkdep.value.source;

import checkdep.util.ImmutableSetBase;

public final class SourceDirectories extends ImmutableSetBase<SourceDirectory> {

  public static SourceDirectories of(String... directories) {
    return new SourceDirectories(directories);
  }

  private SourceDirectories(String... directories) {
    super(directories, SourceDirectory::of);
  }
}
