package test.com.myapp.presenter;

import android.util.Log;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import test.com.myapp.entity.Movie;
import test.com.myapp.model.MovieLoader;
import test.com.myapp.view.RequestView;

/**
 * Created by huangtao on 2018/12/12.
 */

public class MovieRequestPresenter extends AbstractMvpPersenter<RequestView> {
    private  MovieLoader  mMode;
    public MovieRequestPresenter(){
        mMode=new MovieLoader();
    }
    private Subscription subscription;
    public  void getListMovie(){
        //获取View
        if(getmMvpView() != null){
            getmMvpView().requestLoading();
        }

        subscription=mMode.getListMovie(0,10).subscribe(new Observer<List<Movie>>() {
            @Override
            public void onCompleted() {
                Log.e("MovieList","---------onCompleted:----------   ");

            }
            @Override
            public void onError(Throwable e) {
                Log.e("MovieList","---------onError:----------   ");
                if(getmMvpView() != null){
                    getmMvpView().resultFailure(e.toString());
                }
            }

            @Override
            public void onNext(List<Movie> movies) {
                    Log.e("MovieList","---------onNext:----------   ");
                if(getmMvpView() != null){
                    getmMvpView().resultSuccess(movies);
                }
            }
        });


    }

    @Override
    public void interruptHttp() {
        if(subscription !=null && !subscription.isUnsubscribed()  ){
            subscription.unsubscribe();
        }
    }
}
