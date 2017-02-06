package datastructure.line.arrays;


/**
 * 数组抛出的异常
 * @author zhengjianhui
 *
 */
@SuppressWarnings("serial")
public class OutOfBoundaryException extends RuntimeException{
	OutOfBoundaryException(String err) {
		super(err);
	}
}
