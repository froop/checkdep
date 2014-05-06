package checkdep.value.depend;

import static java.util.Collections.*;

import checkdep.util.ReadOnlySet;
import com.google.common.collect.Iterables;

import lombok.Delegate;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class PackageNames implements Iterable<PackageName> {
  public static final PackageNames EMPTY = copyOf(emptySet());

  public static PackageNames copyOf(Iterable<PackageName> raw) {
    return of(ReadOnlySet.copyOf(raw));
  }

  public static PackageNames of(PackageName raw) {
    return of(ReadOnlySet.of(raw));
  }

  @Delegate
  @NonNull
  private final ReadOnlySet<PackageName> set;

  public boolean contains(PackageName target) {
    return stream().anyMatch(target::matches);
  }

  public PackageNames concat(PackageNames adding) {
    return copyOf(Iterables.concat(this, adding));
  }
}
