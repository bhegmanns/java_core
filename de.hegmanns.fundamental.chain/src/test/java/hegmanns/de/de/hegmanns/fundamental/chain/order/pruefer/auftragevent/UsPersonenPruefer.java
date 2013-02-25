package hegmanns.de.de.hegmanns.fundamental.chain.order.pruefer.auftragevent;

import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;
import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCodeCmdResult;
import hegmanns.de.de.hegmanns.fundamental.chain.order.Auftragevent;
import hegmanns.de.de.hegmanns.fundamental.chain.order.messagecodes.OrderMessageCodes;
import de.hegmanns.fundamental.chain.Cmd;

public class UsPersonenPruefer implements Cmd<Auftragevent, AuftrageventCmdCtx, MessageCode, MessageCodeCmdResult>{

	@Override
	public void execute(AuftrageventCmdCtx context, MessageCodeCmdResult result) {
		
		Auftragevent ae = context.getInstanz();
		result.setExecuted(true);
		result.add(OrderMessageCodes.FEHLER_BLA_01);
		
	}

}
