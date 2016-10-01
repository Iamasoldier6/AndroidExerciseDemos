package com.iamasoldier6.mvpdatabinddemo.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.iamasoldier6.mvpdatabinddemo.ContributorView;
import com.iamasoldier6.mvpdatabinddemo.model.GitHubApi;
import com.iamasoldier6.mvpdatabinddemo.viewmodel.Contributor;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author: Iamasoldier6
 * @date: 10/1/16.
 */

public class ContributorPresenter extends MvpBasePresenter<ContributorView> {

    private Subscriber<Contributor> contributorSub = new Subscriber<Contributor>() {

        @Override
        public void onStart() {
            ContributorView view = getView();
            if (view != null) {
                view.onLoadContributorStart();
            }
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Contributor contributor) {
            ContributorView view = getView();
            if (view != null) {
                view.onLoadContributorComplete(contributor);
            }
        }
    };

    public void get(String owner, String repo) {
        GitHubApi.getContributors(owner, repo)
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<List<Contributor>, Contributor>() {

                    @Override
                    public Contributor call(List<Contributor> contributors) {
                        return contributors.get(0);
                    }
                })
                .subscribe(contributorSub);
    }
}
