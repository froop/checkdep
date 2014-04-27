package checkdep.check;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;
import checkdep.value.violation.Violation;
import checkdep.value.violation.Violations;

public class JDependConstraintCheckerTest {
  private JDependConstraintChecker target;

  @Before
  public void setUp() throws Exception {
    target = new JDependConstraintChecker(Constraints.builder()
        .add("checkdep", "checkdep.check")
        .build());
  }

  @Test
  public void testCheck_AllExists() {
    Dependencies dependencies = Dependencies.of(Arrays.asList(
        DependencyStub.of("checkdep", Arrays.asList("checkdep.check"))));

    Violations res = target.check(dependencies);

    Iterator<Violation> it = res.iterator();
    assertFalse(res.toString(), it.hasNext());
  }

  @Test
  public void testCheck_NotExistsDependency() {
    Dependencies dependencies = Dependencies.of(Arrays.asList(
        DependencyStub.of("checkdep",
            Arrays.asList("checkdep.check", "not.exists"))));

    Violations res = target.check(dependencies);

    Iterator<Violation> it = res.iterator();
    assertThat(it.next().toString(), is("checkdep -> not.exists"));
    assertFalse(res.toString(), it.hasNext());
  }

  @Test
  public void testCheck_NeedlessConstraint() {
    target = new JDependConstraintChecker(Constraints.builder()
        .add("a", "b")
        .add("1", "2")
        .build());
    Dependencies dependencies = Dependencies.of(Arrays.asList());

    try {
      target.check(dependencies);

      fail();
    } catch (NeedlessConstraintException e) {
      assertThat(e.getMessage(), is("[1 -> 2, a -> b]"));
    }
  }

  private static class DependencyStub implements Dependency {
    private final PackageName name;
    private final Set<PackageName> efferents;

    public static DependencyStub of(String name, Collection<String> efferents) {
      return new DependencyStub(
          new PackageName(name),
          efferents.stream()
            .map(str -> new PackageName(str))
            .collect(Collectors.toSet()));
    }

    public DependencyStub(PackageName name, Collection<PackageName> efferents) {
      this.name = name;
      this.efferents = new LinkedHashSet<>(efferents);
    }

    @Override
    public PackageName getName() {
      return name;
    }

    @Override
    public Set<PackageName> getEfferents() {
      return efferents;
    }
  }
}
