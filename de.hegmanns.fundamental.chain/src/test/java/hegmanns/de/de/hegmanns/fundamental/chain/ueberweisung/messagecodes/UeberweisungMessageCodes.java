package hegmanns.de.de.hegmanns.fundamental.chain.ueberweisung.messagecodes;

import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;

public class UeberweisungMessageCodes extends MessageCode {

	protected UeberweisungMessageCodes(String code, Severity severity) {
		super(code, severity);
		// TODO Auto-generated constructor stub
	}

	private UeberweisungMessageCodes(String code) {
		super(code);
		// TODO Auto-generated constructor stub
	}
	
	public static final UeberweisungMessageCodes
		HINWEIS_BLA_01 = new UeberweisungMessageCodes("hinweis_bla_01");
	public static final UeberweisungMessageCodes
		FEHLER_BLA_01 = new UeberweisungMessageCodes("fehler_bla_01");
	public static final UeberweisungMessageCodes
		WARNUNG_BLA_01 = new UeberweisungMessageCodes("warnung-bla-01");

}
