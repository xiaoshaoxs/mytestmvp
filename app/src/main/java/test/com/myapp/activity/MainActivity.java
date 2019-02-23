package test.com.myapp.activity;


import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import test.com.myapp.R;
import test.com.myapp.RetrofiManager.RetrofitServiceManager;
import test.com.myapp.Utils.Constant;
import test.com.myapp.entity.Movie;
import test.com.myapp.model.MovieLoader;
import test.com.myapp.presenter.MovieRequestPresenter;
import test.com.myapp.view.RequestView;

public class MainActivity extends AbstractMvpActivity<RequestView,MovieRequestPresenter> implements RequestView<List<Movie>> {

    private MovieRequestPresenter presenter;

    @Override
    protected MovieRequestPresenter createPresenter() {
        return presenter=new MovieRequestPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Log.i("MainActivity","initData");
        //获取数据
        getPresenter().getListMovie();
    }

    @Override
    public void requestLoading() {
        Log.e("MovieList","---------requestLoading:----------   ");

    }

    @Override
    public void resultSuccess(List<Movie> movies) {
        Log.e("MovieList","---------resultSuccess:----------"+movies.size());

    }

    @Override
    public void resultFailure(String re) {
        Log.e("MovieList","---------error message:----------"+re);

    }



    private  void  getMovieList(){
        MovieLoader mMovieLoader=new MovieLoader();
        mMovieLoader.getListMovie(0,10).subscribe(new Observer<List<Movie>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("MovieList","---------error message:----------"+e.getMessage());

            }

            @Override
            public void onNext(List<Movie> movies) {

                if(movies==null){
                    Log.e("MovieList","---------onNext----------  null");

                }else {
                    Log.e("MovieList","---------onNext----------  movies");

                }


            }
        });


//        mMovieLoader.getMovieBaseResponse(0,10).subscribe(new Observer<MovieSubject>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e("MovieList","---------error message:----------"+e.getMessage());
//
//            }
//
//            @Override
//            public void onNext(MovieSubject movies) {
//
//                Log.e("MovieList","--------------MovieList Size-----------"+movies.subjects);
//
//            }
//        });

//        mMovieLoader.getMovie(0,10).subscribe(new Observer<List<Movie>>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(List<Movie> movies) {
//                    Log.e("MovieList","MovieList Size"+movies.size());
//            }
//        });

    }

    private void  getDataObserve(){
        //获取接口实例
        //MovieService movieService= RetrofitServiceManager.getInstance().create(MovieService.class);
        //执行Observable
//        movieService.getTop250able(0,30)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Movie>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Movie movie) {
//                        Log.e("MyRetrofit","initData getTotal"+movie.title);
//                        Toast.makeText(MainActivity.this,"哈哈哈哈",Toast.LENGTH_LONG).show();
//
//
//                    }
//                });
    }

    private  void  testRetrofit(){

//        Call<Movie> movieCall=movieService.getTop250Post(0,40);
//        movieCall.enqueue(new Callback<Movie>() {
//            @Override
//            public void onResponse(Call<Movie> call, Response<Movie> response) {
//                Log.e("MyRetrofit","initData getTotal:"+response.body().getCount());
//
//            }
//
//            @Override
//            public void onFailure(Call<Movie> call, Throwable t) {
//
//            }
//        });
        Log.i("MyRetrofit","initData");
//        Toast.makeText(MainActivity.this,"initData",Toast.LENGTH_LONG).show();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constant.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        //获取接口实例
//        MovieService movieService=retrofit.create(MovieService.class);
//        //调用方法得到call
//        Call<Movie>  movieServiceCall=movieService.getTop250(0,20);
//        movieServiceCall.enqueue(new Callback<Movie>() {
//            @Override
//            public void onResponse(Call<Movie> call, Response<Movie> response) {
//                Log.e("MyRetrofit","initData getTotal"+response.body().title);
//
//            }
//
//            @Override
//            public void onFailure(Call<Movie> call, Throwable t) {
//
//            }
//
//
//        });
    }

    private void  testRxJava(){
        //创建观察者
        Observer<String>  observer=new Observer<String>() {
            //事件触发
            @Override
            public void onNext(String s) {
                Log.i("testRxJava","onNext");

            }
            //不会再有新的 onNext() 发出时，需要触发 onCompleted() 方法作为标志。
            @Override
            public void onCompleted() {
                Log.i("testRxJava","onCompleted");
            }

            //事件队列异常。在事件处理过程中出异常时，onError() 会被触发，同时队列自动终止，不允许再有事件发出
            @Override
            public void onError(Throwable e) {
                Log.i("testRxJava","onError");

            }
        };

        //创建Observer 观察者抽象类: Subscriber
        //实质上，在 RxJava 的 subscribe 过程中，Observer 也总是会先被转换成一个 Subscriber 再使用
        Subscriber<String> subscriber=new Subscriber<String>() {


            //它会在 subscribe 刚开始，而事件还未发送之前被调用
            //unsubscribe(): 这是 Subscriber 所实现的另一个接口 Subscription 的方法，用于取消订阅
            @Override
            public void onStart() {
                super.onStart();
                Log.i("testRxJava","onStart");


            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        //创建被观察者
        Observable observable = Observable.create(new  Observable.OnSubscribe<String>(){
                                                      @Override
            public void call(Subscriber<? super String> subscriber) {
                                subscriber.onNext("Hello");
                                subscriber.onNext("hi ");
                                subscriber.onCompleted();
                  }
             }
        );


        //直接发送事件
        Observable.just("hellp","hi")
                .subscribeOn(Schedulers.io())//发生的线程
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //数据预处理

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//返回结果线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        //最终数据

                    }
                });




        //事件数据 map Func1
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observableForm=Observable.from(words);
        Observable.just("images/logo.png") // 输入类型 String
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String filePath) { // 参数类型 String
                        Bitmap bitmap=null;
                        return  bitmap; // 返回类型 Bitmap
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) { // 参数类型 Bitmap

                    }
                });

      ///flatMap  Func1
//        getToken()
//                .flatMap(new Func1<String, Observable<User>>() {  //参数类型  返回类型
//                    @Override
//                    public Observable<User> onNext(String token) {
//                        return getUser(token, userId);
//                    })
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Observer<User>() {
//                        @Override
//                        public void onNext(User user) {
//                            userView.setUser(user);
//                        }
//
//                        @Override
//                        public void onCompleted() {
//                        }
//
//                        @Override
//                        public void onError(Throwable error) {
//                            // Error handling
//            ...
//                        }
//                    });

        //Schedulers.immediate(): 直接在当前线程运行

        //Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。

        // Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler

        //AndroidSchedulers.mainThread()主线程

    }



}
