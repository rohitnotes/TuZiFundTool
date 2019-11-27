package com.bryant.tuzifundtool.app;

import com.bryant.tuzifundtool.bean.SearchBean;
import com.bryant.tuzifundtool.bean.QueryBean;
import com.bryant.tuzifundtool.bean.SzFundBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2019/10/06.
 */

public interface RetrofitService {
    /*基金实时信息*/
    @FormUrlEncoded
    @POST("FundMNewApi/FundMNFInfo")
    Observable<QueryBean> getQueryFund(@FieldMap Map<String,Object> map);

    /*查询基金*/
    @Headers({"Domain-Name: ttjj"})
    @GET("FundMSearchApi/FundSearchNewFunds")
    Observable<SearchBean> getSearchFund(@QueryMap Map<String,Object> map);

    /*上证指数*/
    @Headers({"Domain-Name: szzs"})
    @FormUrlEncoded
    @POST("api/qt/ulist.np/get")
    Observable<SzFundBean> getSZFund(@FieldMap Map<String,Object> map);
}
