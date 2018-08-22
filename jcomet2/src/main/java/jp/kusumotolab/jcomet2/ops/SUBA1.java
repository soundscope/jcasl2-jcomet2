package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class SUBA1 extends Instruction {

  public SUBA1(JComet2 machine) {
    super(machine, 0x25, "SUBA", ArgType.R1R2);
  }

  @Override
  public void execute() {
    int[] r1r2 = getR1R2();
    int r1 = r1r2[0];
    int r2 = r1r2[1];
    int result = l2a(this.m.GR[r1]) - l2a(this.m.GR[r2]);
    this.m.GR[r1] = a2l(result);
    this.updateFlags(result);
    m.PR += 1;
  }
}
