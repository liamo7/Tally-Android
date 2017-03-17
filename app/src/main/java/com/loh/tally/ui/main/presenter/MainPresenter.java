package com.loh.tally.ui.main.presenter;

import com.loh.tally.domain.authentication.AuthenticationManager;
import com.loh.tally.domain.database.modules.ModuleService;
import com.loh.tally.ui.base.AsyncCallback;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;
import com.loh.tally.ui.main.adapter.MainPagerAdapter;

import javax.inject.Inject;

/**
 * File: MainPresenter.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Communicates with business logic for the functionality of {@link com.loh.tally.ui.main.activity.MainActivity}.
 */
@ViewScope
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private final AuthenticationManager authenticationManager;
    private final ModuleService moduleService;

    @Inject
    public MainPresenter(AuthenticationManager authenticationManager, ModuleService moduleService) {
        this.authenticationManager = authenticationManager;
        this.moduleService = moduleService;
    }

    @Override
    public void navigateToModules() {
        getView().navigateToModules();
    }

    @Override
    public void navigateToChats() {
        getView().navigateToChats();
    }

    @Override
    public void logout() {
        authenticationManager.logout();
    }

    @Override
    public void handleSwitch(int position) {
        if (position == MainPagerAdapter.PAGE_MODULE_LIST) {
            getView().showEnrollFab();
        } else if (position == MainPagerAdapter.PAGE_CHAT_LIST) {
            getView().hideEnrollFab();
        }
    }

    @Override
    public void showEnrollDialog() {
        getView().showEnrollDialog();
    }

    @Override
    public void enrollOnModule(CharSequence input) {
        String moduleID = input.toString();
        moduleService.enrollOnModule(moduleID, authenticationManager.getCurrentUser().getUid(), new AsyncCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean model) {
                if (model) {
                    getView().showSuccesfulEnrollmentMessage();
                }
            }

            @Override
            public void onError(String message) {
                getView().showFailureEnrollmentMessage();
            }
        });
    }
}
