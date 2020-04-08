package file;

import java.lang.reflect.Array;
import java.util.Iterator;

import exceptions.EmptyFifoException;


/**
 * Classe implementant l'interface File, permettant de reprensenter une file sous la forme d'un tableau statique
 * c'est à dire d'un tableau avec une taille fixé lors de l'initialisation mais qui suit la strategie rotative de 
 * "on perd on le plus ancien" en cas de file pleine
 * 
 * @author yossef & steeven
 *
 * @param <E> E represente le type d'objet que peut contenir la file
 */
public class TabStatiqueFile<E> implements File<E> {

	private int indexStart;
	private int indexEnd;
	private E[] tab; 
	
	@SuppressWarnings("unchecked")
	/**
	 * constructeur de TabStatiqueFile permettant d'initialiser l'index de debut et de fin de file à 0 (il faut surtout qu'il soit 
	 * égaux pour initialiser la file à vide au debut)
	 * il ne prend pas de parametre, donc on fixe une taille par defaut à 10.
	 */
	public TabStatiqueFile() {
		super();
		this.indexStart = 0;
		this.indexEnd = 0;
		this.tab = (E[]) Array.newInstance(Object.class, 10);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * constructeur de TabStatiqueFile permettant d'initialiser l'index de debut et de fin de file à 0 (il faut surtout qu'il soit 
	 * égaux pour initialiser la file à vide au debut)
	 * il prend en parametre la taille fixé de la file.
	 * @param size
	 */
	public TabStatiqueFile(int size) {
		super();
		this.indexStart = 0;
		this.indexEnd = 0;
		this.tab = (E[]) Array.newInstance(Object.class, size);
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	

	/**
	 * classe interne implementant l'interface Iterator permettant de redefinir les methodes hasnext() et next()
	 * pour parcourir la file
	 * 
	 *  @author steeven & yossef
	 *
	 */
	private class Itr implements Iterator<E> {
		private int indexCourant = indexStart;

		public boolean hasNext() {
			return this.indexCourant != indexEnd;
		}

		public E next() {
						
			if (this.indexCourant == tab.length) {
				this.indexCourant = 0;
				return tab[this.indexCourant ];
			}			
			
			return tab[this.indexCourant++];
		}
	}
	
	
	@Override
	public void offer(E value) {
		this.tab[indexEnd] = value;
		
		this.indexEnd = (this.indexEnd + 1) % this.tab.length;
		
		if(this.indexStart >= this.indexEnd) {
			this.indexStart = (this.indexStart + 1) % this.tab.length;
		}	
	}

	@Override
	public E poll() throws EmptyFifoException {
		
		if (this.isEmpty()) {
			throw new EmptyFifoException("Erreur file vide"); 
		}
		E top = this.tab[this.indexStart];
		this.indexStart = (this.indexStart + 1) % this.tab.length;

		return top;
	}

	@Override
	public E peek() throws EmptyFifoException {
		if (this.isEmpty()) {
			throw new EmptyFifoException("Erreur file vide"); 
		}
		return this.tab[this.indexStart];
	}

	@Override
	public boolean isEmpty() {
		return this.indexEnd == this.indexStart;
	}

	@Override
	public int size() {
		int nbElement = 0;
		
		if (this.indexStart < this.indexEnd) {
			nbElement = this.indexEnd - this.indexStart;
		}
		
		else if (this.indexStart > this.indexEnd) {
			nbElement = this.tab.length - this.indexStart + this.indexEnd;
		}
		
		return nbElement;
	
	}

	@Override
	public void clear() {
		this.indexStart = this.indexEnd = 0;
	}
}
