package jp.kusumotolab.jcomet2;

class NOP extends Instruction {

  NOP(JComet2 machine) {
    super(machine, 0x00, "NOP", ArgType.NoArg);
  }

  @Override
  public void execute() {
    m.PR += 1;
  }
}
