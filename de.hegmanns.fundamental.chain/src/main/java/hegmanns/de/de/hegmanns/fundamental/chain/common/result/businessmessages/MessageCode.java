package hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages;

public class MessageCode {
	public enum Severity {HINWEIS, WARNUNG, FEHLER};
	private String code;
	private Severity severity;
	
	protected MessageCode(String code, Severity severity){
		this.code = code;
		this.severity = severity;
	}
	
	protected MessageCode(String code){
		this(code, Severity.HINWEIS);
	}

	public String getCode() {
		return code;
	}

	public Severity getSeverity() {
		return severity;
	}
	
	
}
