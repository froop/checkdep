package checkdep.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import checkdep.util.CollectionBase;

public class ExcludePackages extends CollectionBase<ExcludePackage> {

  public static ExcludePackages of(String... directory) {
    return ExcludePackages.of(Arrays.asList(directory));
  }

  public static ExcludePackages of(Collection<String> packages) {
    List<ExcludePackage> target = new ArrayList<>();
    for (String item : packages) {
      target.add(new ExcludePackage(item));
    }
    return new ExcludePackages(target);
  }

  private ExcludePackages(Collection<ExcludePackage> directories) {
    super(directories);
  }
}
