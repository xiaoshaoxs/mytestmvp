package test.com.myapp.presenter;

import test.com.myapp.view.IMvpBaseView;

/**
 * Created by huangtao on 2018/12/12.
 */

public abstract  class AbstractMvpPersenter<V extends IMvpBaseView> {
    private  V mMvpView;

    /**
     * 绑定V层
     * @param view
     */
    public  void attachMvpView(V view){
            this.mMvpView=view;
    }

    /**
     * 解除绑定V层
     */
    public  void detachMvpView(){
        mMvpView=null;
    }

    /**
     * 获取V层
     * @return
     */
    public  V getmMvpView(){
        return mMvpView;
    }




    /**
     * 取消网络请求
     */
    public  void interruptHttp(){};




}
