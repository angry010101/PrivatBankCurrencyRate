package com.example.s1.privat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "debug";
    private static PrivatApi privatApi;
    private Retrofit retrofit;
    TextView alerttext;
    RecyclerView recyclerView;
    ArrayList<CurrencyModel> posts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.privatbank.ua/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        privatApi = retrofit.create(PrivatApi.class);
        posts = new ArrayList<CurrencyModel>();
        alerttext = (TextView) findViewById(R.id.textView);
        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostsAdapter adapter = new PostsAdapter(posts);
        recyclerView.setAdapter(adapter);

        /*Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");*/
        String d = "12.02.2017";//dateFormat.format().toString();
        getandupdatedata(d);
    }

    private void getandupdatedata(String date){
        getApi().getData("", date).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                Log.d(TAG, "onResponse: ");
                alerttext.setText("");
                if (response.body().equals(null)){
                    Log.d(TAG, "onResponse: returned null");
                    return;
                }
                if (response.body().getExchangeRate().isEmpty()) {
                    alerttext.setText("Cannot show the data. Json is empty");
                };
                posts.addAll(response.body().getExchangeRate());
             /*   Iterator<CurrencyModel> iter = posts.iterator();
                while (iter.hasNext()){
                    iter.next();
                }*/
                recyclerView.getAdapter().notifyDataSetChanged();
                //Данные успешно пришли, но надо проверить response.body() на null
            }
            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
                //Произошла ошибка
            }
        });
    }

    public static PrivatApi getApi() {
        return privatApi;
    }
}
