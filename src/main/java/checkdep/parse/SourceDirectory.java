package checkdep.parse;

/**
 * 元ディレクトリ.
 * 解析するclasspath.
 */
public class SourceDirectory {
  private final String directory;

  public SourceDirectory(String directory) {
    this.directory = directory;
  }

  public String getDirectory() {
    return directory;
  }
}
