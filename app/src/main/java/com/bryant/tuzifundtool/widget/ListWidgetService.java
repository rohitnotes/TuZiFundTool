package com.bryant.tuzifundtool.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bryant.tuzifundtool.R;
import com.bryant.tuzifundtool.util.TimeScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SuppressLint("Registered")
public class ListWidgetService extends RemoteViewsService {

    public static final String INITENT_POSITION = "extra_position";
    public Context mContext;
    public static List<Map<String,Object>> mList = new ArrayList<>();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }

    private class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        ListRemoteViewsFactory(Context context) {
            mContext = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {
            mList.clear();
        }

        @Override
        public int getCount() {
            return mList.size()+1;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.fundlist_item_widget);
            if(position == 0){
                views.setTextViewText(R.id.fl_name, "基金名称");
                views.setTextViewText(R.id.fl_code, "基金代码");
                views.setTextViewText(R.id.fl_dwjz, "单位净值");
                views.setTextViewText(R.id.fl_zrzf, "昨日涨幅");
                views.setTextViewText(R.id.fl_jrgs, "今日估算");
                views.setTextColor(R.id.fl_zrzf,mContext.getResources().getColor(R.color.white));
                views.setTextColor(R.id.fl_jrgs,mContext.getResources().getColor(R.color.white));
            }else {
                views.setTextViewText(R.id.fl_name, mList.get(position-1).get("name").toString());
                views.setTextViewText(R.id.fl_code, mList.get(position-1).get("code").toString());
                views.setTextViewText(R.id.fl_dwjz, mList.get(position-1).get("nav").toString());
                if(TimeScope.getWeekend()>0&&TimeScope.getWeekend()<=5) {
                    if (isDoubleOrFloat(mList.get(position - 1).get("navchgrt").toString()) && Double.valueOf(mList.get(position - 1).get("navchgrt").toString()) > 0) {
                        views.setTextColor(R.id.fl_zrzf, mContext.getResources().getColor(R.color.item_state2));
                    }else{
                        views.setTextColor(R.id.fl_zrzf, mContext.getResources().getColor(R.color.item_state));
                    }
                    views.setTextViewText(R.id.fl_zrzf, mList.get(position - 1).get("navchgrt").toString() + "%");
                    if (isDoubleOrFloat(mList.get(position - 1).get("gszzl").toString()) && Double.valueOf(mList.get(position - 1).get("gszzl").toString()) > 0) {
                        views.setTextColor(R.id.fl_jrgs, mContext.getResources().getColor(R.color.item_state2));
                    }else{
                        views.setTextColor(R.id.fl_jrgs, mContext.getResources().getColor(R.color.item_state));
                    }
                    views.setTextViewText(R.id.fl_jrgs, mList.get(position - 1).get("gszzl").toString() + "%");
                }else{
                    if(TimeScope.getWeekend()==0) {
                        views.setTextViewText(R.id.fl_zrzf, "end");
                        views.setTextViewText(R.id.fl_jrgs, "end");
                        views.setTextColor(R.id.fl_zrzf, mContext.getResources().getColor(R.color.item_state3));
                        views.setTextColor(R.id.fl_jrgs, mContext.getResources().getColor(R.color.item_state3));
                    }else{
                        if (isDoubleOrFloat(mList.get(position - 1).get("navchgrt").toString()) && Double.valueOf(mList.get(position - 1).get("navchgrt").toString()) > 0) {
                            views.setTextColor(R.id.fl_zrzf, mContext.getResources().getColor(R.color.item_state2));
                        }else{
                            views.setTextColor(R.id.fl_zrzf, mContext.getResources().getColor(R.color.item_state));
                        }
                        views.setTextViewText(R.id.fl_zrzf, mList.get(position - 1).get("navchgrt").toString() + "%");
                        views.setTextViewText(R.id.fl_jrgs, "end");
                        views.setTextColor(R.id.fl_jrgs, mContext.getResources().getColor(R.color.item_state3));
                    }
                }
            }
            Bundle extras = new Bundle();
            extras.putInt(ListWidgetService.INITENT_POSITION, position);
            Intent changeIntent = new Intent();
            changeIntent.putExtras(extras);
            views.setOnClickFillInIntent(R.id.fl_jrgs, changeIntent);
            return views;
        }

        /* 在更新界面的时候如果耗时就会显示 正在加载... 的默认字样，但是你可以更改这个界面
         * 如果返回null 显示默认界面
         * 否则 加载自定义的，返回RemoteViews
         */
        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }

    public static boolean isDoubleOrFloat(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }
}
