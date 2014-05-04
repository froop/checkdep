package checkdep.value.depend;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PackageNameTest {

  @Test
  public void testTrim() {
    assertThat(PackageName.of(" abc ").getValue(), is("abc"));
  }
}
