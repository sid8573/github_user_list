package com.example.gu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    EditText query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.get);







        mRecyclerView = findViewById(R.id.rvUser);
        mLayoutManager = new LinearLayoutManager(this);

//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<data> arrayList = new ArrayList<>();
                arrayList = loadUser();
                mAdapter = new gAdapter(arrayList);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public ArrayList<data> loadUser() {
        ArrayList<data> arrayList = new ArrayList<>();
        query = findViewById(R.id.etView);


//        arrayList.add(new data("https://pocket-image-cache.com/direct?resize=w2000&url=https%3A%2F%2Fhbr.org%2Fresources%2Fimages%2Farticle_assets%2F2018%2F03%2Fmar18_15_151815075.jpg", "Sadiq", "1234"));
//        arrayList.add(new data("https://pocket-image-cache.com/direct?resize=w2000&url=https%3A%2F%2Fhbr.org%2Fresources%2Fimages%2Farticle_assets%2F2018%2F03%2Fmar18_15_151815075.jpg", "Sadiq", "1234"));
//        arrayList.add(new data("https://pocket-image-cache.com/direct?resize=w2000&url=https%3A%2F%2Fhbr.org%2Fresources%2Fimages%2Farticle_assets%2F2018%2F03%2Fmar18_15_151815075.jpg", "Sadiq", "1234"));


          String Name = query.getText().toString();

        String url = "https://api.github.com/search/users?q=" + Name;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {


                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()) {
                    String myResponse = response.body().string();

                       MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject obj = new JSONObject(myResponse);

                                JSONArray items = obj.getJSONArray("items");

                                query.setText("List of your search");

                                for (int i = 0; i < items.length(); i++) {
                                    JSONObject myObj = items.getJSONObject(i);
                                    String IMG = myObj.getString("avatar_url");
                                    String NAME = myObj.getString("login");
                                    Integer LOGIN = myObj.getInt("id");
                                    arrayList.add(new data(IMG, NAME, LOGIN.toString()));
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                }


            }
        });

        return arrayList;
    }
}