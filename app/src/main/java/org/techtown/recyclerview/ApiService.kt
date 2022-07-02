package org.techtown.recyclerview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users/{user}")
    fun getUser(@Path("user") user : String) : Call<GetUserResp>


    @GET("/repos/{owner}/{repo}")
    fun getRepo(@Path("owner") owner : String, @Path("repo") repo : String) : Call<GetRepoUrlResp>
}