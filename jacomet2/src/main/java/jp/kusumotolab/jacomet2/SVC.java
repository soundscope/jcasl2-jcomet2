package jp.kusumotolab.jacomet2;

class SVC extends Instruction {
    SVC(JaComet2 machine) {
        super(machine, 0xf0, "SVC", ArgType.AdrX);
    }

    @Override
    public void execute() {
    }
}
