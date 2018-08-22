package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class OR1 extends Instruction {

  public OR1(JComet2 machine) {
    super(machine, 0x35, "OR", ArgType.R1R2);
  }

  @Override
  public void execute() {
    int[] r1r2 = getR1R2();
    int r1 = r1r2[0];
    int r2 = r1r2[1];
    this.m.GR[r1] = this.m.GR[r1] | this.m.GR[r2];
    this.updateFlags(this.m.GR[r1]);
    this.m.OF = 0;
    m.PR += 1;
  }
}
