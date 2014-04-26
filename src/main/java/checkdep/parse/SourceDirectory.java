package checkdep.parse;

import checkdep.util.ValueBase;

/**
 * 元ディレクトリ.
 * 解析するclasspath.
 */
public class SourceDirectory extends ValueBase {
  private final String directory;

  public SourceDirectory(String directory) {
    this.directory = directory;
  }

  public String getDirectory() {
    return directory;
  }
}
