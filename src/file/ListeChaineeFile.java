package file;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
	public E poll() throws Exception {
		if (this.isEmpty())
			throw new Exception("Erreur pile vide");

		E elem = this.start.value;
		this.start = this.start.chain;
		this.length--;
		
		return elem;
	}

	@Override
	public E peek() throws Exception {
		if (this.isEmpty())
			throw new Exception("Erreur pile vide");
		
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