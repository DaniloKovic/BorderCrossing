import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static int BROJ_LICNIH_VOZILA = 35;
    public static int BROJ_AUTOBUSA = 5;
    public static int BROJ_KAMIONA = 10;

    public static PolicijskiTerminal ptZaKamion = new PolicijskiTerminal(true, "PTK");
    public static PolicijskiTerminal pt1 = new PolicijskiTerminal(false, "PT1");
    public static PolicijskiTerminal pt2 = new PolicijskiTerminal(false, "PT2");
    public static CarinskiTerminal ctZaKamion = new CarinskiTerminal(true,"CTK");
    public static CarinskiTerminal ct1 = new CarinskiTerminal(false, "CT1");



    public static void main(String[] args) throws Exception {

        List<Terminal> terminali = new ArrayList<Terminal>(5);

        terminali.add(Main.ptZaKamion);
        terminali.add(pt1);
        terminali.add(pt2);

        terminali.add(Main.ctZaKamion);
        terminali.add(ct1);

        ArrayList<Vozilo> vozila = new ArrayList<>(Main.BROJ_KAMIONA);
        for (int i=0; i < BROJ_KAMIONA; i++) {
            vozila.add(new Kamion());
        }
        for (int i=0; i < BROJ_AUTOBUSA; i++) {
            vozila.add(new Autobus());
        }
        for (int i=0; i < BROJ_LICNIH_VOZILA - 25; i++) {
            vozila.add(new LicnoVozilo());
        }
        Collections.shuffle(vozila);

        for (Vozilo vozilo : vozila)
        {
            vozilo.start();
        }

        /*
        for (Vozilo vozilo : vozila)
        {
            if (vozilo instanceof Kamion k) {
                k.start();
            }
            else if(vozilo instanceof Autobus ab){
                ab.start();
            }
            else if (vozilo instanceof Autobus || vozilo instanceof LicnoVozilo)
            {

            }
        }*/
    }
}

