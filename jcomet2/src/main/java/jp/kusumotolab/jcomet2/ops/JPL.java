package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class JPL extends Instruction {

  public JPL(JComet2 machine) {
    super(machine, 0x65, "JPL", ArgType.AdrX);
  }

  @Override
  public void execute() {
    int[] adrx = getAdRx();
    int adr = adrx[0];
    int x = adrx[1];
    if (this.m.ZF == 0 && this.m.SF == 0) {
      this.m.PR = this.getEffectiveAddress(adr, x);
    } else {
      this.m.PR += 2;
    }
  }
}
