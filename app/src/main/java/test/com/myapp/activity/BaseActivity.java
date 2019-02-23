package test.com.myapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by huangtao on 2018/11/9.
 */

public abstract class BaseActivity  extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initView();
        initData();
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
