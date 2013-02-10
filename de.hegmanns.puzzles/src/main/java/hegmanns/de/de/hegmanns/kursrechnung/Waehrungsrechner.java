package hegmanns.de.de.hegmanns.kursrechnung;

public class Waehrungsrechner {
	
	private Kursermittler kursermittler;
	
	public Waehrungsrechner(Kursermittler kursermittler){
		this.kursermittler = kursermittler;
	}
	
	public Waehrungsrechner(){
		
	}

	public Geldbetrag convertiere(Geldbetrag geldbetrag, Waehrung zielwaehrung){
		
		Kurs kurs = kursermittler.ermittleKurs(geldbetrag.getWaehrung(), zielwaehrung);
		
		return new Geldbetrag(geldbetrag.getBetrag().multiply(kurs.getMultiplikator()), kurs.getZielwaehrung());
	}

	public Kursermittler getKursermittler() {
		return kursermittler;
	}

	public void setKursermittler(Kursermittler kursermittler) {
		this.kursermittler = kursermittler;
	}
	
	

}
