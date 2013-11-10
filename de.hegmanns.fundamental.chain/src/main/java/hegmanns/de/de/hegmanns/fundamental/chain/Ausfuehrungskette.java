package hegmanns.de.de.hegmanns.fundamental.chain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Fuehrt die eigentliche Pruefung durch. Diese Klasse stellt das zentrale Objekt dar und wird <b>nicht</b>
 * ueberschrieben.
 * 
 * <br><br>
 * <b>WICHTIG</b><br>
 * Mit jeder Aenderung einer Pruefkette (z.B. neues PruefCommand) immer die Testsuite ausfuehren.
 * Die Standard-Testsuite fuehrt einen einfachen Plausi-Check durch und kontrolliert, ob Pruefergebnisse doppelt
 * vorkommen oder Pruefergebnisse mit doppelten Bedeutungen enthalten sind.
 * Eine neue PruefFactory IMMER in die Standard-Testsuite aufnehmen!!!
 * 
 * @author B. Hegmanns
 */
public final class Ausfuehrungskette<T, E extends AusfuehrungsContext<T>, R, A extends ReadableAusfuehrungsResult<R>> implements AusfuehrungsCommand<T, E, R, A>{

	public List<AusfuehrungsCommand<T, E, R, A>> ausfuehrungsCmds;
	private E ausfuehrungsContext = null;
	private List<ReadableAusfuehrungsResult<R>> results;
	public AusfuehrungsAdvice<T, E, R, A> ausfuehrungsAdvice;
	
	
	public Ausfuehrungskette(){
		ausfuehrungsCmds = new ArrayList<AusfuehrungsCommand<T, E, R, A>>();
		results = new ArrayList<ReadableAusfuehrungsResult<R>>();
	}
	
	@Override
	public void execute(E ausfuehrungsContext,
			A ausfuehrungsResult) {
		
		for (AusfuehrungsCommand<T, E, R, A> cmd : ausfuehrungsCmds)
		{
			A result = erstelleAusfuehrungsResult();
			cmd.execute(getAusfuehrungsContext(), result);
			results.add(result);
			if (!isFortsetzbar(result)){
				break;
			}
			
		}
		
		setAusfuehrungsresultAusEinzelergebnisse(results, ausfuehrungsContext, ausfuehrungsResult);
	}
	
	private E getAusfuehrungsContext()
	{
		if (ausfuehrungsContext == null)
		{
			ausfuehrungsContext = ausfuehrungsAdvice.createAusfuehrungscontext();
		}
		return ausfuehrungsContext;
	}
	private A erstelleAusfuehrungsResult(){
		return ausfuehrungsAdvice.erstelleAusfuehrungsresult();
	}
	
	private boolean isFortsetzbar(ReadableAusfuehrungsResult<R> result){
		return ausfuehrungsAdvice.chainFortsetzen(result);
	}
	
	private void setAusfuehrungsresultAusEinzelergebnisse(List<ReadableAusfuehrungsResult<R>> alleResults, E ausfuehrungscontext, AusfuehrungsResult<R> ausfuehrungsresult){
		ausfuehrungsAdvice.befuelleResult(ausfuehrungsresult, ausfuehrungscontext, alleResults);
	}

	@Override
	public Map<R, String> getMoeglicheErgebnisse() {
		Map<R, String> moeglicheErgebnisse = new HashMap<R, String>();
		for (AusfuehrungsCommand<T, E, R, A> cmd : ausfuehrungsCmds)
		{
			moeglicheErgebnisse.putAll(cmd.getMoeglicheErgebnisse());
		}
		return moeglicheErgebnisse;
	}
	
