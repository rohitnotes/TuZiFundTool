package com.bryant.tuzifundtool.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.blankj.utilcode.util.ToastUtils;
import com.bryant.editlibrary.BSearchEdit;
import com.bryant.tuzifundtool.app.BaseSupportActivity;
import com.bryant.tuzifundtool.bean.FundcodeBean;
import com.bryant.tuzifundtool.bean.SearchBean;
import com.bryant.tuzifundtool.bean.QueryBean;
import com.bryant.tuzifundtool.bean.SzFundBean;
import com.bryant.tuzifundtool.di.component.DaggerMainComponent;
import com.bryant.tuzifundtool.entity.FundEntity;
import com.bryant.tuzifundtool.entity.MultipleItemEntity;
import com.bryant.tuzifundtool.util.TimeScope;
import com.bryant.tuzifundtool.view.adapter.FundListAdapter;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.bryant.tuzifundtool.mvp.contract.MainContract;
import com.bryant.tuzifundtool.mvp.presenter.MainPresenter;
import com.bryant.tuzifundtool.R;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import butterknife.BindView;
import static com.jess.arms.utils.Preconditions.checkNotNull;

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
public class MainActivity extends BaseSupportActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.szzsText)
    TextView szzsText;
    @BindView(R.id.queryText)
    TextView queryText;
    @BindView(R.id.readText)
    TextView readText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.editText)
    EditText editText;

    FundListAdapter adapter;
    List<MultipleItemEntity> beautyData = new ArrayList<>();
    BSearchEdit bSearchEdit;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        RxBus.get().register(this);
        if(mPresenter!=null){
            mPresenter.getFundcode(null);
            mPresenter.getSZFund(this);
        }

        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        adapter = new FundListAdapter(this,beautyData);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_progress);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if(mPresenter!=null){
                mPresenter.getFundcode(null);
                mPresenter.getSZFund(this);
            }
        });

        bSearchEdit = new BSearchEdit(this,editText,350);
        bSearchEdit.setPopup_bg(R.color.edit_background).setIsLine(true).build();
        bSearchEdit.setTextClickListener((position, text) -> {
            if(mPresenter!=null) {
                mPresenter.getFundcode(text);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if(mPresenter!=null)
                  mPresenter.getSearchFund(MainActivity.this,editText.getText().toString());
            }
        });

        readText.setOnClickListener(view -> ArmsUtils.startActivity(HelpActivity.class));
    }


    @SuppressLint("DefaultLocale")
    @Override
    public void queryFund(QueryBean queryBean) {
        swipeRefreshLayout.setRefreshing(false);
        beautyData.clear();
        if(queryBean.getDatas()!=null){
            for (int i=0;i<queryBean.getDatas().size();i++){
                MultipleItemEntity multipleItemEntity = new MultipleItemEntity(MultipleItemEntity.mItem);
                FundEntity entity = new FundEntity();
                entity.setFundName(queryBean.getDatas().get(i).getSHORTNAME());
                entity.setFundCode(queryBean.getDatas().get(i).getFCODE());
                entity.setUnitValue(queryBean.getDatas().get(i).getNAV());
                entity.setPastGain(queryBean.getDatas().get(i).getNAVCHGRT());
                entity.setBudgetValue(queryBean.getDatas().get(i).getGSZZL());
                multipleItemEntity.setFundEntity(entity);
                beautyData.add(multipleItemEntity);
            }
        }
        adapter.notifyDataSetChanged();
        ToastUtils.showShort("已更新");
    }

    @Override
    public void searchFund(SearchBean searchBean) {
        if(searchBean.getDatas()!=null){
            ArrayList<String> list = new ArrayList<>();
            for (int i=0;i<searchBean.getDatas().size();i++){
                list.add(searchBean.getDatas().get(i).getFCODE()+" "+searchBean.getDatas().get(i).getSHORTNAME());
            }
            bSearchEdit.setSearchList(list);
        }
    }

    @Override
    public void searchCodeFund(String code) {
        if(mPresenter!=null) {
            mPresenter.getQueryFund(this, code);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void szFund(SzFundBean szFundBean) {
        if(szFundBean==null){
            return;
        }
        String isOpenFund = "闭市";
        if(TimeScope.isCurrentInTimeScope(9, 0,15,0)){
            isOpenFund = "";
        }
        if(szFundBean.getData().getDiff().get(0).getF3()>0) {
            szzsText.setTextColor(getResources().getColor(R.color.item_state2));
            szzsText.setText("上证指数：" + szFundBean.getData().getDiff().get(0).getF2()+"  +"+szFundBean.getData().getDiff().get(0).getF3()+"%"+"  "+isOpenFund);
        }else{
            szzsText.setTextColor(getResources().getColor(R.color.item_state));
            szzsText.setText("上证指数：" + szFundBean.getData().getDiff().get(0).getF2()+"  "+szFundBean.getData().getDiff().get(0).getF3()+"%"+"  "+isOpenFund);
        }
    }

    @Subscribe
    public void Function(String param){
        if(mPresenter!=null) {
            mPresenter.getFundcode(null);
        }
    }

    ItemTouchHelper.Callback mCallback = new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.LEFT;
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            Collections.swap(beautyData, viewHolder.getAdapterPosition()-1, target.getAdapterPosition()-1);
            adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            beautyData.remove(viewHolder.getAdapterPosition()-1);
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            LitePal.delete(FundcodeBean.class, viewHolder.getAdapterPosition()-1);
        }

        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            LitePal.deleteAll(FundcodeBean.class);
            for (int i=0;i<beautyData.size();i++){
                FundcodeBean fundcodeBean = new FundcodeBean();
                fundcodeBean.setFundcode(beautyData.get(i).getFundEntity().getFundCode());
                fundcodeBean.save();
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                //滑动时改变Item的透明度
                final float alpha = 1 - Math.abs(dX) / (float)viewHolder.itemView.getWidth();
                viewHolder.itemView.setAlpha(alpha);
                viewHolder.itemView.setTranslationX(dX);
            }
        }
    };

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
