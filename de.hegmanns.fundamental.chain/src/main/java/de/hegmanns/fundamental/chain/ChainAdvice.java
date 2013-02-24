package de.hegmanns.fundamental.chain;

import java.util.List;

/**
 * Hilfsklasse zur Ermittlung von Contextobjekten.
 * 
 * @author bernd
 *
 * @param <S>
 * @param <T>
 * @param <U>
 * @param <W>
 */
public interface ChainAdvice<S, T extends CmdContext<S>, U, W extends CmdResult<U>> {

	public T createCmdContext(S instanz);
	
	public W createCmdResult(S instanz);
	
	public boolean canContinue(W currentResult);
	
	public W createRotalResult(List<W> alleResults, W lastResult);
}
