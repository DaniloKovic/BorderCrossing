import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Vozilo extends Thread
{
    public static int brojKreiranihVozila = 0;
    protected int VoziloID;
    public int getVoziloID() {
        return VoziloID;
    }

    public long getVrijemeProcesiranjaVozila_ms() {
        return _vrijemeProcesiranjaPutnikaVozila_ms;
    }

    public long _vrijemeProcesiranjaPutnikaVozila_ms;
    public static Random rand = new Random();

    protected int _brojPutnika;
    protected List<Putnik> putnici;

//     Resurs kojem pristupa nit
//     protected Terminal _terminal;
//
//     protected PolicijskiTerminal _policijskiTerm;
//     protected  CarinskiTerminal _carinskiTerm;

//    public PolicijskiTerminal get_policijskiTerm() {
//        return _policijskiTerm;
//    }
//
//    public CarinskiTerminal get_carinskiTerm() {
//        return _carinskiTerm;
//    }

    public Vozilo(int brojPutnika, long vrijemeProcesiranjaVozila_ms)
    {
        _brojPutnika = brojPutnika;
        _vrijemeProcesiranjaPutnikaVozila_ms = vrijemeProcesiranjaVozila_ms;

        putnici = new ArrayList<Putnik>();
        putnici.add(new Putnik(true));
        for (int i=1; i<_brojPutnika; i++)
        {
            putnici.add(new Putnik());
        }
        VoziloID = ++brojKreiranihVozila;
    }

    public Vozilo(PolicijskiTerminal pt, CarinskiTerminal ct, int brojPutnika, long vrijemeProcesiranjaVozila_ms)
    {
        _brojPutnika = brojPutnika;
        _vrijemeProcesiranjaPutnikaVozila_ms = vrijemeProcesiranjaVozila_ms;

//        _policijskiTerm = pt;
//        _carinskiTerm = ct;

        putnici = new ArrayList<Putnik>();
        putnici.add(new Putnik(true));
        for (int i=1; i<_brojPutnika; i++)
        {
            putnici.add(new Putnik());
        }
        VoziloID = ++brojKreiranihVozila;
    }

    @Override
    public void run() {

    }

    protected abstract void procesirajPutnike();

    protected abstract void procesirajPutnike(long millis);
}