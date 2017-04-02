package com.example.eas212526.smartfridge;

/**
 * Created by Anjanavelil on 21-03-2017.
 */
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class Singleton {

    private static Singleton singletonInstance;
    private RequestQueue requestQueue;
    private Context ctx;

    private Singleton(Context context){
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized Singleton getSingletonInstance(Context context){
        if (singletonInstance == null){
            singletonInstance = new Singleton(context);
        }
        return singletonInstance;
    }

    public <T>void addToRequestQueue (Request<T> request){
        getRequestQueue().add(request);
    }
}

