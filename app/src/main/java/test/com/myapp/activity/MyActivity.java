package test.com.myapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import test.com.myapp.Fragment.MainFragment;
import test.com.myapp.Fragment.SubFragment;
import test.com.myapp.R;

/**
 * Created by huangtao on 2018/12/13.
 */

public class MyActivity extends AppCompatActivity implements View.OnClickListener{

    private  int index;
    private  int currentPosition;

    private  Button[] btns;
    private  Fragment[] fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        initView();
        initFragments();
    }

    private  void initView(){
        btns = new Button[2];
        btns[0] = (Button) findViewById(R.id.btn_main);
        btns[1] = (Button) findViewById(R.id.btn_system);
        btns[0].setOnClickListener(this);
        btns[1].setOnClickListener(this);
        btns[0].setSelected(true);
    }

    private void initFragments() {
        fragments = new Fragment[]{new MainFragment(),new SubFragment()};
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragments[0]).show(fragments[0]).commitAllowingStateLoss();

    }

    private void showCurentFragment(int index){
        if(currentPosition!=index){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(fragments[currentPosition]);
            if (!fragments[index].isAdded()) {
                ft.add(R.id.container, fragments[index]);
            }
            ft.show(fragments[index]).commitAllowingStateLoss();
            btns[currentPosition].setSelected(false);
            btns[index].setSelected(true);
            scaleView();
            currentPosition = index;

        }

    }

    /**
     * view放大缩小
     */
    private void scaleView() {
        btns[currentPosition].animate().scaleX(0.9f).scaleY(0.9f)
                .setDuration(150).start();
        btns[index].animate().scaleX(1.0f).scaleY(1.0f)
                .setDuration(150).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main:
                index=0;
                break;
            case R.id.btn_system:
                index=1;
                break;
                default:
        }
        showCurentFragment(index);

    }
}
