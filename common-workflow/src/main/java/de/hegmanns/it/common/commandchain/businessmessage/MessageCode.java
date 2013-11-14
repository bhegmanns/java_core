package de.hegmanns.it.common.commandchain.businessmessage;


public class MessageCode {

	private String code;
	private AdditionalProperty<?>[] additionalProperties;
	
	public static final MessageCode ERROR_A = new MessageCode("error_a", new AdditionalProperty<?>[]{AdditionalProperty.create(String.class, "a"), AdditionalProperty.create(String.class, "b", true)});
	public static final MessageCode ERROR_B = new MessageCode("error_b", new AdditionalProperty<?>[]{AdditionalProperty.createOptional(Integer.class, "hoechstwert")});
	
	
	private MessageCode(String code, AdditionalProperty<?>[] additionalProperties) {
		super();
		this.code = code;
		this.additionalProperties = additionalProperties;
	}
	
	private MessageCode(String code){
		this(code, new AdditionalProperty<?>[0]);
	}
	
	
}
