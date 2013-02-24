package hegmanns.de.order.auftrag.messagecode;

import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;

public class BlockorderMessageCode extends MessageCode {
	
	public static final BlockorderMessageCode
		HINWEIS_BLOCKORDER_AAA = new BlockorderMessageCode("hinweis_blockorder_aaa", Severity.HINWEIS);

	protected BlockorderMessageCode(String code, Severity severity) {
		super(code, severity);
	}

}
