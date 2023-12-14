
public class LicnoVozilo extends Vozilo {

    public static final int MAX_BROJ_PUTNIKA_LICNO_VOZILO = 5;
    public static final long PROCESIRANJE_PUTNIKA_LICNO_VOZILO_ms = 500L;

    private PolicijskiTerminal _policijskiTerm2;

    private int LicnoVoziloID;

    public int getLicnoVoziloID() {
        return LicnoVoziloID;
    }

    public LicnoVozilo() throws Exception
    {
        super(rand.nextInt(MAX_BROJ_PUTNIKA_LICNO_VOZILO) + 1, PROCESIRANJE_PUTNIKA_LICNO_VOZILO_ms);

        LicnoVoziloID = super.getVoziloID();
    }

//    public LicnoVozilo(PolicijskiTerminal pt1, PolicijskiTerminal pt2, CarinskiTerminal ct) throws Exception
//    {
//        super(pt1, ct, rand.nextInt(MAX_BROJ_PUTNIKA_LICNO_VOZILO) + 1, PROCESIRANJE_PUTNIKA_LICNO_VOZILO_ms);
//        _policijskiTerm2 = pt2;
//
//        LicnoVoziloID = super.getVoziloID();
//    }

    public void run() {

        boolean obradaNaPolicijskom = false;
        String nazivPolicijskogTerminala = new String();
        boolean isZauzeoTerminal = false;

        while (!obradaNaPolicijskom) {
            // Thread.State currentState = Thread.currentThread().getState();
            // if(Main.pt1.getIsSlobodan() == true){

            isZauzeoTerminal = Main.pt1.zauzimanjeTerminala2(this);
            if (isZauzeoTerminal) {
                nazivPolicijskogTerminala = Main.pt1.get_naziv();
            }
            if (!isZauzeoTerminal) {
                isZauzeoTerminal = Main.pt2.zauzimanjeTerminala2(this);
                if (isZauzeoTerminal)
                    nazivPolicijskogTerminala = Main.pt2.get_naziv();
            }

            if (isZauzeoTerminal == true) {
                obradaNaPolicijskom = true;
            }
        }

        boolean obradaNaCarinskom = false;
        while (!obradaNaCarinskom)
        {
            if (Main.ct1.getIsSlobodan()) {
                if (nazivPolicijskogTerminala.equalsIgnoreCase("PT1")) {
                    Main.pt1.napustanjeTerminala(this);
                } else if (nazivPolicijskogTerminala.equalsIgnoreCase("PT2")) {
                    Main.pt2.napustanjeTerminala(this);
                }

                Main.ct1.zauzimanjeTerminala2(this);
                obradaNaCarinskom = true;
            }
        }
        Main.ct1.napustanjeTerminala(this);
    }

//    public void run() {
//
//        boolean obradaNaPolicijskom = false;
//        while(!obradaNaPolicijskom)
//        {
//            if(_policijskiTerm.getIsSlobodan() == true){
//                _policijskiTerm.zauzimanjeTerminala(this);
//                _policijskiTerm2 = null;
//                obradaNaPolicijskom = true;
//            }
//            else if(_policijskiTerm2.getIsSlobodan() == true){
//                _policijskiTerm2.zauzimanjeTerminala(this);
//                _policijskiTerm = null;
//                obradaNaPolicijskom = true;
//            }
//        }
//
//        boolean obradaNaCarinskom = false;
//        while (!obradaNaCarinskom)
//        {
//            if (_carinskiTerm.getIsSlobodan())
//            {
//                if(_policijskiTerm != null) {
//                    _policijskiTerm.napustanjeTerminala(this);
//                }
//                else if (_policijskiTerm2 != null) {
//                    _policijskiTerm2.napustanjeTerminala(this);
//                }
//
//                _carinskiTerm.zauzimanjeTerminala(this);
//                obradaNaCarinskom = true;
//            }
//        }
//
//        _carinskiTerm.napustanjeTerminala(this);
//    }

    @Override
    public void procesirajPutnike(long millis) {
        for (Putnik p : putnici ) {
            try {
                System.out.println("    Putnik u licnom vozilu " + this.getVoziloID() +  " se procesira...");
                Thread.sleep(PROCESIRANJE_PUTNIKA_LICNO_VOZILO_ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void procesirajPutnike() {}
}
