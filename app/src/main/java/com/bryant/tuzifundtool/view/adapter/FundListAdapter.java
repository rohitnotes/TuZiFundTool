package com.bryant.tuzifundtool.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.blankj.utilcode.util.ToastUtils;
import com.bryant.tuzifundtool.R;
import com.bryant.tuzifundtool.bean.FundcodeBean;
import com.bryant.tuzifundtool.entity.MultipleItemEntity;
import com.bryant.tuzifundtool.util.TimeScope;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hwangjr.rxbus.RxBus;
import org.litepal.LitePal;
import java.util.List;
import java.util.regex.Pattern;

public class FundListAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, BaseViewHolder> {

    private Context context;

    public FundListAdapter(Context context,List<MultipleItemEntity> data) {
        super(data);
        this.context = context;
        addItemType(MultipleItemEntity.mItem, R.layout.fundlist_item);
        LayoutInflater inflater= LayoutInflater.from(context);
        @SuppressLint("InflateParams")
        View footerView = inflater.inflate(R.layout.fundlist_item_header, null);
        addHeaderView(footerView);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItemEntity item) {
        helper.setText(R.id.fl_name,item.getFundEntity().getFundName());
        helper.setText(R.id.fl_code,item.getFundEntity().getFundCode());
        helper.setText(R.id.fl_dwjz,item.getFundEntity().getUnitValue());
        if(TimeScope.getWeekend()>0&&TimeScope.getWeekend()<=5) {
            if (isDoubleOrFloat(item.getFundEntity().getPastGain()) && Double.valueOf(item.getFundEntity().getPastGain()) > 0) {
                helper.setTextColor(R.id.fl_zrzf, context.getResources().getColor(R.color.item_state2));
            }else{
                helper.setTextColor(R.id.fl_zrzf, mContext.getResources().getColor(R.color.item_state));
            }
            helper.setText(R.id.fl_zrzf, item.getFundEntity().getPastGain() + "%");
            if (isDoubleOrFloat(item.getFundEntity().getBudgetValue()) && Double.valueOf(item.getFundEntity().getBudgetValue()) > 0) {
                helper.setTextColor(R.id.fl_jrgs, context.getResources().getColor(R.color.item_state2));
            }else{
                helper.setTextColor(R.id.fl_jrgs, mContext.getResources().getColor(R.color.item_state));
            }
            helper.setText(R.id.fl_jrgs, item.getFundEntity().getBudgetValue() + "%");
        }else{
            if(TimeScope.getWeekend()==0) {
                helper.setText(R.id.fl_zrzf, "end");
                helper.setText(R.id.fl_jrgs, "end");
                helper.setTextColor(R.id.fl_zrzf, mContext.getResources().getColor(R.color.item_state3));
                helper.setTextColor(R.id.fl_jrgs, mContext.getResources().getColor(R.color.item_state3));
            }else{
                if (isDoubleOrFloat(item.getFundEntity().getPastGain()) && Double.valueOf(item.getFundEntity().getPastGain()) > 0) {
                    helper.setTextColor(R.id.fl_zrzf, context.getResources().getColor(R.color.item_state2));
                }else{
                    helper.setTextColor(R.id.fl_zrzf, mContext.getResources().getColor(R.color.item_state));
                }
                helper.setText(R.id.fl_zrzf, item.getFundEntity().getPastGain() + "%");
                helper.setText(R.id.fl_jrgs, "end");
                helper.setTextColor(R.id.fl_jrgs, mContext.getResources().getColor(R.color.item_state3));
            }
        }
        helper.setOnClickListener(R.id.fl_name, view -> ToastUtils.showShort(item.getFundEntity().getFundName()));

        helper.setOnLongClickListener(R.id.fl_code, view -> {
            LitePal.deleteAll(FundcodeBean.class,"fundcode = ?",item.getFundEntity().getFundCode());
            RxBus.get().post("update");
            return false;
        });
    }

    private static boolean isDoubleOrFloat(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }
}
