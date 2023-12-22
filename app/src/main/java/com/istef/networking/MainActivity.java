package com.istef.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = VolleySingleton.getInstance(getApplicationContext()).
                getRequestQueue();

        String urlTodos = "https://jsonplaceholder.typicode.com/todos";
        String urlTodo1 = "https://jsonplaceholder.typicode.com/todos/1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                urlTodo1,
                null,
                response -> Log.d("RESPONSE: ", response.toString()),
                error -> Log.e("REQUEST ERROR: ", error.getMessage()));

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlTodos,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Log.d("RESPONSE: ", jsonObject.toString());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                },
                error -> Log.e("REQUEST ERROR: ", error.getMessage()));

        queue.add(jsonObjectRequest);
        queue.add(jsonArrayRequest);
    }
}