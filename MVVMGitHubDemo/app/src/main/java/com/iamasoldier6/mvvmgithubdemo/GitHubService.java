package com.iamasoldier6.mvvmgithubdemo;

import com.iamasoldier6.mvvmgithubdemo.viewmodel.Contributor;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author: Iamasoldier6
 * @date: 10/1/16.
 */

public interface GitHubService {

    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}
