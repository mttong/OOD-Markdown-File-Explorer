package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * tests driver
 */
class DriverTest {

  /**
   * Driver tester
   *
   * @throws IOException for createTempDirectory
   */
  @Test
  public void testMain() throws IOException {
    Path temp = Files.createTempDirectory("t");
    String[] args = {temp.toString(), "filename", "out.md"};


    Driver.main(args);

  }

}