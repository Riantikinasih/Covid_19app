/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.web.rkinasih.covidapp_rianti.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import id.web.rkinasih.covidapp_rianti.BuildConfig;
import id.web.rkinasih.covidapp_rianti.model.news.NewsModel;
import id.web.rkinasih.covidapp_rianti.model.news.NewsResponse;
import id.web.rkinasih.covidapp_rianti.service.ApiEndpoint;
import id.web.rkinasih.covidapp_rianti.service.RetrofitServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<NewsModel>> liveData = new MutableLiveData<>();

    public void setNewsData() {
        Retrofit retrofit = RetrofitServiceApi.getRetrofitServiceNews();
        ApiEndpoint endpoint = retrofit.create(ApiEndpoint.class);
        Call<NewsResponse> call = endpoint.getNews("id", "health", BuildConfig.API_NEWS);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                liveData.setValue((ArrayList<NewsModel>) response.body().getGetArticles());
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        });
    }
    public LiveData<ArrayList<NewsModel>> getNewsData() {
        return liveData;
    }
}
