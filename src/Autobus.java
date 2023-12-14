
public class Autobus extends Vozilo {
    private final Object lock = new Object();
    public static final int MAX_BROJ_PUTNIKA_AUTOBUS = 52;
    public static final long PROCESIRANJE_PUTNIKA_AUTOBUS_ms = 100L;

    private PolicijskiTerminal _policijskiTerm2;

    private int AutobusID;

    public int getAutobusID() {
        return AutobusID;
    }

    public Autobus() throws Exception {
        super(rand.nextInt(MAX_BROJ_PUTNIKA_AUTOBUS) + 1, PROCESIRANJE_PUTNIKA_AUTOBUS_ms);

        AutobusID = super.getVoziloID();
    }

    public void run() {

        boolean obradaNaPolicijskom = false;
        String nazivPolicijskogTerminala = new String();
        boolean isZauzeoTerminal = false;

            while (!obradaNaPolicijskom) {

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
                    if (nazivPolicijskogTerminala.equalsIgnoreCase("PT1"))
                    {
                        Main.pt1.napustanjeTerminala(this);
                    }
                    else if (nazivPolicijskogTerminala.equalsIgnoreCase("PT2"))
                    {
                        Main.pt2.napustanjeTerminala(this);
                    }

                    Main.ct1.zauzimanjeTerminala2(this);
                    obradaNaCarinskom = true;
                }
            }
            Main.ct1.napustanjeTerminala(this);
        }


//    public void run() {
//        int brTerm = 0;
//
//
//            boolean obradaNaPolicijskom = false;
//            while (!obradaNaPolicijskom)
//            {
//                synchronized (lock) {
//                    if (_policijskiTerm.getIsSlobodan()) {
//                        _policijskiTerm.zauzimanjeTerminala(this);
//                        //_policijskiTerm2 = null;
//                        obradaNaPolicijskom = true;
//                    } else if (_policijskiTerm2.getIsSlobodan()) {
//                        _policijskiTerm2.zauzimanjeTerminala(this);
//                        //_policijskiTerm = null;
//                        obradaNaPolicijskom = true;
//                    }
//                }
//            }
//
//
//        boolean obradaNaCarinskom = false;
//        while (!obradaNaCarinskom) {
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
                System.out.println("    Putnik u autobusu " + this.getVoziloID() +  " se procesira...");
                Thread.sleep(PROCESIRANJE_PUTNIKA_AUTOBUS_ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void procesirajPutnike() {}

}
