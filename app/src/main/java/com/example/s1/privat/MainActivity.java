package com.example.s1.privat;

import android.app.ActionBar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
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
    private static TextView alerttext;
    private static RecyclerView recyclerView;
    private static ArrayList<CurrencyModel> posts;
    public static String d = "12.02.2017";
    public static Date date1;
    private static android.support.v7.app.ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
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
        date1 = new Date();
        /*Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");*/
        getReady();
    }

    public static void getReady(){//dateFormat.format().toString();
        posts.clear();
        recyclerView.getAdapter().notifyDataSetChanged();
        getandupdatedata(d);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.datebtnmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                DialogFragment dateDialog = new DatePicker();
                dateDialog.show(getSupportFragmentManager(), "datePicker");
                break;
        }
        return true;
    }


    private static void getandupdatedata(final String date){
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
                    alerttext.setText("Cannot show the data.\nJson is empty");
                    posts.clear();
                    actionBar.setTitle("");
                    recyclerView.getAdapter().notifyDataSetChanged();
                };
                date1 = new Date();
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    date1 = df.parse(response.body().getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                    date1 = new Date();
                }
                actionBar.setTitle(response.body().getDate());
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
