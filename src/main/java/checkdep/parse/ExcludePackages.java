package checkdep.parse;

import java.util.Arrays;
import java.util.Collection;

import checkdep.util.CollectionBase;
import checkdep.util.CollectionMapper;

public class ExcludePackages extends CollectionBase<ExcludePackage> {

  public static ExcludePackages of(String... directory) {
    return ExcludePackages.of(Arrays.asList(directory));
  }

  public static ExcludePackages of(Collection<String> strings) {
    return new ExcludePackages(toParticular(strings));
  }

  private static Collection<ExcludePackage> toParticular(
      Collection<String> strings) {
    return new CollectionMapper<String, ExcludePackage>(strings)
        .toSet(raw -> new ExcludePackage(raw));
  }

  private ExcludePackages(Collection<ExcludePackage> directories) {
    super(directories);
  }
}
