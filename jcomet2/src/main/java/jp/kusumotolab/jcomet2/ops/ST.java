package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class ST extends Instruction {

  public ST(JComet2 machine) {
    super(machine, 0x11, "ST", ArgType.RAdrX);
  }

  @Override
  public void execute() {
    int[] radrx = getRAdRx();
    int r = radrx[0];
    int adr = radrx[1];
    int x = radrx[2];
    m.memory[getEffectiveAddress(adr, x)] = m.GR[r];
    m.PR += 2;
  }
}
