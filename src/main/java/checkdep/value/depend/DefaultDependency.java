package checkdep.value.depend;

import lombok.NonNull;
import lombok.Value;

@Value
class DefaultDependency implements Dependency {

  @NonNull
  private final PackageName name;

  @NonNull
  private final PackageNames efferents;
}
