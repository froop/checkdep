package checkdep.value.constraint;

import checkdep.value.depend.DependArrow;
import lombok.Delegate;
import lombok.NonNull;
import lombok.Value;

@Value
public class Constraint {

  public static Constraint of(@NonNull String from, @NonNull String to) {
    return new Constraint(DependArrow.of(from, to));
  }

  @Delegate
  private final DependArrow delegate;
}
