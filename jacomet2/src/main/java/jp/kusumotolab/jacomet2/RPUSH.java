package jp.kusumotolab.jacomet2;

class RPUSH extends Instruction {
    RPUSH(JaComet2 machine) {
        super(machine, 0xa0, "RPUSH", ArgType.NoArg);
    }

    @Override
    public void execute() {
        for (int i = 1; i < 9; i++) {
            this.m.setSP(this.m.getSP() - 1);
            this.m.memory[this.m.getSP()] = this.m.GR[i];
        }
        this.m.PR += 1;
    }
}
