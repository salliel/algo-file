package file;

public interface File<E> extends Iterable<E> {

	void offer(E value);
	
	E poll() throws Exception;
	
	E peek() throws Exception;
	
	boolean isEmpty();
	
	int size();
	
	void clear();
}