	public List<R> doWork(){
		A ausfuehrungsresult = erstelleAusfuehrungsResult();
		execute(getAusfuehrungsContext(), ausfuehrungsresult);
		
		return ausfuehrungsresult.getResults();
	}
	
//	/**
//	 * An dieses Objekt delegiert die Pruefkette die Erzeugung wichtiger Werte.
//	 */
//	private AusfuehrungskettenFactory<T, E> ausfuehrungskettenFactory;
//	
//	public Ausfuehrungskette(){
//		
//	}
//	
//	/**
//	 * Fuehrt die eigentliche Pruefung durch.
//	 * 
//	 * @param pruefcommands
//	 * @param instanz
//	 * @return
//	 */
//	private AusfuehrungsResult<E> ausfuehrungscommandsAusfuehren(List<AusfuehrungsCommand<T, E>> ausfuehrungscommands, T instanz){
//		AusfuehrungsContext<T> pruefContext = initAusfuehrungsContext(instanz);
//		AusfuehrungsResult<E> pruefResult = null;
//		List<AusfuehrungsResult<E>> pruefergebnisse = new ArrayList<>(ausfuehrungscommands.size());
//		for (AusfuehrungsCommand<T, E> ausfuehrungscommand : ausfuehrungscommands){
//			pruefResult = initAusfuehrungsResult(instanz);
//			
//			ausfuehrungscommand.execute(pruefContext, pruefResult);
//			pruefergebnisse.add(pruefResult);
//			if (!istFortsetzbar(pruefResult)){
//				break;
//			}
//		}
//		
//		return createAusfuehrungsResultAusTeilergebnisse(pruefergebnisse);
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	public Map<E, String> getMoeglicheErgebnisse(){
//		List<AusfuehrungsCommand<T, E>> pruefCommands = getPruefCommands();
//		Map<E, String> map = new HashMap<E, String>();
//		
//		for (AusfuehrungsCommand<T, E> pruefCommand : pruefCommands)
//		{
//			map = gather(map, pruefCommand.getMoeglicheErgebnisse());
//		}
//		
//		return map;
//	}
//	
//	private Map<E, String> gather(Map<E, String> source, Map<E, String> zuAddieren)
//	{
//		if (zuAddieren == null)
//		{
//			return source;
//		}
//		for (Entry<E, String> mapEntry : zuAddieren.entrySet())
//		{
//			if (!source.containsKey(mapEntry.getKey()))
//			{
//				source.put(mapEntry.getKey(), mapEntry.getValue());
//			}
//			else
//			{
//				String sourceValue = source.get(mapEntry.getKey());
//				if (!mapEntry.getValue().equals(sourceValue))
//				{
//					throw new IllegalArgumentException("Ergebnis '" + mapEntry.getKey() + "' kann nicht mit zweifacher Erklaerung aufgenommen werden!!!");
//				}
//				else
//				{
//					// do nothing, ...
//				}
//			}
//		}
//		return source;
//	}
//	
//	public AusfuehrungsResult<E> pruefe(T pruefInstanz){
//		AusfuehrungsResult<E> result = ausfuehrungscommandsAusfuehren(getPruefCommands(), pruefInstanz);
//		
//		return result;
//	}
//	
//	private List<AusfuehrungsCommand<T, E>> getPruefCommands(){
//		return ausfuehrungskettenFactory.getPruefCommands();
//	}
//	
//	private AusfuehrungsContext<T> initAusfuehrungsContext(T instanz){
//		return ausfuehrungskettenFactory.createPruefContext();
//	}
//	
//	private AusfuehrungsResult<E> initAusfuehrungsResult(T instanz){
//		return ausfuehrungskettenFactory.createPruefResult();
//	}
//	
//	private boolean istFortsetzbar(AusfuehrungsResult<E> ausfuehrungsResult){
//		return true;
//	}
//	
//	private AusfuehrungsResult<E> createAusfuehrungsResultAusTeilergebnisse(Collection<AusfuehrungsResult<E>> allePruefergebnisse){
//		return null;
//	}
//
//	@Override
//	public void execute(AusfuehrungsContext<T> pruefContext, AusfuehrungsResult<E> pruefResult) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void execute(E ausfuehrungsContext,
//			AusfuehrungsResult<R> ausfuehrungsResult) {
//		// TODO Auto-generated method stub
//		
//	}
}
