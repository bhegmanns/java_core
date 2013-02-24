package hegmanns.de.order.auftrag.messagecode;

import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;

public class OrderMessageCode extends MessageCode {
	
	public static final OrderMessageCode
		FEHLER_ORDER_AAA = new OrderMessageCode("fehler_order_aaa", Severity.FEHLER);

	protected OrderMessageCode(String code, Severity severity) {
		super(code, severity);
	}

}
