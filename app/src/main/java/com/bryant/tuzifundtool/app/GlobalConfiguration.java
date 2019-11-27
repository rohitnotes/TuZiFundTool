package com.bryant.tuzifundtool.app;

import android.app.Application;
import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.bryant.tuzifundtool.BuildConfig;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.integration.ConfigModule;
import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.logging.HttpLoggingInterceptor;

public class GlobalConfiguration implements ConfigModule {
    //为框架配置一些配置信息
    @Override
    public void applyOptions(@NotNull Context context, @NotNull GlobalConfigModule.Builder builder) {
        //服务器地址
        builder.baseurl(BuildConfig.BASE_URL)
        //这里可以自己自定义配置Okhttp的参数
        .okhttpConfiguration((context1, okhttpBuilder) -> {
            //请求超时设置
            okhttpBuilder.connectTimeout(StaticContent.TIME_OUT, TimeUnit.SECONDS);
            okhttpBuilder.readTimeout(StaticContent.TIME_OUT, TimeUnit.SECONDS)
            //OkHttp3日志监听
            .addInterceptor(new HttpLoggingInterceptor().
                    setLevel(HttpLoggingInterceptor.Level.BODY));
            //使用一行代码监听 Retrofit／Okhttp 上传下载进度监听,以及 Glide 加载进度监听, 详细使用方法请查看 https://github.com/JessYanCoding/ProgressManager
            //ProgressManager.getInstance().with(okhttpBuilder);
            //让 Retrofit 同时支持多个 BaseUrl 以及动态改变 BaseUrl, 详细使用方法请查看 https://github.com/JessYanCoding/RetrofitUrlManager
            RetrofitUrlManager.getInstance().with(okhttpBuilder);
        });
    }

    @Override
    public void injectAppLifecycle(@NotNull Context context, @NotNull List<AppLifecycles> lifecycles) {
        //向 Application的 生命周期中注入一些自定义逻辑
        lifecycles.add(new AppLifecycles() {
            @Override
            public void attachBaseContext(@NotNull Context base) {
                //在onCreate之前被调用,可以做一些较早的初始化
            }

            @Override
            public void onCreate(@NotNull Application application) {
                LitePal.initialize(application);
            }

            @Override
            public void onTerminate(@NotNull Application application) {
                //终止时会被调用
            }
        });
    }

    @Override
    public void injectActivityLifecycle(@NotNull Context context, @NotNull List<Application.ActivityLifecycleCallbacks> lifecycles) {
        //向 Activity 的生命周期中注入一些自定义逻辑
    }

    @Override
    public void injectFragmentLifecycle(@NotNull Context context, @NotNull List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        //向 Fragment 的生命周期中注入一些自定义逻辑
    }
}
