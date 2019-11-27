package com.bryant.tuzifundtool.mvp.contract;

import android.content.Context;

import com.bryant.tuzifundtool.bean.SearchBean;
import com.bryant.tuzifundtool.bean.QueryBean;
import com.bryant.tuzifundtool.bean.SzFundBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.Map;

import io.reactivex.Observable;


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
public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void queryFund(QueryBean realBean);

        void searchFund(SearchBean searchBean);

        void searchCodeFund(String code);

        void szFund(SzFundBean szFundBean);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<QueryBean> getQueryFund(Context context, Map<String,Object> map);

        Observable<SearchBean> getSearchFund(Context context, Map<String,Object> map);

        Observable<SzFundBean> getSZFund(Context context, Map<String,Object> map);
    }
}
