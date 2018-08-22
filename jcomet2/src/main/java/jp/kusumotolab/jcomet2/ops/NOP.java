package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class NOP extends Instruction {

  public NOP(JComet2 machine) {
    super(machine, 0x00, "NOP", ArgType.NoArg);
  }

  @Override
  public void execute() {
    m.PR += 1;
  }
}
