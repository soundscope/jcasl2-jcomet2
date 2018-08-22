package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class JUMP extends Instruction {

  public JUMP(JComet2 machine) {
    super(machine, 0x64, "JUMP", ArgType.AdrX);
  }

  @Override
  public void execute() {
    int[] adrx = getAdRx();
    int adr = adrx[0];
    int x = adrx[1];
    this.m.PR = this.getEffectiveAddress(adr, x);
  }
}
