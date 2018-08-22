package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class SUBA2 extends Instruction {

  public SUBA2(JComet2 machine) {
    super(machine, 0x21, "SUBA", ArgType.RAdrX);
  }

  @Override
  public void execute() {
    int[] radrx = getRAdRx();
    int r = radrx[0];
    int adr = radrx[1];
    int x = radrx[2];
    int v = this.getValueAtEffectiveAddress(adr, x);
    int result = l2a(this.m.GR[r]) - l2a(v);
    this.m.GR[r] = a2l(result);
    this.updateFlags(result);
    m.PR += 2;
  }
}
