package checkdep.value.declaration;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class ImportDeclarations implements Iterable<ImportDeclaration> {
  private final ImmutableList<ImportDeclaration> list;

  public ImportDeclarations(List<ImportDeclaration> list) {
    this.list = new ImmutableList.Builder<ImportDeclaration>().addAll(list).build();
  }

  @Override
  public Iterator<ImportDeclaration> iterator() {
    return list.iterator();
  }
}
