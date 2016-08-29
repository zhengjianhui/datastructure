package datastructure.line.thelist;


/**
 * 数组抛出的异常
 * @author zhengjianhui
 *
 */
@SuppressWarnings("serial")
public class OutOfBoundaryException extends RuntimeException{
	public OutOfBoundaryException(String err) {
		super(err);
	}
}
