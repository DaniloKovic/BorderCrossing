// Dijeljeni resurs.
// Nasljedjuju ga klase PolicijskiTerminal i CarinskiTerminal
public abstract class Terminal {
    protected final Object lock = new Object();

    protected Boolean isSlobodan = true;

    protected String _naziv;
    public String get_naziv() {
        return _naziv;
    }
    public synchronized Boolean getIsSlobodan() {
            return isSlobodan;
    }
    public synchronized void setSlobodan(Boolean slobodan) {
        isSlobodan = slobodan;
    }

    public  Terminal() {
        // TODO Auto-generated constructor stub
    }

    public  Terminal(Boolean isSlobodan) {
        // TODO Auto-generated constructor stub
        this.isSlobodan = isSlobodan;
    }


    public abstract void zauzimanjeTerminala(Vozilo v);
    public abstract Boolean zauzimanjeTerminala2(Vozilo v);

    public abstract void napustanjeTerminala(Vozilo v);
    public abstract void napustanjeTerminala2(Vozilo v);

//    public abstract void zauzimanjeTerminala(int id);
//    public abstract void zauzimanjeTerminala(String tipVozila, int id);
//
//    public abstract void napustanjeTerminala(int id);
//    public abstract void napustanjeTerminala(String tipVozila, int id);

}
