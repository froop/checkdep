package checkdep.value.source;

import checkdep.util.MyImmutableSet;
import lombok.Delegate;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public final class SourceDirectories implements Iterable<SourceDirectory> {

  public static SourceDirectories of(String... directories) {
    return of(MyImmutableSet.of(directories, SourceDirectory::of));
  }

  @Delegate
  @NonNull
  private final MyImmutableSet<SourceDirectory> delegate;
}
