package de.hegmanns.it.utils.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Basisklasse fuer Builder.
 * 
 * In den Implementierungsklassen koennen ueber die Annotation {@link MemberDefault} Defaultwerte fuer die Attribute des zu erzeugenden
 * Objekts gesetzt werden.
 * 
 * Fuer Komposite kann auch ein automatischer Build-Prozess erstellt werden, in dem Builder-Subklassen in die {@link BuilderRegistry}
 * registriert werden. Hierzu bei Bedarf einfach eine static-Variable in die jeweilige Subklasse aufnehmen.
 * 
 * @author B. Hegmanns
 *
 * @param <T> der Typ des zu erzeugenden Objekts
 */
public abstract class Builder<T> {
	private static Logger LOG = LoggerFactory.getLogger(Builder.class);

	/**
	 * Der Typ der durch diesen Builder erzeugten Bean
	 */
	private Class<T> buildType;
	
	/**
	 * Die Member-Definitionen.
	 * key: Attributname
	 * Gemaess Bean-Spezifikation gibt es hier die entsprechenden Getter/Setter.
	 * 
	 * value: Attribut-Wert
	 */
	private Map<String, Object> memberDefinitionen = new HashMap<String, Object>();
	
	/**
	 * Waehrend des Build-Prozesses von der Sub-Klasse eingetragene TestdatenBuilder fuer
	 * ein konkretes Attribut.
	 * 
	 * key: Attributname
	 * 
	 * value: Builder fuer das Attribut
	 */
	private Map<String, Builder<?>> testdatenbuilderForMember = new HashMap<>();
	
	/**
	 * Vordefinierte Definitionen der Attribute. Wird ueber die Annotation {@link MemberDefault} eingelesen.
	 */
	private static Map<String, Object> predefinedDefinitions = null;
	
	
	
	/**
	 * Die im Rahmen den Build-Vorgangs bereits zugewiesenen Attribute.
	 * Verhindert einerseits eine Neu-Zuweisung und ermoeglicht die Zuweisung
	 * bei basic-Attributtypen.
	 */
	private Set<String> memberZuweisungen = new HashSet<>();
	
	
	/**
	 * Konstruktor, nimmt den Typ der durch diesen Builder zu erstellenden Objekte
	 * auf und registriert diesen Builder bei der BuilderRegistry.
	 * 
	 * @param buildType Typ der durch diesen Builder erstellten Objekte
	 */
	protected Builder(Class<T> buildType){
		this.buildType = buildType;
		BuilderRegistry.register(buildType, this);
	}
	
	/**
	 * Fuegt eine Feld-Definition gemaess der Bean-Spezifikation dem Builder hinzu.
	 * 
	 * Ein eventuell bereits vorhandener Wert wird ueberschrieben. Es wird ein Warn-Log erstellt.
	 * Ein Warn-Log wird auch erstellt, wenn der Feld-Value durch einen anderen Typ getauscht wird.
	 * 
	 * @param menbername der Attributname (gemaess Bean-Spezifikation)
	 * @param memberValue der vorgesehene Wert
	 * 
	 * @param <U> 
	 */
	protected <U> void put(String menbername, U memberValue)
	{
		Object currentMemberValue = memberDefinitionen.get(menbername);
		
		if (currentMemberValue != null)
		{
			if (!currentMemberValue.getClass().equals(memberValue.getClass()))
			{
				LOG.warn("Fuer membername '" + menbername + "' sind alter MemberValue '" + currentMemberValue + "' und neu hinzuzufuegender MemberValue '" + memberValue + "' nicht vom gleichen Typ.");
			}
			else
			{
				LOG.warn("Fuer membername '" + menbername + "' existiert bereits der Value '" + currentMemberValue + "', wird nun getauscht gegen '" + memberValue + "'");
			}
		}
		
		memberDefinitionen.put(menbername, memberValue);
	}
	
	/**
	 * Fuegt eine Feld-Definition ueber einen TestdatenBuilder hinzu.
	 * 
	 * Ein eventuell bereits vorhandener Wert wird ueberschrieben. Es wird ein Warn-Log erstellt.
	 * 
	 * @param membername der Attributname
	 * @param testdatenbuilder der fuer das Attribut vorgesehene TestdatenBuilder
	 */
	protected <U> void put(String membername, Builder<U> testdatenbuilder)
	{
		Builder<?> currentTestdatenbuilder = testdatenbuilderForMember.get(membername);
		if (currentTestdatenbuilder != null)
		{
			LOG.warn("Fuer membername '" + membername + "' existiert bereits ein Testdatenbuilder. Wird nun ausgetauscht.");
		}
		
		testdatenbuilderForMember.put(membername, testdatenbuilder);
	}
	
	
	protected Map<String, Object> getMemberMap()
	{
		return memberDefinitionen;
	}
	
	@SuppressWarnings("unchecked")
	protected <S> S getMember(Class<S> type, String membername){
		return (S)memberDefinitionen.get(membername);
	}
	
	/**
	 * Erzeugt das Objekt.
	 * 
	 * @return das Objekt
	 * @throws BuilderException bei Auftritt einer Build-Fehlers
	 */
	public final T build() throws BuilderException{
		T instanz = null;
		try{
			instanz = erzeugeInstanz();
			
			List<Field> felder = addIntoFields(new ArrayList<Field>(), instanz.getClass());
			
			// (1) die richtigen Definitionen ueber den Builder-Aufruf
			fillEmptyValues(instanz, felder, new TestdatenFieldvalueControlMap(memberDefinitionen));
			
			// (2) ueber ggf. definierte Builder, dieser ueberschreibt ggf. vorhandene Werte
			fillEmptyValues(instanz, felder, new TestdatenBuilderFieldnameControlMap(this.testdatenbuilderForMember));
			
			// (3) die Default-Werte
			// (3a) Vordefinitionen lesen
			predefinedDefinitions = FieldAnnotationMapper.read(this, MemberDefault.class);
			// (3b)
			fillEmptyValues(instanz, felder, new TestdatenFieldvalueControlMap(predefinedDefinitions));
			
			// (4) 
			fillEmptyValues(instanz, felder, new TestdatenBuilderFieldtypeControlMap(BuilderRegistry.getRegisteredTestdatenBuilderMap()));
		}catch(Exception e){
			LOG.error("Instanz kann nicht erstellt werden.", e);
			throw new BuilderException(e.getMessage(), e);
		}
		
		return instanz;
	};
	
	private T erzeugeInstanz() throws InstantiationException, IllegalAccessException
	{
		return buildType.newInstance();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void fillEmptyValues(T instanz, Iterable<Field> fieldIterable, ControlMap testdatenbuildermap) throws BuilderException
	{
		
		for (Field field : fieldIterable){
			if (!memberZuweisungen.contains(field.getName()))
			{
				if (testdatenbuildermap.handleField(instanz, field)){
				memberZuweisungen.add(field.getName());
				}
			}
		}
	}
	
	private <P> List<Field> addIntoFields(List<Field> fieldlist, Class<P> yourClass)
	{
		fieldlist.addAll(Arrays.asList(yourClass.getDeclaredFields()));
		if (!yourClass.getSuperclass().equals(Object.class))
		{
			fieldlist = addIntoFields(fieldlist, yourClass.getSuperclass());
		}
		
		return fieldlist;
	}
}
