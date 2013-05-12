package hegmanns.it.de.java6.corelanguage.exceptions;

import hegmanns.it.de.java6.corelanguage.terror.Anheftbar;
import hegmanns.it.de.java6.corelanguage.terror.Auto;
import hegmanns.it.de.java6.corelanguage.terror.Autobombe;
import hegmanns.it.de.java6.corelanguage.terror.KnallPeng;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Bernd
 *
 */
public class RessourcenbereinigungInTryCatch {
	private static final Logger LOG = Logger.getLogger("java6");
	
	@Test
	public void ressourcenbereinigungAltOhneException(){
		Auto auto = null;
		AutoAusObserver autoausObserver = null;
		try{
			auto = new Auto();
			autoausObserver = new AutoAusObserver(auto);
			Autobombe autobombe = new Autobombe();
			autobombe.anheften(auto);
			
		}catch(KnallPeng knallPeng)
		{
			Assert.fail("Die Bombe wurde gar nicht gezuendet, daher darf es nicht zu einer KnallPeng-Exception kommen!");
		}
		finally{
			try {
				auto.close();
			} catch (Exception e) {
				// nothing to do
			}
			
		}
		
		MatcherAssert.assertThat("", autoausObserver.isAutoAus(), Matchers.is(true));
	}
	
	@Test
	public void ressourcenbereinigungAltMitException(){
		Auto auto = null;
		AutoAusObserver autoausObserver = null;
		try{
			auto = new Auto();
			autoausObserver = new AutoAusObserver(auto);
			Autobombe autobombe = new Autobombe();
			autobombe.anheften(auto);
			
			autobombe.zuenden();
			
			Assert.fail("Nach dem Zuenden muss es zu einer KnallPeng-Exception kommen!!!");
		}catch(KnallPeng knallPeng)
		{
			LOG.info("Es ist zum Fehler gekommen.");
		}
		finally{
			try {
				auto.close();
				Assert.fail("close() muss eine Exception verursachen, weil das Auto durch Explosion zerstoert wurde!");
			} catch (Exception e) {
				// nothing to do
			}
			
		}
		
		MatcherAssert.assertThat("", autoausObserver.isAutoAus(), Matchers.is(false));
	}
	
	@Test
	public void ressourcenBereinigungJava7OhneException() throws Exception
	{
		AutoAusObserver autoausObserver = null;
		try(Auto auto = new Auto()){

			autoausObserver = new AutoAusObserver(auto);
			Autobombe autobombe = new Autobombe();
			autobombe.anheften(auto);

		}catch(KnallPeng knallPeng)
		{
			Assert.fail("Die Bombe wurde gar nicht gezuendet, daher darf es nicht zu einer KnallPeng-Exception kommen!");
		}
		
		MatcherAssert.assertThat("", autoausObserver.isAutoAus(), Matchers.is(true));
	}
	
	/**
	 * Wo ist hier nun der Unterschied???
	 * 
	 * Hier befindet sich im try-Statement eine Anweisung, die eine 
	 * Ressource definiert.
	 * Hier duerfen nun Ressourcen erstellt werden, die das 
	 * @throws Exception
	 */
	@Test
	public void ressourcenBereinigungJava7MitException() throws Exception
	{
		AutoAusObserver autoausObserver = null;
		try(Auto auto = new Auto()){

			autoausObserver = new AutoAusObserver(auto);
			Autobombe autobombe = new Autobombe();
			autobombe.anheften(auto);
			
			autobombe.zuenden();
			Assert.fail("Nach dem Zuenden muss es zu einer KnallPeng-Exception kommen!!!");
		}catch(KnallPeng knallPeng)
		{
			LOG.info("close() muss eine Exception verursachen, weil das Auto durch Explosion zerstoert wurde!");
		}
		
		MatcherAssert.assertThat("", autoausObserver.isAutoAus(), Matchers.is(false));
	}

	
	
	
	
	@Test
	public void unterdrueckteExceptions()
	{
		try
		{
			autobombeOeffentlich();
		}catch(Exception e){
			MatcherAssert.assertThat(e, Matchers.instanceOf(KnallPeng.class));
			MatcherAssert.assertThat(e.getSuppressed(), Matchers.arrayWithSize(1));
			MatcherAssert.assertThat(e.getSuppressed()[0], Matchers.instanceOf(Error.class));
			MatcherAssert.assertThat(e.getSuppressed()[0].getMessage(), Matchers.equalTo("Auto ist zerstoert"));
		}
		
		
		try{
			autobombeGeheim();
		}catch(Exception e){
			MatcherAssert.assertThat(e, Matchers.instanceOf(Error.class));
			MatcherAssert.assertThat(e.getMessage(), Matchers.equalTo("Auto ist zerstoert"));
		}
	}
		
		
	/**
	 * Aus dieser Methode kommen eigentlich 2 Exceptions.
	 * Einmal das Zuenden der Autobombe und dann beim
	 * AutoClode vom Auto.
	 * Da die Methode aber nur einen Fehler werfen kann,
	 * wird die Exception durch das Autoclose unterdrueckt
	 * und dem Fehler als SuppressedException mit gereicht.
	 * 
	 * @throws Exception
	 */
	private void autobombeOeffentlich() throws Exception
	{
		try(Auto auto = new Auto())
		{
			Autobombe autobombe = new Autobombe();
			autobombe.anheften(auto);
			
			autobombe.zuenden();
		} 
	}
	
	/**
	 * Hier wird eine Exception abgefangen, die durch die Autobombe
	 * auftritt.
	 * Das schliessen des Autos verursacht hier dann den original-Fehler.
	 * @throws Exception
	 */
	private void autobombeGeheim() throws Exception
	{
		try(Auto auto = new Auto())
		{
			Autobombe autobombe = new Autobombe();
			autobombe.anheften(auto);
			
			autobombe.zuenden();
		} catch(KnallPeng e)
		{
			// Auto explodiert ...
		}
	}
}
