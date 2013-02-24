package de.hegmanns.fundamental.chain;

public interface Cmd<S, T extends CmdContext<S>, U, W extends CmdResult<U>> {

	/**
	 * Fuehrt den feingranularen Befehl aus, bedient sich hierbei dem Context
	 * und schreibt das Ergebnis oder die Ergebnisse in das Result.
	 * 
	 * <p>
	 * 	Falls dieses Command nicht ausgefuehrt werden kann, 
	 * </p>
	 * @param context
	 * @param result
	 */
	public void execute(T context, W result);
}
