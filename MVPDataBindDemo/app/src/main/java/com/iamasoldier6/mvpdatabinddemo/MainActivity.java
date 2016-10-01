package com.iamasoldier6.mvpdatabinddemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.android.annotations.NonNull;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.iamasoldier6.mvpdatabinddemo.databinding.ActivityMainBinding;
import com.iamasoldier6.mvpdatabinddemo.presenter.ContributorPresenter;
import com.iamasoldier6.mvpdatabinddemo.viewmodel.Contributor;

public class MainActivity extends MvpActivity<ContributorView, ContributorPresenter>
        implements ContributorView {

    private ProcessDialog dialog;
    private ActivityMainBinding binding; // ActivityMain 要与布局文件 activity_main 一致

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @NonNull
    @Override
    public ContributorPresenter createPresenter() {
        return new ContributorPresenter();
    }

    public void get(View view) {
        getPresenter().get("square", "retrofit");
    }

    public void change(View view) {
        if (binding.getContributor() != null) {
            binding.getContributor().setLogin("Iamasoldier6");
        }
    }

    @Override
    public void onLoadContributorStart() {
        showProgress();
    }

    @Override
    public void onLoadContributorComplete(Contributor contributor) {
        binding.setContributor(contributor);
        dismissProgress();
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

}
