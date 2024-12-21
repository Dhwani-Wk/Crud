package com.example.internalassignment;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ApiHelper {

    private static final String BASE_URL = "http://10.0.2.2/InternalAssignment/";

    public static void sendRequest(Context context, String endpoint, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + endpoint;
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        queue.add(stringRequest);
    }
}
