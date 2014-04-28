package checkdep.parse;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import checkdep.util.CollectionBase;

public class ExcludePackages extends CollectionBase<ExcludePackage> {

  public static ExcludePackages of(String... directory) {
    return ExcludePackages.of(Arrays.asList(directory));
  }

  public static ExcludePackages of(Collection<String> strings) {
    return new ExcludePackages(toExcludePackageSet(strings));
  }

  private static Set<ExcludePackage> toExcludePackageSet(
      Collection<String> strings) {
    return strings.stream()
        .map(item -> new ExcludePackage(item))
        .collect(Collectors.toSet());
  }

  private ExcludePackages(Collection<ExcludePackage> directories) {
    super(directories);
  }
}
