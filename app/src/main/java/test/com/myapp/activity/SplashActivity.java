package test.com.myapp.activity;

import android.content.Intent;
import android.widget.Toast;

import test.com.myapp.R;

/**
 * Created by huangtao on 2018/11/9.
 */

public class SplashActivity  extends  BaseActivity{


    @Override
    protected int setLayoutId() {
        return R.layout.splash_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        Toast.makeText(this,"开始进入主界面",Toast.LENGTH_LONG).show();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(SplashActivity.this,MyActivity.class));
        finish();

    }




}
