import java.io.Serializable;
import java.util.Random;

public class Kofer implements Serializable{

    public static final int PROCENAT_ISPRAVNIH_KOFERA = 90;
    public static Random rand = new Random();

    private Boolean isKoferValid;
    private Object[] stvari;

    public Kofer() {
        super();
        isKoferValid = (rand.nextInt(100) < PROCENAT_ISPRAVNIH_KOFERA) ? true : false;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

}
