package datastructure.line.myarray;



/***
 * 自定义抛出异常
 * @author zhengjianhui
 *
 */
@SuppressWarnings("serial")
public class MyException extends IndexOutOfBoundsException{

	public MyException(String err) {
		super(err);
	}
}
