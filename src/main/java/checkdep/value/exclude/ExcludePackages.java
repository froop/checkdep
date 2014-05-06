package checkdep.value.exclude;

import checkdep.util.ReadOnlySet;
import lombok.Delegate;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class ExcludePackages implements Iterable<ExcludePackage> {

  public static ExcludePackages of(String... packages) {
    return of(ReadOnlySet.of(packages, ExcludePackage::of));
  }

  @Delegate
  @NonNull
  private final ReadOnlySet<ExcludePackage> delegate;
}
