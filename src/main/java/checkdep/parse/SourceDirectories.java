package checkdep.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import checkdep.util.CollectionBase;

public class SourceDirectories extends CollectionBase<SourceDirectory> {

  public static SourceDirectories of(String... directory) {
    return SourceDirectories.of(Arrays.asList(directory));
  }

  public static SourceDirectories of(Collection<String> directories) {
    List<SourceDirectory> target = new ArrayList<SourceDirectory>();
    for (String item : directories) {
      target.add(new SourceDirectory(item));
    }
    return new SourceDirectories(target);
  }

  private SourceDirectories(Collection<SourceDirectory> directories) {
    super(directories);
  }
}
