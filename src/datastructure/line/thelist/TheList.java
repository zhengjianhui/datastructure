package datastructure.line.thelist;

import org.junit.Test;

/**
 * 测试单向链表
 * @author zhengjianhui
 *
 */
public class TheList {

	@Test
	public void test() {
		ListSLinked l = new ListSLinked();
		
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		System.out.println(l.isEmpty());
		System.out.println(l.size());
		
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));			
		}
		
		System.out.println("==================================");
		
		
		l.insert(1, 1.5);
		
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));			
		}
		
		System.out.println("==================================");
		
		
		l.removeIndex(1);
		
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));			
		}
		
		System.out.println("==================================");
		
	}
}
