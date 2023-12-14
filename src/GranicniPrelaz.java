import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GranicniPrelaz {

    private static final int BROJ_TERMINALA = 5;
    private static final int BROJ_POLICIJSKIH_TERMINALA = 3;
    private static final int BROJ_CARINSKIH_TERMINALA = 2;

    public List<PolicijskiTerminal> policijskiTerminali = new ArrayList<>();
    public List<CarinskiTerminal> carinskiTerminali = new ArrayList<>();

    public GranicniPrelaz() {
//        policijskiTerminali.add(new PolicijskiTerminal(false));
//        policijskiTerminali.add(new PolicijskiTerminal(false));
//        policijskiTerminali.add(new PolicijskiTerminal(true));
//        carinskiTerminali.add(new CarinskiTerminal(false));
//        carinskiTerminali.add(new CarinskiTerminal(true));
    }

    public List<PolicijskiTerminal> getPolicijskiTerminali() {
        return policijskiTerminali;
    }

    public Optional<PolicijskiTerminal> getPolicijskiTerminalZaKamione() {
        return policijskiTerminali.stream()
                                  .filter(pt -> pt.isZaKamion == true)
                                  .findFirst();

    }
    public Optional<CarinskiTerminal> getCarinskiTerminalZaKamione() {
        return carinskiTerminali.stream()
                                .filter(ct -> ct.isZaKamion == true)
                                .findFirst();

    }

	/*
	public void setPolicijskiTerminali(List<Terminal> policijskiTerminali) {
		this.policijskiTerminali = policijskiTerminali;
	}
	*/

    public List<CarinskiTerminal> getCarinskiTerminali() {
        return carinskiTerminali;
    }

	/*
	public void setCarinskiTerminali(List<Terminal> carinskiTerminali) {
		this.carinskiTerminali = carinskiTerminali;
	}
	*/

}
