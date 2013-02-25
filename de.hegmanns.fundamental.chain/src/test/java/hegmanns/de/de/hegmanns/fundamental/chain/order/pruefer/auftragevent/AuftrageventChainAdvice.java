package hegmanns.de.de.hegmanns.fundamental.chain.order.pruefer.auftragevent;

import java.util.List;

import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode;
import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCodeCmdResult;
import hegmanns.de.de.hegmanns.fundamental.chain.common.result.businessmessages.MessageCode.Severity;
import hegmanns.de.de.hegmanns.fundamental.chain.order.Auftragevent;
import de.hegmanns.fundamental.chain.ChainAdvice;

public class AuftrageventChainAdvice 
	implements ChainAdvice<Auftragevent, AuftrageventCmdCtx, MessageCode, MessageCodeCmdResult>{

	@Override
	public AuftrageventCmdCtx createCmdContext(Auftragevent instanz) {
		return new AuftrageventCmdCtx(instanz);
	}

	@Override
	public MessageCodeCmdResult createCmdResult(Auftragevent instanz) {
		return new MessageCodeCmdResult();
	}

	@Override
	public boolean canContinue(MessageCodeCmdResult currentResult) {
		boolean canContinue = true;
		for (MessageCode messagecode : currentResult.getResults())
		{
			if (messagecode.getSeverity().equals(Severity.FEHLER)){
				canContinue = false;
				break;
			}
		}
		
		return canContinue;
	}

	@Override
	public MessageCodeCmdResult createRotalResult(
			List<MessageCodeCmdResult> alleResults,
			MessageCodeCmdResult lastResult) {
		
		MessageCodeCmdResult result = new MessageCodeCmdResult();
		
		for (MessageCodeCmdResult cmdResult : alleResults)
		{
			result.add(cmdResult.getResults());
		}
		
		return result;
	}

}
