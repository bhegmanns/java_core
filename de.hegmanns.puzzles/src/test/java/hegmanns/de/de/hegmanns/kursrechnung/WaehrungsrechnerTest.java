package hegmanns.de.de.hegmanns.kursrechnung;

import java.math.BigDecimal;

import hegmanns.de.de.hegmanns.fundamental.core.Ref;
import hegmanns.de.de.hegmanns.kursrechnung.Geldbetrag;
import hegmanns.de.de.hegmanns.kursrechnung.Kurs;
import hegmanns.de.de.hegmanns.kursrechnung.Waehrung;
import hegmanns.de.de.hegmanns.kursrechnung.Waehrungsrechner;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


@RunWith(MockitoJUnitRunner.class)
public class WaehrungsrechnerTest {
	
	@InjectMocks
	Waehrungsrechner waehrungsrechner;
	
	@Mock(answer = Answers.CALLS_REAL_METHODS)
	MockitoKursermittlerFromAbstractKursermittler kursermittler;

	
	@Test
	public void einErsterTest(){
		StringBuffer sb = new StringBuffer();
		sb.append("Hier").append(" ist").append(" Joe.");
		String s = "Hier ist Joe.";
		
		MatcherAssert.assertThat(sb.toString(), Matchers.is(s));
		MatcherAssert.assertThat(sb.toString(), Matchers.not(Matchers.sameInstance(s)));
	}
	
	@Test
	public void einZweiterTest(){
		Kurs kurs = new Kurs();
		kurs.setAusgangswaehrung(new Waehrung());
		kurs.setZielwaehrung(new Waehrung());
		kurs.setMultiplikator(BigDecimal.TEN);
		Mockito.when(kursermittler.ermittleKurs(Mockito.any(Waehrung.class), Mockito.any(Waehrung.class)))
		.thenReturn(kurs);
		
		Geldbetrag betrag = waehrungsrechner.convertiere(new Geldbetrag(BigDecimal.ONE, new Waehrung()), new Waehrung());
		MatcherAssert.assertThat(betrag, Matchers.notNullValue());
		MatcherAssert.assertThat(betrag.getBetrag(), Matchers.is(BigDecimal.TEN));
		MatcherAssert.assertThat(betrag, Matchers.is(new Geldbetrag(BigDecimal.TEN, new Waehrung())));
		
	}
	
	@Test
	public void waehrungsrechnerGibtRichtigeWaehrungZurueck()
	{
		Waehrung ausgangswaehrung = new Waehrung("AAA");
		Waehrung zielwaehrung     = new Waehrung("ZZZ");
		BigDecimal multiplikator = BigDecimal.TEN;
		Kurs kurs = new Kurs();
		kurs.setAusgangswaehrung(ausgangswaehrung);
		kurs.setZielwaehrung(zielwaehrung);
		kurs.setMultiplikator(multiplikator);
		
		Mockito.when(kursermittler.ermittleKurs(Mockito.same(ausgangswaehrung), Mockito.same(zielwaehrung))).thenReturn(kurs);
		
		Geldbetrag betrag = waehrungsrechner.convertiere(new Geldbetrag(BigDecimal.ONE, ausgangswaehrung), zielwaehrung);
		MatcherAssert.assertThat(betrag, Matchers.is(new Geldbetrag(BigDecimal.TEN, zielwaehrung)));
	}
	
	@Test
	public void waehrungsrechnerGibtRichtigeWaehrungZurueckDirekteInstanzen()
	{
		Waehrung ausgangswaehrung = new Waehrung("AAA");
		Waehrung zielwaehrung     = new Waehrung("ZZZ");
		BigDecimal multiplikator = BigDecimal.TEN;
		Kurs kurs = new Kurs();
		kurs.setAusgangswaehrung(ausgangswaehrung);
		kurs.setZielwaehrung(zielwaehrung);
		kurs.setMultiplikator(multiplikator);
		
		Mockito.when(kursermittler.ermittleKurs(ausgangswaehrung, zielwaehrung)).thenReturn(kurs);
		
		Geldbetrag betrag = waehrungsrechner.convertiere(new Geldbetrag(BigDecimal.ONE, ausgangswaehrung), zielwaehrung);
		MatcherAssert.assertThat(betrag, Matchers.is(new Geldbetrag(BigDecimal.TEN, zielwaehrung)));
	}
	
	
	
	
	
	@Test
	public void waehrungsrechnerMitDBGenauigkeit(){
		final Waehrung ausgangswaehrung = new Waehrung("AAA");
		final Waehrung zielwaehrung     = new Waehrung("ZZZ");
		final BigDecimal multiplikator = BigDecimal.TEN;
		Kurs kurs = new Kurs();
		kurs.setAusgangswaehrung(ausgangswaehrung);
		kurs.setZielwaehrung(zielwaehrung);
		kurs.setMultiplikator(multiplikator);
		
		Ref<Kursermittlungsqualitaet> referenz = new Ref<Kursermittlungsqualitaet>(new Kursermittlungsqualitaet(Kursqualitaet.REALTIME));
		
		Mockito.when(kursermittler.ermittleKurs(ausgangswaehrung, zielwaehrung, new Ref<Kursermittlungsqualitaet>(new Kursermittlungsqualitaet(Kursqualitaet.REALTIME)))).thenAnswer(new Answer<Kurs>() {

			public Kurs answer(InvocationOnMock invocation) throws Throwable {
				Ref<Kursermittlungsqualitaet> ref = (Ref<Kursermittlungsqualitaet>)invocation.getArguments()[2];
				ref.get().setMoeglicheMindestQualitaet(Kursqualitaet.DATENBANK);
				return null;
			}
		});
		
		Mockito.when(kursermittler.ermittleKurs(ausgangswaehrung, zielwaehrung, new Ref<Kursermittlungsqualitaet>(new Kursermittlungsqualitaet(Kursqualitaet.DATENBANK)))).thenAnswer(new Answer<Kurs>() {

			public Kurs answer(InvocationOnMock invocation) throws Throwable {
				Ref<Kursermittlungsqualitaet> ref = (Ref<Kursermittlungsqualitaet>)invocation.getArguments()[2];
				ref.get().setMoeglicheMindestQualitaet(Kursqualitaet.DATENBANK);
				
				Kurs kurs = new Kurs();
				kurs.setAusgangswaehrung(ausgangswaehrung);
				kurs.setZielwaehrung(zielwaehrung);
				kurs.setMultiplikator(multiplikator);
				kurs.setKursqualitaet(Kursqualitaet.DATENBANK);
				return kurs;
			}
		});
		
		Geldbetrag betrag = waehrungsrechner.convertiere(new Geldbetrag(BigDecimal.ONE, ausgangswaehrung), zielwaehrung);
		MatcherAssert.assertThat(betrag, Matchers.is(new Geldbetrag(BigDecimal.TEN, zielwaehrung)));
	}

}


class MockitoKursermittlerFromAbstractKursermittler extends AbstractKursermittler
{

	@Override
	public Kurs ermittleKurs(Waehrung ausgangswaehrung, Waehrung zielwaehrung,
			Ref<Kursermittlungsqualitaet> kursermittlungsqualitaet) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
