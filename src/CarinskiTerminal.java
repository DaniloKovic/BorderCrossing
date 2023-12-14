import java.util.LinkedList;
import java.util.Queue;

// Dijeljeni resurs
public class CarinskiTerminal extends Terminal { // Kreiraju se 2 carinska terminala od kojih je jedan uvijek rezervisan za kamione

    public Boolean isZaKamion;

    public CarinskiTerminal() {
        // TODO Auto-generated constructor stub
    }

    public CarinskiTerminal(Boolean zaKamion, String naziv) {
        // TODO Auto-generated constructor stub
        this.isZaKamion = zaKamion;
        _naziv = naziv;
    }



    @Override
    public synchronized void zauzimanjeTerminala(Vozilo v) {
        if(!isSlobodan) {
            try {
                // System.out.println(v.getClass().getName() + " "  + v.getVoziloID() + " CEKA na carinski terminal " + nazivCarinskog + ".");
                wait();
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(v.getClass().getName() + " "  + v.getVoziloID() + " ULAZI na carinski terminal " + _naziv + ".");
        // v.procesirajPutnike(v.getVrijemeProcesiranjaVozila_ms());
        isSlobodan = false;
    }

    @Override
    public synchronized Boolean zauzimanjeTerminala2(Vozilo v)
    {
        if (this.getIsSlobodan() == true)
        {
            this.setSlobodan(false);
            System.out.println(v.getClass().getName() + " "  + v.getVoziloID() + " ULAZI na carinski terminal " + _naziv + ".");
            // v.procesirajPutnike(v.getVrijemeProcesiranjaVozila_ms());
            return true;
        }
        return false;
    }

    @Override
    public synchronized void napustanjeTerminala(Vozilo v) {
        isSlobodan = true;
        System.out.println(v.getClass().getName() + " "  + v.getVoziloID() + " IZLAZI sa carinskog terminala " + _naziv + ".");
        notify();
    }

    @Override
    public void napustanjeTerminala2(Vozilo v) {    }

//    @Override
//    public synchronized void zauzimanjeTerminala(int id) {
//        if(!isSlobodan) {
//            try {
//                System.out.println("Kamion " + id + " CEKA na carinski terminal " + _naziv + ".");
//                wait();
//            }
//            catch(Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        System.out.println("Kamion " + id + " ULAZI na carinski terminal " + _naziv + ".");
//        isSlobodan = false;
//    }
//
//    @Override
//    public synchronized void zauzimanjeTerminala(String tipVozila, int id) {
//        if(!isSlobodan) {
//            try {
//                System.out.println(tipVozila + " " + id + " CEKA na carinski terminal.");
//                wait();
//            }
//            catch(Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        System.out.println(tipVozila + " " + id + " ULAZI na carinski terminal.");
//        isSlobodan = false;
//    }
//
//    @Override
//    public synchronized void napustanjeTerminala(int id) {
//        isSlobodan = true;
//        System.out.println("Kamion " + id + " IZLAZI sa carinskog terminala.");
//        notify();
//    }
//
//    @Override
//    public synchronized void napustanjeTerminala(String tipVozila, int id) {
//        isSlobodan = true;
//        System.out.println(tipVozila + " " + id + " IZLAZI sa carinskog terminala.");
//        notify();
//    }
}
