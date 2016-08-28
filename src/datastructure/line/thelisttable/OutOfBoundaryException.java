package datastructure.line.thelisttable;


/**
 * 越界异常
 * @author zhengjianhui
 *
 */
@SuppressWarnings("serial")
public class OutOfBoundaryException extends RuntimeException{
	OutOfBoundaryException(String err) {
		super(err);
	}
}
