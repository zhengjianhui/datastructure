package datastructure.line.myarray;


import org.junit.Test;

public class ArrayTest {

	@Test
	public void insert() {
		MyArray test = new MyArray(6);
		
//		System.out.println(test.isEmpty());
		
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		for (int i = 0; i < test.length(); i++) {
			System.out.println(test.get(i));
			
		}
		
		
		System.out.println();
		
//		System.out.println(test.length());
		
		test.insert(3, 3.5);
		
		for (int i = 0; i < test.length(); i++) {
			System.out.println(test.get(i));
		}
		
		
		System.out.println();
		
		test.removeIndex(5);
		for (int i = 0; i < test.length(); i++) {
			System.out.println(test.get(i));
		}
		
		
		
		
		
	}
}
