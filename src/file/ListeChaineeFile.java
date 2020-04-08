package file;

import java.util.Iterator;
import java.util.NoSuchElementException;

import exceptions.EmptyFifoException;


/**
 * Classe implementant l'interface pile permettant de representer une file sous la forme d'une liste chainé
 * 
 * @author steeven & yossef
 *
 * @param <E>E represente le type d'objet que peut contenir la liste chainé
 */
public class ListeChaineeFile<E> implements File<E> {

    /**
     * Classe interne permettant de définir un noeud de la liste chainé
     *
     * @author steeven & yossef
     *
     */
    private class Noeud {
        E value;
        Noeud chain;

        /**
         * contructeur
         *
         * @param value
         * @param chain
         */
        Noeud(E value, Noeud chain) {
            this.value = value;
            this.chain = chain;
        }
    }

    private Noeud start;
    private Noeud end;
    
    private int length;


    /**
     * Constructeur de la file sous forme de liste chaine permettant d'initialiser le noeud du début et le noeud de fin
     * à null (ce qui definit que la pile est vide) ainsi que la longueur de la file à 0 car la file estt vide à l'initialisation.
     */
    public ListeChaineeFile() {
		super();
		this.start = null;
		this.end = null;
		this.length = 0;
	}


	@Override
	public void offer(E value) {
		Noeud nd = new  Noeud(value, null);
		
		if (this.start == null) {
			this.start = nd;
		}
		else {
			this.end.chain = nd;
		}
		
		this.end = nd;	
		this.length++;
	}

	@Override
	public E poll() throws EmptyFifoException {
		if (this.isEmpty())
			throw new EmptyFifoException("Erreur file vide");

		E elem = this.start.value;
		this.start = this.start.chain;
		this.length--;
		
		return elem;
	}

	@Override
	public E peek() throws EmptyFifoException {
		if (this.isEmpty())
			throw new EmptyFifoException("Erreur file vide");
		
		return this.start.value;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;	
	}

	@Override
	public int size() {
		return this.length;
	}

	@Override
	public void clear() {
		this.start = this.end = null;
		this.length = 0;
	}
	
	@Override
	public Iterator<E> iterator(){
		return new Itr();
	}
	
	/**
	 * classe interne implementant l'interface Iterator permettant de redefinir les methodes hasnext() et next()
	 * pour parcourir la file
	 * 
	 *  @author steeven & yossef
	 *
	 */
	private class Itr implements Iterator<E>{

		private Noeud currentElement = start;
		@Override
		public boolean hasNext() {
			return this.currentElement != end;
		}

		@Override
		public E next() {
			if(!this.hasNext()) {
				throw new NoSuchElementException("c'etait le dernier élément");
			}
			Noeud element = this.currentElement;
			this.currentElement = this.currentElement.chain;
			
			return element.value;
		}
	}

}