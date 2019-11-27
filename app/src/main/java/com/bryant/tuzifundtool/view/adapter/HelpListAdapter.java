package com.bryant.tuzifundtool.view.adapter;

import android.content.Context;
import com.bryant.tuzifundtool.R;
import com.bryant.tuzifundtool.entity.MultipleItemEntity;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

public class HelpListAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, BaseViewHolder> {


    public HelpListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(MultipleItemEntity.mItem, R.layout.helplist_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItemEntity item) {
        helper.setText(R.id.help_title,helper.getLayoutPosition()+"."+item.getHelpEntity().getHelpTitle());
        helper.setText(R.id.help_cpntent,item.getHelpEntity().getHelpContent());
    }

}
