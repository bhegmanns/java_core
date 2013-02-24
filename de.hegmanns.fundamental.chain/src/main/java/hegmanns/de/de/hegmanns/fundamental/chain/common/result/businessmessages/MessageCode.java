package hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages;

public abstract class MessageCode {
	
	public enum Severity{
		HINWEIS("hinweis"), WARNUNG("warnung"), FEHLER("fehler");
		
		String severityCode;
		
		private Severity(String code){
			this.severityCode = code;
		}
		
		public String getSeverityCode(){
			return severityCode;
		}
		
	};
	
	private Severity severity;
	private String code;
	protected MessageCode(String code){
		this(code, analyseSeverity(code));
	}
	
	private static Severity analyseSeverity(String code){
		Severity[] severities = Severity.values();
		Severity severity = null;
		for (Severity currentSeverity : severities){
			if (code.startsWith(currentSeverity.getSeverityCode()))
			{
				severity = currentSeverity;
				break;
			}
		}
		if (severity == null)
		{
			throw new IllegalArgumentException("Kann Code '" + code + "' nicht analysieren!");
		}
		return severity;
	}
	
	protected MessageCode(String code, Severity severity){
		this.code = code;
		this.severity = severity;
	}
	public String getCode(){
		return code;
	}
	public Severity getSeverity(){
		return severity;
	}
}
