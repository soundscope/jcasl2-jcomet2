package jp.kusumotolab.jcomet2;

class SVC extends Instruction {

  SVC(JComet2 machine) {
    super(machine, 0xf0, "SVC", ArgType.AdrX);
  }

  @Override
  public void execute() {}
}
