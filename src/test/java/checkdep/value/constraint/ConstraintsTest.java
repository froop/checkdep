package checkdep.value.constraint;

import static com.google.common.collect.Sets.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;

import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;
import checkdep.value.depend.PackageNames;

public class ConstraintsTest {

  @Test
  public void testToDependencies() {
    Constraints target = Constraints.builder()
        .add("a", "b")
        .add("a", "c")
        .add("b", "c")
        .build();

    Dependencies res = target.toDependencies();

    Iterator<Entry<PackageName, Dependency>> it = res.entrySet().iterator();
    assertEntry(it.next(), new PackageName("a"),
        newHashSet(new PackageName("b"), new PackageName("c")));
    assertEntry(it.next(), new PackageName("b"),
        newHashSet(new PackageName("c")));
    assertFalse(it.hasNext());
  }

  private void assertEntry(Entry<PackageName, Dependency> item,
      PackageName name, HashSet<PackageName> efferents) {
    assertThat(item.getKey(), is(name));
    assertThat(item.getValue().getName(), is(name));
    assertThat(item.getValue().getEfferents(), is(new PackageNames(efferents)));
  }
}
