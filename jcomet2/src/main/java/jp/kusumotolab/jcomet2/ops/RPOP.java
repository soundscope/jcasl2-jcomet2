package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class RPOP extends Instruction {

  public RPOP(JComet2 machine) {
    super(machine, 0xa1, "RPOP", ArgType.NoArg);
  }

  @Override
  public void execute() {
    for (int i = 8; i > 0; i--) {
      this.m.GR[i] = this.m.memory[this.m.getSP()];
      this.m.setSP(this.m.getSP() + 1);
    }
    this.m.PR += 1;
  }
}
