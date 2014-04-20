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
        new Dependency("test1"), new Dependency("test2"));

    Dependencies target = new Dependencies(list);

    Iterator<Dependency> it = target.iterator();
    assertThat(it.next().getName(), is("test1"));
    assertThat(it.next().getName(), is("test2"));
    assertFalse(it.hasNext());
  }
}
