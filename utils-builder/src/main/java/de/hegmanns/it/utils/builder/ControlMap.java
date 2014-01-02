package de.hegmanns.it.utils.builder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 * Verwaltet Zuordnungen, um Werte an einem Member-Attribut setzen zu koennen.
 * 
 * @author B. Hegmanns
 *
 * @param <K>
 * @param <U>
 */
public abstract class ControlMap<K, U> {
	/**
	 * Die Verwaltungs-Map.
	 */
	private Map<K, U> testdatenControlMap;
	
	/**
	 * Uebernimmt das eigentliche Setzen des Member-Attribut.
	 */
	BeanUtilsBean beanUtilsBean = null;
	
	/**
	 * Konstruktor 
	 * @param controlMap die Verwaltungs-Map
	 */
	public ControlMap(Map<K, U> controlMap){
		
		this.testdatenControlMap = controlMap;
		
		ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
		convertUtilsBean.register(new DateConverter(null), Date.class);
		convertUtilsBean.register(new BigDecimalConverter(null), BigDecimal.class);
		
		beanUtilsBean = new BeanUtilsBean(convertUtilsBean);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	protected U getControl(K key)
	{
		return testdatenControlMap.get(key);
	}

	/**
	 * 
	 * @param instanz
	 * @param field
	 * @param value
	 * @return
	 * @throws BuilderException 
	 */
	private <T> boolean fill(T instanz, Field field, Object value) throws BuilderException
	{
		try {
			beanUtilsBean.setProperty(instanz, field.getName(), value);
			return true;
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new BuilderException(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param instanz
	 * @param field
	 * @return
	 * @throws BuilderException
	 */
	public <T> boolean handleField(T instanz, Field field) throws BuilderException{
		U control = getControl(instanz, field);
		
		boolean geaendert = false;
		if (control != null)
		{
			geaendert = fill(instanz, field, createFieldValue(instanz, field, control));
		}
		
		return geaendert;
	}
	
	/**
	 * Gibt das fuer die Wertzuweisung relevante Objekt zurueck. Dabei kann es sich um eine Hilfsklasse (beispielsweise ein Builder oder eine Factory)
	 * oder bereits um den richtigen Wert handeln.
	 * 
	 * @param instanz
	 * @param field
	 * @return
	 */
	protected abstract <T> U getControl(T instanz, Field field);
	
	/**
	 * 
	 * @param instanz
	 * @param field
	 * @param control
	 * @return
	 * @throws BuilderException
	 */
	protected abstract <T> Object createFieldValue(T instanz, Field field, U control) throws BuilderException;
}

/**
 * Verwaltet Attributname-TestdatenBuilder-Zuordnungen.
 * 
 * Hierdurch koennen Komposit-Attribute ueber einen speziellen Builder definiert werden.
 * 
 * @author B. Hegmanns
 */
class TestdatenBuilderFieldnameControlMap extends ControlMap<String, Builder<?>>{
	
	public TestdatenBuilderFieldnameControlMap(Map<String, Builder<?>> map)
	{
		super(map);
	}
	

	@Override
	protected <T> Builder<?> getControl(T instanz, Field field) {
		return super.getControl(field.getName());
	}

	@Override
	protected <T> Object createFieldValue(T instanz, Field field,
			Builder<?> control) throws BuilderException {
		return control.build();
	}
	
}

/**
 * Verwaltet Attributtyp-TestdatenBuilder-Zuordnungen.
 * 
 * Hierdurch koennen Komposit-Attribute ohne weitere Definition automatisch generiert werden.
 * 
 * @author B. Hegmanns
 */
class TestdatenBuilderFieldtypeControlMap extends ControlMap<Class<?>, Builder<?>>{
	
	public TestdatenBuilderFieldtypeControlMap(Map<Class<?>, Builder<?>> map)
	{
		super(map);
	}
	

	@Override
	protected <T> Builder<?> getControl(T instanz, Field field) {
		return super.getControl(field.getType());
	}

	@Override
	protected <T> Object createFieldValue(T instanz, Field field,
			Builder<?> control) throws BuilderException {
		return control.build();
	}
	
}

/**
 * Verwaltet direkte Attributname-Attributvalue-Zuordnungen.
 * 
 * @author B. Hegmanns
 */
class TestdatenFieldvalueControlMap extends ControlMap<String, Object>{

	
	public TestdatenFieldvalueControlMap(Map<String, Object> map)
	{
		super(map);
	}

	@Override
	protected <T> Object getControl(T instanz, Field field) {
		return super.getControl(field.getName());
	}

	@Override
	protected <T> Object createFieldValue(T instanz, Field field, Object control) {
		return control;
	}
	
}
