package test.com.myapp.Fragment;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import test.com.myapp.R;
import test.com.myapp.entity.Movie;
import test.com.myapp.presenter.MovieRequestPresenter;
import test.com.myapp.view.RequestView;

/**
 * Created by huangtao on 2018/12/13.
 */

public class SubFragment extends  BaseFragment<RequestView,MovieRequestPresenter> implements  RequestView<List<Movie>> {
    private MovieRequestPresenter presenter;
    private TextView textView;

    @Override
    protected MovieRequestPresenter createPresenter() {
        return  presenter=new MovieRequestPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.my_fragment;
    }

    @Override
    protected void initView(View view) {
        textView=(TextView) view.findViewById(R.id.mytexttext);
        textView.setText("SubFragment");
    }

    //获取数据
    @Override
    protected void initData() {
        getPresenter().getListMovie();
    }

    //填充数据
    @Override
    public void resultSuccess(List<Movie> result) {
        textView.setText("SubFragment:"+result.size());
        //mypro

    }

    @Override
    public void resultFailure(String result) {
        textView.setText("SubFragment:"+result);

    }




}
