package jp.kusumotolab.jacomet2;

class JNZ extends Instruction {
    JNZ(JaComet2 machine) {
        super(machine, 0x62, "JNZ", ArgType.AdrX);
    }

    @Override
    public void execute() {
        int[] adrx = getAdRx();
        int adr = adrx[0];
        int x = adrx[1];
        if (this.m.ZF == 0) {
            this.m.PR = this.getEffectiveAddress(adr, x);
        } else {
            this.m.PR += 2;
        }
    }
}
