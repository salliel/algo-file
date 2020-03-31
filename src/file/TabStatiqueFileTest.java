package file;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

class TabStatiqueFileTest {

	@Test
	public void offerTest() {
		File<Integer> f = new TabStatiqueFile<>(5);
		
		assertTrue(f.isEmpty());
		
		for (int i = 0; i < 4 ; i++) {
			f.offer(i);
		}
				
		assertEquals(4, f.size());
		
		try {
			assertEquals(0, f.peek());

			f.offer(4);
			
			assertFalse(0 == f.peek());
			
			assertEquals(1, f.peek());

			assertTrue(1 == f.peek());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void pullTest() {
		File<Integer> f = new TabStatiqueFile<>(5);

		for (int i = 0; i < 5 ; i++) {
			f.offer(i);
		}
		
		assertEquals(4, f.size());
		
		try {
			assertEquals(1, f.poll());
			
			assertEquals(3, f.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void peekTest() {
		File<Integer> f = new TabStatiqueFile<>(5);

		for (int i = 0; i < 4 ; i++) {
			f.offer(i);
		}
		
		try {
			assertEquals(0, f.peek());
			
			f.offer(4);
			
			assertFalse(0 == f.peek());
			
			assertEquals(1, f.peek());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void isEmptyTest() {
		File<Integer> f = new TabStatiqueFile<>(5);

		assertTrue(f.isEmpty());
		
		f.offer(5);
		
		assertFalse(f.isEmpty());
	}
	
	@Test
	public void sizeTest() {
		File<Integer> f = new TabStatiqueFile<>(5);

		assertEquals(0, f.size());
		
		f.offer(5);
		
		assertEquals(1, f.size());
		
		for (int i = 0; i < 20 ; i++) {
			f.offer(i);
		}
		
		assertEquals(4, f.size());
		try {
			
			for (int i = 0; i < 2 ; i++) {
				f.poll();
			}
			
			assertEquals(2, f.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void clearTest() {
		File<Integer> f = new TabStatiqueFile<>(5);
		
		for (int i = 0; i < 20 ; i++) {
			f.offer(i);
		}
	
		assertEquals(4, f.size());
		assertFalse(f.isEmpty());
		
		f.clear();
		
		assertEquals(0, f.size());
		assertTrue(f.isEmpty());
	}
	
	@Test
	public void iteratorTest() {
		File<Integer> f = new TabStatiqueFile<>(5);
		
		for (int i = 0; i < 20 ; i++) {
			f.offer(i);
		}
		
		Iterator<Integer> it = f.iterator();
		
		try {
			assertEquals(it.next(), f.peek());
	
			int i = 0;
			while (it.hasNext()){
				System.out.println(it.next());
				i++;
			}
			
			assertEquals(4, i);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
