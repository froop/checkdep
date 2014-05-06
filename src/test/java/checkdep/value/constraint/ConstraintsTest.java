package checkdep.value.constraint;

import static com.google.common.collect.Sets.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Set;

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

    assertElement(res, PackageName.of("a"),
        newHashSet(PackageName.of("b"), PackageName.of("c")));
    assertElement(res, PackageName.of("b"),
        newHashSet(PackageName.of("c")));
    assertThat(res.size(), is(2));
  }

  private void assertElement(Dependencies deps, PackageName name, Set<PackageName> efferents) {
    Dependency item = deps.get(name).get();
    assertThat(item.getName(), is(name));
    assertThat(item.getEfferents(), is(PackageNames.copyOf(efferents)));
  }
}
