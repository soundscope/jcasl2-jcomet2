package jp.kusumotolab.jcomet2;

class JUMP extends Instruction {

  JUMP(JComet2 machine) {
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
