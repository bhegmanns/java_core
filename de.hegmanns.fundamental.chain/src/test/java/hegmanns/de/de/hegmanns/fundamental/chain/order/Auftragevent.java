package hegmanns.de.de.hegmanns.fundamental.chain.order;

import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;
import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCodeCmdResult;
import hegmanns.de.de.hegmanns.fundamental.chain.order.pruefer.auftragevent.AuftrageventCmdCtx;
import hegmanns.de.de.hegmanns.fundamental.chain.order.pruefer.auftragevent.UsPersonenPruefer;
import de.hegmanns.fundamental.chain.CmdChain;

public class Auftragevent {

	
	public void pruefeMich()
	{
		CmdChain<Auftragevent, AuftrageventCmdCtx, MessageCode, MessageCodeCmdResult>
		 myChain = new CmdChain<>();
		 
		myChain.add(new UsPersonenPruefer());
		myChain.processChain(this);
	}
}
