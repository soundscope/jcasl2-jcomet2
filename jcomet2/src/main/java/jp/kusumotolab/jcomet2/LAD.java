package jp.kusumotolab.jcomet2;

class LAD extends Instruction {

  LAD(JComet2 machine) {
    super(machine, 0x12, "LAD", ArgType.RAdrX);
  }

  @Override
  public void execute() {
    int[] radrx = getRAdRx();
    int r = radrx[0];
    int adr = radrx[1];
    int x = radrx[2];
    m.GR[r] = getEffectiveAddress(adr, x);
    m.PR += 2;
  }
}
