package FileTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import file.File;
import file.ListeChaineeFile;

class ListeChaineeFileTest {

	
	@Test
	public void offerTest() {
		File<Integer> f = new ListeChaineeFile<>();
		
		assertTrue(f.isEmpty());
		
		for (int i = 0; i < 4 ; i++) {
			f.offer(i);
		}
				
		assertEquals(4, f.size());
		
		try {
			assertEquals(0, f.peek());

			f.offer(4);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void pullTest() {
		File<Integer> f = new ListeChaineeFile<>();

		for (int i = 0; i < 5 ; i++) {
			f.offer(i);
		}
		
		assertEquals(5, f.size());
		
		try {
			assertEquals(0, f.poll());
			
			assertEquals(4, f.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void peekTest() {
		File<Integer> f = new ListeChaineeFile<>();

		for (int i = 0; i < 4 ; i++) {
			f.offer(i);
		}
		
		try {
			assertEquals(0, f.peek());
			
			f.offer(4);
			
			assertEquals(0, f.peek());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void isEmptyTest() {
		File<Integer> f = new ListeChaineeFile<>();

		assertTrue(f.isEmpty());
		
		f.offer(5);
		
		assertFalse(f.isEmpty());
	}
	
	@Test
	public void sizeTest() {
		File<Integer> f = new ListeChaineeFile<>();

		assertEquals(0, f.size());
		
		f.offer(5);
		
		assertEquals(1, f.size());
		
		for (int i = 0; i < 20 ; i++) {
			f.offer(i);
		}
		
		assertEquals(21, f.size());
		try {
			
			for (int i = 0; i < 2 ; i++) {
				f.poll();
			}
			
			assertEquals(19, f.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void clearTest() {
		File<Integer> f = new ListeChaineeFile<>();
		
		for (int i = 0; i < 20 ; i++) {
			f.offer(i);
		}
	
		assertEquals(20, f.size());
		assertFalse(f.isEmpty());
		
		f.clear();
		
		assertEquals(0, f.size());
		assertTrue(f.isEmpty());
	}
	
	@Test
	public void iteratorTest() {
		File<Integer> f = new ListeChaineeFile<>();
		
		for (int i = 0; i < 20 ; i++) {
			f.offer(i);
		}
		
		Iterator<Integer> it = f.iterator();
		
		try {	
			while (it.hasNext()){
				assertEquals(it.next(), f.poll());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
