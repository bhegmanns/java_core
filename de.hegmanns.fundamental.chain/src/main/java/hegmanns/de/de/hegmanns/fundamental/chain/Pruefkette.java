package hegmanns.de.de.hegmanns.fundamental.chain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
 *
 */
public final class Pruefkette<T, E> implements PruefCommand<T, E>{
	
	/**
	 * An dieses Objekt delegiert die Pruefkette die Erzeugung wichtiger Werte.
	 */
	private PruefkettenFactory<T, E> pruefkettenFactory;
	
	public Pruefkette(){
		
	}
	
	/**
	 * Fuehrt die eigentliche Pruefung durch.
	 * 
	 * @param pruefcommands
	 * @param pruefInstanz
	 * @return
	 */
	private PruefResult<E> pruefCommandsAusfuehren(List<PruefCommand<T, E>> pruefcommands, T pruefInstanz){
		PruefContext<T> pruefContext = initPruefContext(pruefInstanz);
		PruefResult<E> pruefResult = null;
		List<PruefResult<E>> pruefergebnisse = new ArrayList<>(pruefcommands.size());
		for (PruefCommand<T, E> pruefcommand : pruefcommands){
			pruefResult = initPruefResult(pruefInstanz);
			
			pruefcommand.execute(pruefContext, pruefResult);
			pruefergebnisse.add(pruefResult);
			if (!istPruefungFortsetzbar(pruefResult)){
				break;
			}
		}
		
		return createPruefResultAusPruefergebnisse(pruefergebnisse);
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<E, String> getMoeglichePruefergebnisse(){
		List<PruefCommand<T, E>> pruefCommands = getPruefCommands();
		Map<E, String> map = new HashMap<E, String>();
		
		for (PruefCommand<T, E> pruefCommand : pruefCommands)
		{
			map = gather(map, pruefCommand.getMoeglichePruefergebnisse());
		}
		
		return map;
	}
	
	private Map<E, String> gather(Map<E, String> source, Map<E, String> zuAddieren)
	{
		if (zuAddieren == null)
		{
			return source;
		}
		for (Entry<E, String> mapEntry : zuAddieren.entrySet())
		{
			if (!source.containsKey(mapEntry.getKey()))
			{
				source.put(mapEntry.getKey(), mapEntry.getValue());
			}
			else
			{
				String sourceValue = source.get(mapEntry.getKey());
				if (!mapEntry.getValue().equals(sourceValue))
				{
					throw new IllegalArgumentException("Ergebnis '" + mapEntry.getKey() + "' kann nicht mit zweifacher Erklaerung aufgenommen werden!!!");
				}
				else
				{
					// do nothing, ...
				}
			}
		}
		return source;
	}
	
	public PruefResult<E> pruefe(T pruefInstanz){
		PruefResult<E> result = pruefCommandsAusfuehren(getPruefCommands(), pruefInstanz);
		
		return result;
	}
	
	private List<PruefCommand<T, E>> getPruefCommands(){
		return pruefkettenFactory.getPruefCommands();
	}
	
	private PruefContext<T> initPruefContext(T pruefInstanz){
		return pruefkettenFactory.createPruefContext();
	}
	
	private PruefResult<E> initPruefResult(T pruefInstanz){
		return pruefkettenFactory.createPruefResult();
	}
	
	private boolean istPruefungFortsetzbar(PruefResult<E> pruefresult){
		return true;
	}
	
	private PruefResult<E> createPruefResultAusPruefergebnisse(Collection<PruefResult<E>> allePruefergebnisse){
		return null;
	}

	@Override
	public void execute(PruefContext<T> pruefContext, PruefResult<E> pruefResult) {
		// TODO Auto-generated method stub
		
	}
}
