package jp.kusumotolab.jcomet2.ops;

import jp.kusumotolab.jcomet2.JComet2;

public class SVC extends Instruction {

  public SVC(JComet2 machine) {
    super(machine, 0xf0, "SVC", ArgType.AdrX);
  }

  @Override
  public void execute() {}
}