/*
•	Vozila mogu da budu: lična vozila sa do 5 putnika (uključujući vozača), autobusi sa kapacitetom do 52 putnika (uključujući vozača),
    te kamioni sa kapacitetom do 3 putnika (uključujući vozača).
    Svaki putnik ima identifikacioni dokument. Svako vozilo ima slučajno generisan broj putnika, ali minimalno jednog putnika koji je u tom slučaju vozač.
    Vrijeme procesiranja putnika u ličnom vozilu i kamionu na policijskom/carinskom terminalu je 0.5 sekundi, a u autobusu 0.1 sekundu.
    Od svih dokumenata putnika, 3% je neispravno i isti ne mogu preći granicu.
•	Kamioni imaju teret za koji je možda potrebno generisati carinsku dokumentaciju (vjerovatnoća da je potrebno generisati dokumentacija je 50%), kao i deklarisanu i stvarnu masu tereta (osmisliti algoritam tako da u 20% kamiona stvarna masa tereta bude do 30% veća od deklarisane).
•	Autobusi imaju teretni prostor u kojem se potencijalno nalazi kofer putnika (vjerovatnoća da putnik ima kofer je 70%, pri čemu u 10% kofera su nedozvoljene stvari).
•	Svaki putnik ima identifikacioni dokument. Svako vozilo ima slučajno generisan broj putnika, ali minimalno jednog putnika koji je u tom slučaju vozač.
•	Na početku simulacije kreira se red u kojem je 50 vozila i pri tome 5 autobusa, 10 kamiona i 35 ličnih vozila. Sva vozila se postavljaju na slučajno generisanu poziciju u redu.
•	Iz zajedničkog reda moguće je paralelno procesiranje vozila na tri policijska terminala. Pri tom su jedan policijski i carinski terminal (terminali koji će na gafičkom prikazu biti skroz desno) uvijek rezervisani samo za kamione.
    Na preostala dva terminala se paralelno procesiraju autobusi i vozila, te se nakon toga nastavlja procesiranje na jednom zajedničkom carinskom terminalu.
    Ukoliko se procesira vozilo na carinskom terminalu, a i na policijskim terminalima su vozila procesirana, ista čekaju u redu za procesiranje na carinskom temrinalu i dalje stoje na policijskim terminalima dok se carinski terminal ne oslobodi.
•	Vrijeme procesiranja putnika u ličnom vozilu i kamionu na policijskom/carinskom terminalu je 0.5 sekundi, a u autobusu 0.1 sekundu.
    Od svih dokumenata putnika, 3% je neispravno i isti ne mogu preći granicu.
    U tom slučaju se u posebnu evidenciju upisuju i binarno serijalizuju podaci u listi kažnjenih osoba.
    Evidentiranje ovog tipa se vrši prilikom policijske provjere.
    Vozila nastavljaju dalje procesiranje bez datog putnika ili putnika samo u slučaju ako putnik nije vozač.
    Ako je u pitanju vozač, vozilo se izbacuje iz kolone sa svim putnicima i ne može preći granicu.
    U carinskom dijelu lična vozila se zadržavaju ukupno 2 sekunde bez dodatnih kontrola, autobusi se standardno procesiraju po putnuku i provjerava se kofer putnika.
    Ukoliko kofer sadrži nedozvoljene stvari, putnik se izbacuje iz autobusa.
    Za kamione se kreira carinska dokumentacija ukoliko je to potrebno, te se isti testiraju na težinu tereta.
    Ukoliko je kamion preopterećen isti ne može da pređe granicu.
    Vijeme procesiranja kamiona na carinskom terminalu je 0.5 sekundi
•	Vijeme procesiranja kamiona na carinskom terminalu je 0.5 sekundi
•	U oba prethodna slučaja neprelaska na carini formira se evidencija u tekstualnom obliku.
    Svi carinski i policijski terminali u jednom zajedničkom fajlu imaju informaciju o tome da li trenutno mogu da rade.
•	Osmisliti i implementirati rješenje gdje se nakon izmjene u datom zajedničkom fajlu za određeni policijski i li carinski terminal vrši blokada ili ponovno puštanje u rad terminala (pod blokadom se smatra da na datom terminalu trenutno nije moguće procesiranje vozila/putnika).
•	Iz početnog reda potrebno je prikazati narednih pet vozila koja su u redu za prelazak granice.
    Ostala vozila koja čekaju u redu potrebno je prikazati u odvojenom korisničkom interfejsu.
    Sva vozila koja su imala incident moguće je prikazati u odvojenom korisničkom interfejsu sa tačnim podacima šta se zapravo desilo.
    Osmisliti i implementirati rješenje za predstavljanje vozila u sklopu tog korisničkog interfejsa koja obuhvataju vozila koja nisu prešla granicu ivozila koja jesu prešla granicu, ali su imali problem sa nekim od putnika.
•	Simulaciju je potrebno prikazati grafički, tako da se vide svi relevantni događaji. Tipove vozila označiti posebnim bojama (ili ikonicama).
•	Potrebno je i ispisivati sve događaje koji se dešavaju na graničnom prelazu. TipDogadjajaEnum
•	Za svako vozilo potrebno je biti u mogućnosti prikazati sve podatke o vozilu i putnicima.
    Osmisliti način prikaza podataka (npr. klikom na vozilo se negdje ispisuju podaci, ili unosom id-a vozila i slično).
    Logika kretanja vozila potrebno je da bude implementirana u vozilima, a ne odvojeno od istih.
    Simulacija može da se zaustavi i pokrene ponovo.
    Vrijeme trajanja simulacije je prikazanona ekranu.
•	Obavezno koristiti Logger klasu za obradu izuzetaka u svim klasama. Logger
•	Između terminala nema dodatnih polja.
    Prelazak sa polja na polje najbolje je implementirati tako što se dato polje popuni ikonom/bojom/oznakom vozila koje je trenutno na istom.
•	Na kraju simulacije postoje samo dva fajla (binarna i tekstualna evidencija) koja su nastala na osnovu te simulacije.
    Svaki novi start simulacije kreira nova dva fajla. Za jedinstveni naziv fajlova najbolje je u naziv staviti vrijeme početka simulacije.

Primjer grafičkog dijela prikaza graničnog prelaza:
V - automobil
A - autobus
K - kamion
P1, P2 - policijski terminal za automobile i autobuse
PK - policijski terminal za kamione
C1 - carinski terminal za automobile i autobuse
CK - carinski terminal za kamione
Napomena: Prelaskom na naredno polje (kolona ili terminal) u isto će biti upisana oznaka
vozila koje se trenutno nalazi na tom polju.

* */