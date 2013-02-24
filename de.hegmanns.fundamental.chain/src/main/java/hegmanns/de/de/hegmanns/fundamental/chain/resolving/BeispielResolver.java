package hegmanns.de.de.hegmanns.fundamental.chain.resolving;

import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsContext;

@ResolveForType(instanceType = String.class, resolveType = String.class)
public class BeispielResolver extends AbstractInstanceResolver<String, String> {

	@Override
	protected AbstractInstanceResolver createInstance() {
		return this;
	}
	
	public static void main(String [] args){
		BeispielResolver beispielResolver = new BeispielResolver();
		
	}

	@Override
	public String resolve(AusfuehrungsContext<String> pruefContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
