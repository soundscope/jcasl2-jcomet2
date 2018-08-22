package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class OR2 extends Instruction {

  public OR2(JComet2 machine) {
    super(machine, 0x31, "OR", ArgType.RAdrX);
  }

  @Override
  public void execute() {
    int[] radrx = getRAdRx();
    int r = radrx[0];
    int adr = radrx[1];
    int x = radrx[2];
    int v = this.getValueAtEffectiveAddress(adr, x);
    this.m.GR[r] = this.m.GR[r] | v;
    this.updateFlags(this.m.GR[r]);
    this.m.OF = 0;
    this.m.PR += 2;
  }
}
