package test.com.myapp.Utils;

/**
 * Created by huangtao on 2018/11/27.
 */

public class Fault extends RuntimeException{
    private int errorCode;

    public Fault(int errorCode,String message){
        super(message);
        errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
