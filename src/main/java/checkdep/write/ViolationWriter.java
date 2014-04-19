package checkdep.write;

import checkdep.value.violation.Violations;

public interface ViolationWriter {

  void write(Violations check);
}
