/*
* •	Vijeme procesiranja kamiona na carinskom terminalu je 0.5 sekundi
*   Za kamione se kreira carinska dokumentacija ukoliko je to potrebno, te se isti testiraju na težinu tereta.
*   Ukoliko je kamion preopterećen isti ne može da pređe granicu.
*   Vijeme procesiranja kamiona na carinskom terminalu je 0.5 sekundi.
*   Kamioni sa kapacitetom do 3 putnika (uključujući vozača).
*   Vrijeme procesiranja putnika u ličnom vozilu i kamionu na policijskom/carinskom terminalu je 0.5 sekundi, a u autobusu 0.1 sekundu.
*   Od svih dokumenata putnika, 3% je neispravno i isti ne mogu preći granicu.
* •	Kamioni imaju teret za koji je možda potrebno generisati carinsku dokumentaciju
*   (vjerovatnoća da je potrebno generisati dokumentacija je 50%), kao i deklarisanu i stvarnu masu tereta
*   (osmisliti algoritam tako da u 20% kamiona stvarna masa tereta bude do 30% veća od deklarisane).
 * */
public class Kamion extends Vozilo {

    public static final int MAX_BROJ_PUTNIKA_KAMION = 3;
    public static final int PROCENAT_KAMIONA_SA_RAZLICITOM_STVARNOM_I_DEKLARISANOM_MASOM = 20;
    public static final long PROCESIRANJE_PUTNIKA_KAMION_ms = 500L;

    private Teret teret;

    private int KamionID;

    public int getKamionID() {
        return KamionID;
    }

    public Kamion() throws Exception
    {
        super(rand.nextInt(MAX_BROJ_PUTNIKA_KAMION) + 1, PROCESIRANJE_PUTNIKA_KAMION_ms);

        double stvarnaMasa = rand.nextDouble(100, 1000);
        if(rand.nextInt(100) < PROCENAT_KAMIONA_SA_RAZLICITOM_STVARNOM_I_DEKLARISANOM_MASOM)
        {
            teret = new Teret(stvarnaMasa, rand.nextDouble(stvarnaMasa, stvarnaMasa + stvarnaMasa * 0.3));
        }
        else
        {
            teret = new Teret(stvarnaMasa, stvarnaMasa);
        }
        KamionID = super.getVoziloID();
    }

    public Kamion(PolicijskiTerminal pt, CarinskiTerminal ct) throws Exception
    {
        super(pt, ct, rand.nextInt(MAX_BROJ_PUTNIKA_KAMION) + 1, PROCESIRANJE_PUTNIKA_KAMION_ms);

        double stvarnaMasa = rand.nextDouble(100, 1000);
        if(rand.nextInt(100) < PROCENAT_KAMIONA_SA_RAZLICITOM_STVARNOM_I_DEKLARISANOM_MASOM)
        {
            teret = new Teret(stvarnaMasa, rand.nextDouble(stvarnaMasa, stvarnaMasa + stvarnaMasa * 0.3));
        }
        else
        {
            teret = new Teret(stvarnaMasa, stvarnaMasa);
        }
        KamionID = super.getVoziloID();
    }

    public void run() {
        Main.ptZaKamion.zauzimanjeTerminala(this);

        boolean obradaNaCarinskom = false;
        while (!obradaNaCarinskom)
        {
            if (Main.ctZaKamion.getIsSlobodan())
            {
                Main.ptZaKamion.napustanjeTerminala(this);

                Main.ctZaKamion.zauzimanjeTerminala(this);
                obradaNaCarinskom = true;
            }
        }
        Main.ctZaKamion.napustanjeTerminala(this);
    }

//    public void run() {
//        _policijskiTerm.zauzimanjeTerminala(this);
//
//        boolean obradaNaCarinskom = false;
//        while (!obradaNaCarinskom) {
//            if (_carinskiTerm.getIsSlobodan()) {
//                _policijskiTerm.napustanjeTerminala(this);
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
                System.out.println("    Putnik u kamionu " + this.KamionID +  " se procesira...");
                Thread.sleep(PROCESIRANJE_PUTNIKA_KAMION_ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void procesirajPutnike() {}
}
