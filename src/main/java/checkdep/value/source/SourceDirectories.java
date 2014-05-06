package checkdep.value.source;

import checkdep.util.ReadOnlySet;
import lombok.Delegate;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class SourceDirectories implements Iterable<SourceDirectory> {

  public static SourceDirectories of(String... directories) {
    return of(ReadOnlySet.of(directories, SourceDirectory::of));
  }

  @Delegate
  @NonNull
  private final ReadOnlySet<SourceDirectory> delegate;
}
