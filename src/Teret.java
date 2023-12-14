import java.util.Random;

public class Teret {

    private static Random rand = new Random();

    private Boolean hasDokumentacija = false;
    public double deklarisanaMasa;
    public double stvarnaMasa;

    public Teret()
    {
        this.hasDokumentacija = rand.nextBoolean();
    }

    public Teret(double stvarnaM, double deklarisanaM)
    {
        this.hasDokumentacija = rand.nextBoolean();
        this.stvarnaMasa = stvarnaM;
        this.deklarisanaMasa = deklarisanaM;
    }

}
