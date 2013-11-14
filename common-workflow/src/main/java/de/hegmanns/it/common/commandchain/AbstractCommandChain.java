package de.hegmanns.it.common.commandchain;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommandChain<W extends CommandContext<U>, U, R extends CommandResult<T>, T> implements Command<W, U, R, T>{
	
	private List<Command<W, U, R, T>> commands = new ArrayList<>();

	@Override
	public void execute(W context, R result) {
		
		List<R> resultsInChain = new ArrayList<>();
		R singleResultForCommand = null;
		for (Command<W, U, R, T> command : commands)
		{
			singleResultForCommand = createEmptyChainResult(context.getInstanz());
			command.execute(context, singleResultForCommand);
			
			resultsInChain.add(singleResultForCommand);
			
			if (!canContinue(resultsInChain, singleResultForCommand)){
				break;
			}
		}
		
		fillIntoChainResult(result, resultsInChain, singleResultForCommand);
	}
	
	public void addCommand(Command<W, U, R, T> command)
	{
		commands.add(command);
	}
	
	public final R processChain(U instanz)
	{
		W context = createChainContext(instanz);
		R result  = createEmptyChainResult(instanz);
		
		execute(context, result);
		
		return result;
	}
	
	protected abstract W createChainContext(U instanz);
	
	protected abstract R createEmptyChainResult(U instanz);
	
	protected abstract boolean canContinue(List<R> allResults, R lastResult);
	
	protected abstract void fillIntoChainResult(R targetResult, List<R> allResults, R lastResult);

}
