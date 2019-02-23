package test.com.myapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.com.myapp.presenter.AbstractMvpPersenter;
import test.com.myapp.view.IMvpBaseView;

/**
 * Created by huangtao on 2018/12/13.
 */

public abstract class BaseFragment<V extends IMvpBaseView,P extends AbstractMvpPersenter<V>> extends Fragment implements  IMvpBaseView{

    private P presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        int layoutId = setLayoutId();
        if (layoutId != 0) {
            view = inflater.inflate(setLayoutId(), container, false);
            initView(view);
        }
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter 不能为空!");
        }
        //绑定view
        presenter.attachMvpView((V) this);
        initData();
        return view;

    }


    /**
     * 创建Presenter
     * @return 子类自己需要的Presenter
     */
    protected abstract P createPresenter();
    /**
     * 获取Presenter
     * @return 返回子类创建的Presenter
     */
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("BaseFragment","onDestroy");
        //解除绑定
        if (presenter != null) {
            presenter.detachMvpView();
            presenter.interruptHttp();
        }
    }

    @Override
    public void requestLoading() {

    }

    /**
     * 获取布局
     */
    protected abstract int setLayoutId();

    /**
     * 初始化
     */
    protected abstract void initView(View  view);

    /**
     * 初始化
     */
    protected abstract void initData();
}
