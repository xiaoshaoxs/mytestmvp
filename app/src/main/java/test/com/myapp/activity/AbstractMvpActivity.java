package test.com.myapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import test.com.myapp.presenter.AbstractMvpPersenter;
import test.com.myapp.view.IMvpBaseView;

/**
 * Created by huangtao on 2018/11/9.
 */

public abstract class AbstractMvpActivity<V extends IMvpBaseView, P extends AbstractMvpPersenter<V>>  extends AppCompatActivity implements IMvpBaseView {

    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initView();
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter 不能为空!");
        }
        //绑定view
        presenter.attachMvpView((V) this);
        initData();
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
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        if (presenter != null) {
            presenter.detachMvpView();
            presenter.interruptHttp();
        }
    }
    /**
     * 获取布局
     */
    protected abstract int setLayoutId();

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 初始化
     */
    protected abstract void initData();






}
