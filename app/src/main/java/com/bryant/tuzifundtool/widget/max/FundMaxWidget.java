package com.bryant.tuzifundtool.widget.max;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.blankj.utilcode.util.ToastUtils;
import com.bryant.tuzifundtool.R;
import com.bryant.tuzifundtool.app.BaseObserver;
import com.bryant.tuzifundtool.app.RetrofitService;
import com.bryant.tuzifundtool.bean.FundcodeBean;
import com.bryant.tuzifundtool.bean.QueryBean;
import com.bryant.tuzifundtool.bean.SzFundBean;
import com.bryant.tuzifundtool.util.TimeScope;
import com.bryant.tuzifundtool.widget.FundAppWidget;
import com.bryant.tuzifundtool.widget.ListWidgetService;
import com.jess.arms.utils.ArmsUtils;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import timber.log.Timber;

/**
 * Implementation of App Widget functionality.
 */
public class FundMaxWidget extends AppWidgetProvider {

    static RemoteViews views;
    long sleep = 50;
    Context mContext;

    int[] bitmapId = new int[]{R.mipmap.temp_00000,R.mipmap.temp_00001,R.mipmap.temp_00002,R.mipmap.temp_00003,R.mipmap.temp_00004,R.mipmap.temp_00005,R.mipmap.temp_00006,R.mipmap.temp_00007,R.mipmap.temp_00008,R.mipmap.temp_00009
            ,R.mipmap.temp_00010,R.mipmap.temp_00011,R.mipmap.temp_00012,R.mipmap.temp_00013,R.mipmap.temp_00014,R.mipmap.temp_00015,R.mipmap.temp_00016,R.mipmap.temp_00017,R.mipmap.temp_00018,R.mipmap.temp_00019};
    static final int START_ANIMATION = 0;

    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        views = new RemoteViews(context.getPackageName(), R.layout.fund_app_widget);

        Intent lvIntent = new Intent(context, ListWidgetService.class);
        views.setRemoteAdapter(R.id.listView, lvIntent);
        views.setEmptyView(R.id.listView,android.R.id.empty);

