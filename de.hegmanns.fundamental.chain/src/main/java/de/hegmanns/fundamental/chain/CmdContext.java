package de.hegmanns.fundamental.chain;


public class CmdContext<S> {

	private S instanz;

	public CmdContext(S instanz){
		this.instanz = instanz;
	}
	public S getInstanz() {
		return instanz;
	}
	public void setInstanz(S instanz) {
		this.instanz = instanz;
	}
	
	
	
}
