import java.io.Serializable;
import java.util.Random;

// Od svih dokumenata putnika, 3% je neispravno i isti ne mogu preći granicu.
public class Dokument implements Serializable {

    private static final int PROCENAT_ISPRAVNIH_DOKUMENATA = 97;

    public static Random rand = new Random();

    public int dokumentID;
    public boolean isIspravan;

    public Dokument()
    {
        isIspravan = (rand.nextInt(100) < PROCENAT_ISPRAVNIH_DOKUMENATA) ? true : false;
    }

}
