package de.hegmanns.it.common.commandchain.businessmessage;

import java.util.ArrayList;
import java.util.List;

public class BusinessMessages {

	private List<BusinessMessage> businessMessages = new ArrayList<>();
	
	public void add(BusinessMessage businessMessage)
	{
		businessMessages.add(businessMessage);
	}
	
	public List<BusinessMessage> get(Severity severity)
	{
		return null;
	}
	
	public boolean isOk(){
		return false;
	}
}
