package test.com.myapp.view;

/**
 * Created by huangtao on 2018/12/12.
 * V层接口，定义V层需要作出的动作的接口
 */

public interface RequestView<T>  extends IMvpBaseView {

    //请求成功
    void resultSuccess(T result);
    //请求失败
    void resultFailure(String result);



}
