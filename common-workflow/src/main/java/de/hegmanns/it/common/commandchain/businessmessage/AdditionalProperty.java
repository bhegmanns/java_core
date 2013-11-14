package de.hegmanns.it.common.commandchain.businessmessage;

public class AdditionalProperty<T> {

	private String name;
	private Class<T> type;
	private boolean required;
	
	public static <T> AdditionalProperty<T> create(Class<T> type, String name)
	{
		return create(type, name, false);
	}
	
	public static <T> AdditionalProperty<T> createOptional(Class<T> type, String name)
	{
		return create(type, name, true);
	}
	
	public static <T> AdditionalProperty<T> create(Class<T> type, String name, boolean optional)
	{
		AdditionalProperty<T> property = new AdditionalProperty<>(name, type, !optional);
		return property;
	}
	
	public AdditionalProperty(String name, Class<T> type, boolean required) {
		super();
		this.name = name;
		this.type = type;
		this.required = required;
	}

	public String getName() {
		return name;
	}

	public Class<T> getType() {
		return type;
	}

	public boolean isRequired() {
		return required;
	}
	
	
	
	
}
