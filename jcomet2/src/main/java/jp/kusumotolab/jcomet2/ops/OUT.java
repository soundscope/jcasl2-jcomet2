package jp.kusumotolab.jcomet2.ops;

import java.io.IOException;
import java.io.UncheckedIOException;
import jp.kusumotolab.jcomet2.JComet2;

public class OUT extends Instruction {

  public OUT(JComet2 machine) {
    super(machine, 0x91, "OUT", ArgType.StrLen);
  }

  @Override
  public void execute() {
    int[] sl = this.getStrLen();
    int s = sl[0];
    int l = sl[1];
    int length = this.m.memory[l];
    StringBuilder sb = new StringBuilder();
    for (int i = s; i < s + length; i++) {
      if (Character.isValidCodePoint(this.m.memory[i])) {
        sb.append((char) (this.m.memory[i] & 0x000000FF));
      } else {
        System.err.println("Error:");
        this.m.printStatus();
        this.m.dump(s);
      }
    }
    try {
      String line = sb.toString() + "\n";
      if (this.m.out == null) {
        System.out.print(line);
      } else {
        this.m.out.write(line);
      }
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
    this.m.PR += 3;
  }
}
