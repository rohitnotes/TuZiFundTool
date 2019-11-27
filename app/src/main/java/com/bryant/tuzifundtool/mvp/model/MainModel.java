package com.bryant.tuzifundtool.mvp.model;

import android.app.Application;
import android.content.Context;

import com.bryant.tuzifundtool.app.RetrofitService;
import com.bryant.tuzifundtool.bean.QueryBean;
import com.bryant.tuzifundtool.bean.SearchBean;
import com.bryant.tuzifundtool.bean.SzFundBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.bryant.tuzifundtool.mvp.contract.MainContract;
import com.jess.arms.utils.ArmsUtils;

import java.util.Map;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/21/2019 15:32
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<QueryBean> getQueryFund(Context context, Map<String, Object> map) {
        return ArmsUtils.obtainAppComponentFromContext(context).repositoryManager()
                .obtainRetrofitService(RetrofitService.class)
                .getQueryFund(map);
    }

    @Override
    public Observable<SearchBean> getSearchFund(Context context, Map<String, Object> map) {
        RetrofitUrlManager.getInstance().putDomain("ttjj", "http://appsuggest.1234567.com.cn/");
        return ArmsUtils.obtainAppComponentFromContext(context).repositoryManager()
                .obtainRetrofitService(RetrofitService.class)
                .getSearchFund(map);
    }

    @Override
    public Observable<SzFundBean> getSZFund(Context context, Map<String, Object> map) {
        RetrofitUrlManager.getInstance().putDomain("szzs", "https://push2.eastmoney.com/");
        return ArmsUtils.obtainAppComponentFromContext(context).repositoryManager()
                .obtainRetrofitService(RetrofitService.class)
                .getSZFund(map);
    }

}