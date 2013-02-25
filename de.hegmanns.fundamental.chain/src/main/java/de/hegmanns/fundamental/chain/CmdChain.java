package de.hegmanns.fundamental.chain;

import java.util.ArrayList;
import java.util.List;

public final class CmdChain<S, T extends CmdContext<S>, U, W extends CmdResult<U>> implements Cmd<S, T, U, W>{

	private List<Cmd<S, T, U, W>> commands = new ArrayList<Cmd<S, T, U, W>>();
	private ChainAdvice<S, T, U, W> chainAdvice;
	
	
	
	public ChainAdvice<S, T, U, W> getChainAdvice() {
		return chainAdvice;
	}

	public void setChainAdvice(ChainAdvice<S, T, U, W> chainAdvice) {
		this.chainAdvice = chainAdvice;
	}

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
		for (Cmd<S, T, U, W> command : commands)
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
	
	public void add(Cmd<S, T, U, W> command){
		commands.add(command);
	}

	@Override
	public void execute(T context, W result) {
		// TODO Auto-generated method stub
		
	}
}
