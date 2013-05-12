package hegmanns.it.de.java6.corelanguage.generic;

public class Command<T> {

	private Class<T> type;
	
	public Command(Class<T> type){
		this.type = type;
	}
	
	public <T>Command(T type)
	{

	}
}
