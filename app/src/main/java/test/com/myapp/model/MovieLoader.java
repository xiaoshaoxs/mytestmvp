package test.com.myapp.model;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import test.com.myapp.RetrofiManager.RetrofitServiceManager;
import test.com.myapp.Utils.BaseResponse;
import test.com.myapp.Utils.ObservableThread;
import test.com.myapp.Utils.PayLoad;
import test.com.myapp.entity.Movie;

/**
 * Created by huangtao on 2018/11/23.
 */

public class MovieLoader extends ObservableThread {
    private MovieService mMovieService;

    public MovieLoader(){
        mMovieService = RetrofitServiceManager.getInstance().create(MovieService.class);
    }

   //获取单个数据 T
    public  Observable<List<Movie>> getListMovie(int start, int count){
        return  observe(mMovieService.getTopMovie(start,count).map(new PayLoad<List<Movie>>()));

    }

    //获取所有数据 BaseResponse
    public Observable<BaseResponse<List<Movie>>> getBaseResponse(){
        return  observe(mMovieService.getTopMovie(0,10));
    }


    public interface MovieService{
        @GET("top250")
        Observable<BaseResponse<List<Movie>>> getTopMovie(@Query("start") int start, @Query("count")int count);
    }


}
