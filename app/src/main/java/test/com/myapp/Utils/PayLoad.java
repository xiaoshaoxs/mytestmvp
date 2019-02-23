package test.com.myapp.Utils;

import android.util.Log;

import rx.functions.Func1;

/**
 * Created by huangtao on 2018/11/27.
 */

public class PayLoad<T>  implements Func1<BaseResponse<T>,T>{

    @Override
    public T call(BaseResponse<T> tBaseResponse) {
//        if(!tBaseResponse.isSuccess()){
//            //throw new Fault(tBaseResponse.status,tBaseResponse.message);
//
//        }
       // Log.e("MovieList","---------isSuccesss:----------"+tBaseResponse.isSuccess());
        return tBaseResponse.subjects;

    }

}
