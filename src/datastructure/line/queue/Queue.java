package datastructure.line.queue;
/**
 * 队列接口定义
 * @author zhengjianhui
 *
 */
public interface Queue {

	/** 返回规模 */
	public int getSize();
	
	/** 判空 */
	public boolean isEmpty();
	
	/** 入队 */
	public void enQueue(Object e);
	
	/** 出队 */
	public Object deQueue() throws QueueEmptyException;
	
	/** 获取队首元素但是不出队 */
	public Object peek() throws QueueEmptyException;
}
