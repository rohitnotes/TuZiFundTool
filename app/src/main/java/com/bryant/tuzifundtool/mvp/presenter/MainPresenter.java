package com.bryant.tuzifundtool.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.bryant.tuzifundtool.app.BaseObserver;
import com.bryant.tuzifundtool.app.BaseSupportActivity;
import com.bryant.tuzifundtool.bean.FundcodeBean;
import com.bryant.tuzifundtool.bean.QueryBean;
import com.bryant.tuzifundtool.bean.SearchBean;
import com.bryant.tuzifundtool.bean.SzFundBean;
import com.bryant.tuzifundtool.util.RxUtils;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import timber.log.Timber;
import javax.inject.Inject;
import com.bryant.tuzifundtool.mvp.contract.MainContract;
import org.litepal.LitePal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }

    public void getQueryFund(BaseSupportActivity activity,String queryCode){
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex","1");
        map.put("ReqNo","1574412794906");
        map.put("pageSize","30");
        map.put("cccccccTokenErrorStop","true");
        map.put("plat","Android");
        map.put("appType","ttjj");
        map.put("deviceid","1");
        map.put("product","EFund");
        map.put("version","1");
        map.put("Fcodes",queryCode);
        mModel.getQueryFund(activity,map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.applySchedulersNoLoading(mRootView))
                .subscribe(new BaseObserver<QueryBean>() {
                    @Override
                    public void onSuccess(QueryBean queryBean) {
                        if(queryBean!=null&&queryBean.getErrCode()==0){
                            mRootView.queryFund(queryBean);
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Timber.i(e.toString());
                    }
                });
    }

    public void getSearchFund(BaseSupportActivity activity, String key){
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex","1");
        map.put("version","1");
        map.put("pageSize","8");
        map.put("KEY",key);
        map.put("plat","Android");
        map.put("appType","ttjj");
        map.put("deviceid","1");
        map.put("product","EFund");
        mModel.getSearchFund(activity,map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.applySchedulersNoLoading(mRootView))
                .subscribe(new BaseObserver<SearchBean>() {
                    @Override
                    public void onSuccess(SearchBean searchBean) {
                        if(searchBean.getDatas()!=null){
                            mRootView.searchFund(searchBean);
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Timber.i(e.toString());
                    }
                });
    }

    public void getSZFund(BaseSupportActivity activity){
        Map<String,Object> map = new HashMap<>();
        map.put("fltt","2");
        map.put("secids","1.000001");
        map.put("fields","f1,f2,f3,f14");
        mModel.getSZFund(activity,map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.applySchedulersNoLoading(mRootView))
                .subscribe(new BaseObserver<SzFundBean>() {
                    @Override
                    public void onSuccess(SzFundBean searchBean) {
                        if(searchBean.getData()!=null){
                            mRootView.szFund(searchBean);
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Timber.i(e.toString());
                    }
                });
    }

    public void getFundcode(String text){
        Log.i("code","text"+text);
        if(!"".equals(text)&& text != null) {
            String[] sp = text.split(" ");
            FundcodeBean fundcode = new FundcodeBean();
            fundcode.setFundcode(sp[0]);
            fundcode.save();
        }
        List<FundcodeBean> fundcodeBeans = LitePal.findAll(FundcodeBean.class);
        StringBuilder code = new StringBuilder();
        for (int i=0;i<fundcodeBeans.size();i++){
            code.append(",").append(fundcodeBeans.get(i).getFundcode());
            Log.i("code",""+fundcodeBeans.get(i).getFundcode());
        }
        mRootView.searchCodeFund(code.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
