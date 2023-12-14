import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/*
Vrijeme procesiranja putnika u ličnom vozilu i kamionu na policijskom/carinskom terminalu je 0.5 sekundi, a u autobusu 0.1 sekundu.
*/
public class Putnik implements Serializable {

    public static Random rand = new Random();

    private Boolean _isVozac = false;
    private Kofer _kofer;
    private Dokument _dokument; // Svaki putnik ima identifikacioni dokument.

    public static final int PROCENAT_PUTNIKA_KOJI_IMAJU_KOFER = 90;

    public Putnik() {
        _dokument = new Dokument();
        _kofer = (rand.nextInt(100) < PROCENAT_PUTNIKA_KOJI_IMAJU_KOFER) ? new Kofer() : null;
    }

    public Putnik(Boolean isVozac)
    {
        _dokument = new Dokument();
        _kofer = (rand.nextInt(100) < PROCENAT_PUTNIKA_KOJI_IMAJU_KOFER) ? new Kofer() : null;
        _isVozac = isVozac;
    }

    public void binarnaSerijalizacijaNeispravnogPutnika()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser")))
        {
            // Serialize and write the object to the file
            oos.writeObject(this);

            System.out.println("Object has been serialized and written to data.ser.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
