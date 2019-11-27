package com.bryant.tuzifundtool.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bryant.tuzifundtool.app.BaseSupportActivity;
import com.bryant.tuzifundtool.entity.HelpEntity;
import com.bryant.tuzifundtool.entity.MultipleItemEntity;
import com.bryant.tuzifundtool.view.adapter.HelpListAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.bryant.tuzifundtool.di.component.DaggerHelpComponent;
import com.bryant.tuzifundtool.mvp.contract.HelpContract;
import com.bryant.tuzifundtool.mvp.presenter.HelpPresenter;
import com.bryant.tuzifundtool.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/26/2019 17:26
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class HelpActivity extends BaseSupportActivity<HelpPresenter> implements HelpContract.View {

    @BindView(R.id.breaks)
    TextView breaks;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<MultipleItemEntity> beautyData = new ArrayList<>();
    HelpListAdapter adapter;

    String[] helpTitle,helpContent;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHelpComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_help; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        breaks.setOnClickListener(view -> HelpActivity.this.finish());

        helpTitle = getResources().getStringArray(R.array.helpTitle);
        helpContent = getResources().getStringArray(R.array.helpContent);

        for (int i=0;i<helpTitle.length;i++){
            MultipleItemEntity multipleItemEntity = new MultipleItemEntity(MultipleItemEntity.mItem);
            HelpEntity entity = new HelpEntity();
            entity.setHelpTitle(helpTitle[i]);
            entity.setHelpContent(helpContent[i]);
            multipleItemEntity.setHelpEntity(entity);
            beautyData.add(multipleItemEntity);
        }

        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        adapter = new HelpListAdapter(beautyData);
        recyclerView.setAdapter(adapter);
    }

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
}