        Intent toIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 200, toIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.listView, pendingIntent);

        Intent refreshIntent = new Intent(context, FundMaxWidget.class);
        refreshIntent.setAction("0x00");
        final PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(context, 0, refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.bt,refreshPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (TextUtils.equals(intent.getAction(),"0x00")) {
            this.mContext = context;
            num = 0;
            getQueryFund(context,getFundCode());
            getSZFund(context);
            Message msg = mHandler.obtainMessage(START_ANIMATION);
            msg.arg1 = 0;
            mHandler.sendMessage(msg);
        }
    }

    private void queryFund(Context context,QueryBean queryBean){
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ListWidgetService.mList.clear();
        if(queryBean.getDatas()!=null) {
            for (int i = 0; i < queryBean.getDatas().size(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", queryBean.getDatas().get(i).getSHORTNAME());
                map.put("code", queryBean.getDatas().get(i).getFCODE());
                map.put("nav", queryBean.getDatas().get(i).getNAV());
                map.put("navchgrt", queryBean.getDatas().get(i).getNAVCHGRT());
                map.put("gszzl", queryBean.getDatas().get(i).getGSZZL());
                ListWidgetService.mList.add(map);
            }
        }
        ComponentName mComponentName = new ComponentName(context, FundMaxWidget.class);
        manager.notifyAppWidgetViewDataChanged(manager.getAppWidgetIds(mComponentName),
                R.id.listView);
        ToastUtils.showShort("已更新");
    }

    public void getQueryFund(Context context, String queryCode){
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex","1");
        map.put("ReqNo","1574412794906");
        map.put("pageSize","10");
        map.put("cccccccTokenErrorStop","true");
        map.put("plat","Android");
        map.put("appType","ttjj");
        map.put("deviceid","1");
        map.put("product","EFund");
        map.put("version","1");
        map.put("Fcodes",queryCode);
        getQueryFund(context,map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<QueryBean>() {
                    @Override
                    public void onSuccess(QueryBean queryBean) {
                        if(queryBean!=null&&queryBean.getErrCode()==0){
                            queryFund(context,queryBean);
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Timber.i(e.toString());
                    }
                });
    }

    public Observable<QueryBean> getQueryFund(Context context, Map<String, Object> map) {
        return ArmsUtils.obtainAppComponentFromContext(context).repositoryManager()
                .obtainRetrofitService(RetrofitService.class)
                .getQueryFund(map);
    }

    public void szFund(SzFundBean szFundBean,Context context){
        if(szFundBean.getData()==null){
            return;
        }
        String isOpenFund = "闭市";
        if(TimeScope.isCurrentInTimeScope(9, 0,15,0)){
            isOpenFund = "";
        }
        AppWidgetManager appWidgetManger = AppWidgetManager.getInstance(context);
        views = new RemoteViews(context.getPackageName(), R.layout.fund_app_widget);
        if(szFundBean.getData().getDiff().get(0).getF3()>0) {
            views.setTextColor(R.id.szzsText,context.getResources().getColor(R.color.item_state2));
            views.setTextViewText(R.id.szzsText, "上证指数：" + szFundBean.getData().getDiff().get(0).getF2()+"  +"+szFundBean.getData().getDiff().get(0).getF3()+"%"+"  "+isOpenFund);
        }else{
            views.setTextColor(R.id.szzsText,context.getResources().getColor(R.color.item_state));
            views.setTextViewText(R.id.szzsText, "上证指数：" + szFundBean.getData().getDiff().get(0).getF2()+"  "+szFundBean.getData().getDiff().get(0).getF3()+"%"+"  "+isOpenFund);
        }
        appWidgetManger.updateAppWidget(new ComponentName(context, FundMaxWidget.class), views);
    }

    public void getSZFund(Context context){
        Map<String,Object> map = new HashMap<>();
        map.put("fltt","2");
        map.put("secids","1.000001");
        map.put("fields","f1,f2,f3,f14");
        getSZFund(context,map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<SzFundBean>() {
                    @Override
                    public void onSuccess(SzFundBean searchBean) {
                        if(searchBean.getData()!=null){
                            szFund(searchBean,context);
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Timber.i(e.toString());
                    }
                });
    }

    public Observable<SzFundBean> getSZFund(Context context, Map<String, Object> map) {
        RetrofitUrlManager.getInstance().putDomain("szzs", "https://push2.eastmoney.com/");
        return ArmsUtils.obtainAppComponentFromContext(context).repositoryManager()
                .obtainRetrofitService(RetrofitService.class)
                .getSZFund(map);
    }

    public String getFundCode(){
        List<FundcodeBean> fundcodeBeans = LitePal.findAll(FundcodeBean.class);
        if(fundcodeBeans.size()<=0){
            return "";
        }
        StringBuilder code = new StringBuilder();
        for (int i=0;i<fundcodeBeans.size();i++){
            code.append(",").append(fundcodeBeans.get(i).getFundcode());
        }
        return code.toString();
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        getQueryFund(context,getFundCode());
        getSZFund(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private int num = 0;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NotNull Message msg) {
            super.handleMessage(msg);
            AppWidgetManager appWidgetManger = AppWidgetManager.getInstance(mContext);
            if (msg.what == START_ANIMATION) {
                views = new RemoteViews(mContext.getPackageName(), R.layout.fund_app_widget);
                views.setImageViewResource(R.id.bt, bitmapId[msg.arg1]);
                appWidgetManger.updateAppWidget(new ComponentName(mContext, FundMaxWidget.class), views);
                //循环图片
                num++;
                if(num<=bitmapId.length) {
                    Message message = mHandler.obtainMessage(START_ANIMATION);
                    message.arg1 = (msg.arg1 + 1) % bitmapId.length;
                    mHandler.sendMessageDelayed(message, sleep);
                }
            }
        }
    };


}

