// Dijeljeni resurs - zahtjeva postojanje sinhronizovanih metoda
// sinhronizacija pomocu wait() <-> notify() mehanizma()
public class PolicijskiTerminal extends Terminal { // Ukupno se kreiraju 3 policijska terminala od kojih jedan "opsluzuje" kamione, a drugi automobile i autobuse

    public Boolean isZaKamion;

    public PolicijskiTerminal() {
        // TODO Auto-generated constructor stub
    }

    public PolicijskiTerminal(Boolean zaKamion, String naziv) {
        // TODO Auto-generated constructor stub
        this.isZaKamion = zaKamion;
        _naziv = naziv;
    }

    @Override
    public synchronized void zauzimanjeTerminala(Vozilo v){

        // System.out.println("ZAUZIMANJE " + this._naziv + " " + this.isSlobodan);
        if(!isSlobodan) // Ako je terminal zauzet
        {
            try {
                wait(); // Obavjestava thredove da moraju sacekati
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(v.getClass().getName() + " "  + v.getVoziloID() + " ULAZI na policijski terminal " + _naziv + ".");
        // v.procesirajPutnike(v.getVrijemeProcesiranjaVozila_ms());
        this.setSlobodan(false);
    }

    @Override
    public synchronized Boolean zauzimanjeTerminala2(Vozilo v)
    {
        if (this.getIsSlobodan() == true) {
            this.setSlobodan(false);
            System.out.println(v.getClass().getName() + " "  + v.getVoziloID() + " ULAZI na policijski terminal " + _naziv + ".");
            // v.procesirajPutnike(v.getVrijemeProcesiranjaVozila_ms());
            return true;
        }
        return false;
    }

    @Override
    public synchronized void napustanjeTerminala(Vozilo v) {
        isSlobodan = true;
        // System.out.println("NAPUSTANJE " + this._naziv + " " + this.isSlobodan);
        System.out.println(v.getClass().getName() + " "  + v.getVoziloID() + " IZLAZI sa policijskog terminala " + _naziv + ".");
        notify(); // Obavjestava jednu od niti koja je u redu za cekanje da je monitor ovog objekta slobodan
    }

    @Override
    public void napustanjeTerminala2(Vozilo v) { }

//    @Override
//    public synchronized void zauzimanjeTerminala(int id) {
//        if(!isSlobodan) // Ako je terminal zauzet
//        {
//            try {
//                System.out.println("Kamion " + id + " CEKA na policijski terminal.");
//                wait(); // Obavjestava thredove da moraju sacekati
//            }
//            catch(Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        System.out.println("Kamion " + id + " ULAZI na policijski terminal.");
//        isSlobodan = false;
//    }
//
//    @Override
//    public synchronized void zauzimanjeTerminala(String tipVozila, int id) {
//        if(!isSlobodan) // Ako je terminal zauzet
//        {
//            try {
//                System.out.println(tipVozila + " " + id + " CEKA na policijski terminal.");
//                wait(); // Obavjestava thredove da moraju sacekati
//            }
//            catch(Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        System.out.println(tipVozila + " " + id + " ULAZI na policijski terminal.");
//        isSlobodan = false;
//    }


//    @Override
//    public synchronized void napustanjeTerminala(int id) {
//        isSlobodan = true;
//        System.out.println("Kamion " + id + " IZLAZI sa policijskog terminala.");
//        notify(); // Obavjestava jednu od niti koja je u redu za cekanje da je monitor ovog objekta slobodan
//    }
//
//    @Override
//    public synchronized void napustanjeTerminala(String tipVozila, int id) {
//        isSlobodan = true;
//        System.out.println(tipVozila + " " + id + " IZLAZI sa policijskog terminala.");
//        notify(); // Obavjestava jednu od niti koja je u redu za cekanje da je monitor ovog objekta slobodan
//    }
}
