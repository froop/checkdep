package checkdep.value.declaration;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import checkdep.value.declaration.ImportDeclaration;
import checkdep.value.declaration.ImportDeclarations;

public class ImportDeclarationsTest {

  @Test
  public void testIterator() {
    List<ImportDeclaration> list = Arrays.asList(
        new ImportDeclaration("test1", false), new ImportDeclaration("test2", false));

    ImportDeclarations target = new ImportDeclarations(list);

    Iterator<ImportDeclaration> it = target.iterator();
    assertThat(it.next().getName(), is("test1"));
    assertThat(it.next().getName(), is("test2"));
    assertFalse(it.hasNext());
  }
}
