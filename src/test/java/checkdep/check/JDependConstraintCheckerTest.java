package checkdep.check;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;
import checkdep.value.depend.PackageNames;
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
  public void testCheck_SubPackageNotAllowFrom() {
    Dependencies dependencies = Dependencies.of(Arrays.asList(
        DependencyStub.of("checkdep", Arrays.asList("checkdep.check")),
        DependencyStub.of("checkdep.a", Arrays.asList("checkdep.check"))));

    Violations res = target.check(dependencies);

    Iterator<Violation> it = res.iterator();
    assertThat(it.next().toString(), is("checkdep.a -> checkdep.check"));
    assertFalse(res.toString(), it.hasNext());
  }

  @Test
  public void testCheck_SubPackageNotAllowTo() {
    Dependencies dependencies = Dependencies.of(Arrays.asList(
        DependencyStub.of("checkdep",
            Arrays.asList("checkdep.check", "checkdep.check.a"))));

    Violations res = target.check(dependencies);

    Iterator<Violation> it = res.iterator();
    assertThat(it.next().toString(), is("checkdep -> checkdep.check.a"));
    assertFalse(res.toString(), it.hasNext());
  }

  @Test
  public void testCheck_WildcardTo() {
    target = new JDependConstraintChecker(Constraints.builder()
        .add("checkdep", "checkdep.check.*")
        .build());
    Dependencies dependencies = Dependencies.of(Arrays.asList(
        DependencyStub.of("checkdep",
            Arrays.asList("checkdep.check", "checkdep.check.a"))));

    Violations res = target.check(dependencies);

    Iterator<Violation> it = res.iterator();
    assertFalse(res.toString(), it.hasNext());
  }

  @Test
  public void testCheck_WildcardFrom() {
    target = new JDependConstraintChecker(Constraints.builder()
        .add("checkdep.check.*", "checkdep.parse")
        .build());
    Dependencies dependencies = Dependencies.of(Arrays.asList(
        DependencyStub.of("checkdep.check.a",
            Arrays.asList("checkdep.parse"))));

    Violations res = target.check(dependencies);

    Iterator<Violation> it = res.iterator();
    assertFalse(res.toString(), it.hasNext());
  }

  @Test
  public void testCheck_WildcardFromTo() {
    target = new JDependConstraintChecker(Constraints.builder()
        .add("checkdep.check.*", "checkdep.parse.*")
        .build());
    Dependencies dependencies = Dependencies.of(Arrays.asList(
        DependencyStub.of("checkdep.check.a",
            Arrays.asList("checkdep.parse.a"))));

    Violations res = target.check(dependencies);

    Iterator<Violation> it = res.iterator();
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
    private final PackageNames efferents;

    public static DependencyStub of(String name, Collection<String> efferents) {
      return new DependencyStub(
          new PackageName(name),
          efferents.stream()
            .map(str -> new PackageName(str))
            .collect(Collectors.toSet()));
    }

    public DependencyStub(PackageName name, Collection<PackageName> efferents) {
      this.name = name;
      this.efferents = new PackageNames(efferents);
    }

    @Override
    public PackageName getName() {
      return name;
    }

    @Override
    public PackageNames getEfferents() {
      return efferents;
    }
  }
}
