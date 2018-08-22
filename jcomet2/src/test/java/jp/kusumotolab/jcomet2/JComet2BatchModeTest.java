package jp.kusumotolab.jcomet2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JComet2BatchModeTest extends JComet2Test {

  @Before
  public void before() {
    tmpDir.mkdir();
  }

  @Test(timeout = 1000)
  public void testCount() throws Exception {
    String target = "001";
    Path comPath = Paths.get(JComet2BatchModeTest.class.getResource("/enshud/" + target + ".com")
        .toURI());
    Path ansPath = Paths.get(JComet2BatchModeTest.class.getResource("/enshud/" + target + ".ans")
        .toURI());
    Path inputPath = Paths.get(tmpDir.toString(), target + ".com");
    Files.copy(comPath, inputPath, StandardCopyOption.REPLACE_EXISTING);
    String actual = runJComet2(new String[] {inputPath.toString(), "-c", "-r"}).replace("\r", "");
    String expected = parseExpected(ansPath).replace("\r", "") + "Step count: 87930\n";
    Assert.assertEquals(expected, actual);
  }

  @Test(timeout = 10000)
  public void testWatch() throws Exception {
    String target = "001";
    Path comPath = Paths.get(JComet2BatchModeTest.class.getResource("/enshud/" + target + ".com")
        .toURI());
    Path expectedPath =
        Paths.get(JComet2BatchModeTest.class.getResource("/enshud/" + target + "-watch.txt.gz")
            .toURI());
    Path inputPath = Paths.get(tmpDir.toString(), target + ".com");
    Files.copy(comPath, inputPath, StandardCopyOption.REPLACE_EXISTING);
    String actual =
        runJComet2(new String[] {inputPath.toString(), "-w", "PR,GR0,#001b"}).replace("\r", "")
            .trim();
    String expected;
    try (InputStream is = Files.newInputStream(expectedPath);
        GZIPInputStream gzis = new GZIPInputStream(is);
        InputStreamReader isr = new InputStreamReader(gzis);
        BufferedReader br = new BufferedReader(isr)) {
      expected = br.lines()
          .collect(Collectors.joining("\n"));
    }
    Assert.assertEquals(expected, actual);
  }

  @Test(timeout = 1000)
  public void testHelp() throws Exception {
    String actual = runJComet2(new String[] {"-h"}).replace("\r", "");
    String expected = "Usage: JComet2 [options] input.com\n" + "Options:\n"
        + "  -h, --help            show this help message and exit\n"
        + "  -c, --count-step      count step.\n"
        + "  -d, --dump            dump last status to last_state.txt.\n"
        + "  -r, --run             run\n" + "  -w WATCHVARIABLES, --watch=WATCHVARIABLES\n"
        + "                        run in watching mode. (ex. -w PR,GR0,GR8,#001f)\n"
        + "  -D, --Decimal         watch GR[0-8] and specified address in decimal\n"
        + "                        notation. (Effective in watcing mode only)\n"
        + "  -v, --version         display version information.\n";
    Assert.assertEquals(expected, actual);
  }

}
