package checkdep.parse;

import checkdep.value.depend.Dependencies;
import checkdep.value.exclude.ExcludePackages;
import checkdep.value.source.SourceDirectories;
import lombok.NonNull;

public interface ImportParser {

  static ImportParser of(@NonNull SourceDirectories source, @NonNull ExcludePackages exclude) {
    return new JDependImportParser(source, exclude);
  }

  Dependencies parse();
}
