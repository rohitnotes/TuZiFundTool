package com.bryant.tuzifundtool.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.bryant.tuzifundtool.mvp.ui.activity.HelpActivity;
import com.jess.arms.di.component.AppComponent;

import com.bryant.tuzifundtool.di.module.HelpModule;
import com.bryant.tuzifundtool.mvp.contract.HelpContract;

import com.jess.arms.di.scope.ActivityScope;


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
@ActivityScope
@Component(modules = HelpModule.class, dependencies = AppComponent.class)
public interface HelpComponent {
    void inject(HelpActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HelpComponent.Builder view(HelpContract.View view);

        HelpComponent.Builder appComponent(AppComponent appComponent);

        HelpComponent build();
    }
}