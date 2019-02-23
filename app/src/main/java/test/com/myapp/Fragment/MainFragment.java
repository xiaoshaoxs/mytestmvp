package test.com.myapp.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
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

public class MainFragment extends  BaseFragment<RequestView,MovieRequestPresenter> implements  RequestView<List<Movie>>{
    private MovieRequestPresenter presenter;
    private TextView textView;
    private SwipeRefreshLayout refreshLayout;
    @Override
    protected MovieRequestPresenter createPresenter() {
        return presenter=new MovieRequestPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.my_fragment;
    }

    @Override
    protected void initView(View view) {
        textView=(TextView) view.findViewById(R.id.mytexttext);
        refreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refreshLayout);
        textView.setText("MainFragment");
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initData() {
        getPresenter().getListMovie();

    }

    @Override
    public void resultSuccess(List<Movie> result) {
        textView.setText("MainFragment:"+result.size());
    }

    @Override
    public void resultFailure(String result) {

    }
}
