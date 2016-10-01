package com.iamasoldier6.mvpdatabinddemo;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.iamasoldier6.mvpdatabinddemo.viewmodel.Contributor;

/**
 * @author: Iamasoldier6
 * @date: 10/1/16.
 */

public interface ContributorView extends MvpView {
    void onLoadContributorStart();

    void onLoadContributorComplete(Contributor topContributor);
}
