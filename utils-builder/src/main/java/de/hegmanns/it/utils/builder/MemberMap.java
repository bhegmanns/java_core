package de.hegmanns.it.utils.builder;

import java.util.HashMap;
import java.util.Map;


public interface MemberMap {

	public <S> S getMember(Class<S> type, String name);
	
	@SuppressWarnings("rawtypes")
	public Map getMemberMap();
}

class MemberMapImpl implements MemberMap{
	
	private Map<String, Object> definitionMap = new HashMap<String, Object>();
	
	public MemberMapImpl(Map<String, Object> definitionen)
	{
		this.definitionMap = definitionen;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <S> S getMember(Class<S> type, String name) {
		return (S) definitionMap.get(name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map getMemberMap() {
		return definitionMap;
	}
	
}
