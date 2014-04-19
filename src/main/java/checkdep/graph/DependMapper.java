package checkdep.graph;

import checkdep.value.declaration.PackageDeclaration;
import checkdep.value.graph.DependGraph;

public interface DependMapper {

  DependGraph map(PackageDeclaration declaration);
}
