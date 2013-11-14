package de.hegmanns.it.common.commandchain.businessmessage;

import java.util.HashMap;
import java.util.Map;

public class BusinessMessage {

	private Severity severity;
	
	private MessageCode messageCode;
	
	private Map<AdditionalProperty<?>, Object> properties = new HashMap<AdditionalProperty<?>, Object>();
	

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public MessageCode getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(MessageCode messageCode) {
		this.messageCode = messageCode;
	}

	public Map<AdditionalProperty<?>, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<AdditionalProperty<?>, Object> properties) {
		this.properties = properties;
	}
	
	
	
}
