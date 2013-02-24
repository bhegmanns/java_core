package hegmanns.de.de.hegmanns.fundamental.chain.order.messagecodes;

import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;

public class OrderMessageCodes extends MessageCode{

	protected OrderMessageCodes(String code) {
		super(code);
	}

	public OrderMessageCodes(String code, Severity severity) {
		super(code, severity);
	}
	
	public static final OrderMessageCodes FEHLER_BLA_01 = new OrderMessageCodes("fehler-bla-01");
	public static final OrderMessageCodes HINWEIS_BLA_01 = new OrderMessageCodes("hinweis_bla_01");
	public static final OrderMessageCodes WARNUNG_BLA_01 = new OrderMessageCodes("warnung_bla_01");
	
}
