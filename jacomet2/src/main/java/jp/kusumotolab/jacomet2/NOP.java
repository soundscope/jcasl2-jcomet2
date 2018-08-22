package jp.kusumotolab.jacomet2;

class NOP extends Instruction {
    NOP(JaComet2 machine) {
        super(machine, 0x00, "NOP", ArgType.NoArg);
    }

    @Override
    public void execute() {
        m.PR += 1;
    }
}
