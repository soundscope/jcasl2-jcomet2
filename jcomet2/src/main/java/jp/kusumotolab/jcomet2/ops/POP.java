package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class POP extends Instruction {

  public POP(JComet2 machine) {
    super(machine, 0x71, "POP", ArgType.R);
  }

  @Override
  public void execute() {
    int r = getR();
    this.m.GR[r] = this.m.memory[this.m.getSP()];
    this.m.setSP(this.m.getSP() + 1);
    this.m.PR += 1;
  }
}
