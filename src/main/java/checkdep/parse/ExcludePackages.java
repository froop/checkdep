package checkdep.parse;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import checkdep.util.CollectionBase;

public class ExcludePackages extends CollectionBase<ExcludePackage> {

  public static ExcludePackages of(String... directory) {
    return new ExcludePackages(Arrays.stream(directory)
        .map(item -> new ExcludePackage(item))
        .collect(Collectors.toSet()));
  }

  private ExcludePackages(Collection<ExcludePackage> directories) {
    super(directories);
  }
}
