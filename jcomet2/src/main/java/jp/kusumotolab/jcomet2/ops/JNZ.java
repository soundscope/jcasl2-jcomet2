package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class JNZ extends Instruction {

  public JNZ(JComet2 machine) {
    super(machine, 0x62, "JNZ", ArgType.AdrX);
  }

  @Override
  public void execute() {
    int[] adrx = getAdRx();
    int adr = adrx[0];
    int x = adrx[1];
    if (this.m.ZF == 0) {
      this.m.PR = this.getEffectiveAddress(adr, x);
    } else {
      this.m.PR += 2;
    }
  }
}
