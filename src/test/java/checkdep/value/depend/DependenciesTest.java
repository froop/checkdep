package checkdep.value.depend;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class DependenciesTest {

  @Test
  public void testIterator() {
    List<Dependency> list = Arrays.asList(
        new DependencyStub("test1"), new DependencyStub("test2"));

    Dependencies target = new Dependencies(list);

    Iterator<Dependency> it = target.iterator();
    assertThat(it.next().getName(), is(new PackageName("test1")));
    assertThat(it.next().getName(), is(new PackageName("test2")));
    assertFalse(it.hasNext());
  }

  private static class DependencyStub implements Dependency {
    private final String name;

    public DependencyStub(String name) {
      this.name = name;
    }

    @Override
    public PackageName getName() {
      return new PackageName(name);
    }
  }
}
