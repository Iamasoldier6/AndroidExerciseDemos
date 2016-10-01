package com.iamasoldier6.mvvmgithubdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iamasoldier6.mvvmgithubdemo.databinding.MvvmActivityMainBinding;
import com.iamasoldier6.mvvmgithubdemo.model.GitHubApi;
import com.iamasoldier6.mvvmgithubdemo.viewmodel.Contributor;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author: Iamasoldier6
 * @date: 10/1/16.
 */

public class MainActivity extends AppCompatActivity {

    private ProcessDialog dialog;
    private MvvmActivityMainBinding binding; // MvvmActivityMain 要与布局文件 mvvm_activity_main 一致

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.mvvm_activity_main);
    }

    private Subscriber<Contributor> contributorSub = new Subscriber<Contributor>() {

        @Override
        public void onStart() {
            showProgress();
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Contributor contributor) {
            binding.setContributor(contributor);
            dismissProgress();
        }
    };

    public void get(View view) {
        getContributors("square", "retrofit");
    }

    public void change(View view) {
        if (binding.getContributor() != null) {
            binding.getContributor().setLogin("Iamasoldier6");
        }
    }

    public void showProgress() {
        if (dialog == null) {
            dialog = new ProcessDialog(this);
        }

        dialog.showMessage("正在加载...");
    }

    public void dismissProgress() {
        if (dialog == null) {
            dialog = new ProcessDialog(this);
        }

        dialog.dismiss();
    }

    public void getContributors(String owner, String repo) {
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
