package file;

import java.util.Iterator;

/**
 * Interface permettant de representer une file
 * 
 * @author yossef & steeven
 *
 * @param <E>
 */
public interface File<E> extends Iterable<E> {

	/**
	 * Methode permettant d'ajouter un element E dans une file
	 * @param value
	 */
	void offer(E value);
	
	/**
	 * Methode permettant de retirer le premier element E de la file et de le retourner
	 * leve une exception en cas de file vide
	 * @return
	 * @throws Exception
	 */
	E poll() throws Exception;
	
	
	/**
	 * Methode permettant de retourner le premier element E de la file
	 * leve une exception en cas de file vide
	 * @return
	 * @throws Exception
	 */
	E peek() throws Exception;
	
	
	/**
	 * Methode permettant de tester si la file est vide ou non
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * Methode retournant la taille d'une file
	 * @return
	 */
	int size();
	
	
	/**
	 * Methode permettant de vider la file
	 */
	void clear();
	
	/**
	 * Methode permettant de pacourir la file en utilisant l'iterateur
	 */
	Iterator<E> iterator();
}
