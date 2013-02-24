package de.hegmanns.fundamental.chain;

import java.util.ArrayList;
import java.util.List;

public final class CmdChain<S, T extends CmdContext<S>, U, W extends CmdResult<U>> {

	private List<Cmd<S, CmdContext<S>, U, CmdResult<U>>> commands;
	private ChainAdvice<S, T, U, W> chainAdvice;
	
	public W processWith(S instanz){
		
		return processChain(instanz);
	}
	
	public W processChain(S instanz)
	{
		// Initialisierung der Ausfuehrungskette 
		T context = chainAdvice.createCmdContext(instanz);
		W cmdResult = null;
		
		List<W> alleResults = new ArrayList<W>();
		// Durchfuehrung
		for (Cmd<S, CmdContext<S>, U, CmdResult<U>> command : commands)
		{
			cmdResult = chainAdvice.createCmdResult(instanz);
			command.execute(context, cmdResult);
			alleResults.add(cmdResult);
			if (!chainAdvice.canContinue(cmdResult)){
				break;
			}
		}
		
		// Auswertung und Ermittlung des Ergebnis
		return chainAdvice.createRotalResult(alleResults, cmdResult);
	}
}
