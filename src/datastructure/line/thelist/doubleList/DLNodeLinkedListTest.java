package datastructure.line.thelist.doubleList;

import org.junit.Test;

import datastructure.line.thelist.doubleList.Iterator.Iterator;

public class DLNodeLinkedListTest {

	@Test
	public void test() {
		LinkedListDLNote l = new LinkedListDLNote();
		
		l.insertLast(1);
		l.insertLast(2);
		l.insertLast(3);
		l.insertLast(4);
		l.insertLast(5);
		
	
//		System.out.println(l.removeFrist());
//		System.out.println(l.removeFrist());
//		System.out.println(l.removeFrist());
//		System.out.println(l.removeFrist());
//		System.out.println(l.removeFrist());
		
		
		Iterator i = l.elements();
		while(!i.isDone()) {
			
			
			DLNode obj = (DLNode) i.currentItem();
			System.out.println(obj.getData());
			
			Object obj1 = i.currentItem();
			System.out.println(obj1);
			
			i.next();
		}
	}
}
