package file;

import java.lang.reflect.Array;
import java.util.Iterator;

public class TabStatiqueFile<E> implements File<E> {

	private int indexStart;
	private int indexEnd;
	private E[] tab; 
	
	@SuppressWarnings("unchecked")
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
	public E poll() throws Exception {
		
		if (this.isEmpty()) {
			throw new Exception("Erreur file vide"); 
		}
		E top = this.tab[this.indexStart];
		this.indexStart = (this.indexStart + 1) % this.tab.length;

		return top;
	}

	@Override
	public E peek() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Erreur file vide"); 
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
