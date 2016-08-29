package datastructure.line.thelist.doubleList;


import datastructure.line.thelist.InvalidNodeException;
import datastructure.line.thelist.Node;
import datastructure.line.thelist.OutOfBoundaryException;

/**
 * 连接表接口
 * @author zhengjianhui
 *
 */
public interface LinkedList {
	
	/** 返回规模 */
	public int size();

	/** 判断是否为空 */
	public boolean isEmpty();
	
	/** 返回第一个节点 */
	public Node first() throws OutOfBoundaryException;
	
	/** 返回最后一个节点 */
	public Node last() throws OutOfBoundaryException;
	
	/** 返回p 之后的节点 */
	public Node getNext(Node p) throws OutOfBoundaryException, InvalidNodeException;
	
	/** 返回p 之前的节点 */
	public Node getPre(Node p) throws OutOfBoundaryException, InvalidNodeException;
	
	/** 将e作为第一个节点插入 */
	public Node insertFirst(Object e);
	
	/** 将e作为最后个节点插入 */
	public Node insertLast(Object e);
	
	/** 将e 插入到p之后的位置 并返回e所在的节点 */
	public Node insertafter(Node node, Object obj) throws InvalidNodeException;
	
	/** 将e 插入到p之前的位置 并返回e所在的节点 */
	public Node insertBefore(Node node, Object obj) throws InvalidNodeException;
	
	/** 删除指定位置元素 并返回 */
	public Object remove(Node node) throws InvalidNodeException;
	
	/** 删除第一个元素 并返回 */
	public Object removeFrist() throws OutOfBoundaryException;
	
	/** 删除最后个元素 并返回 */
	public Object removeLast() throws OutOfBoundaryException;
	
	/** 替换指定位置元素，并返回 */
	public Object replace(Node node, Object obj) throws InvalidNodeException;
	
	/** 返回元素迭代器 */
	public datastructure.line.thelist.doubleList.Iterator.Iterator elements();
	
}
