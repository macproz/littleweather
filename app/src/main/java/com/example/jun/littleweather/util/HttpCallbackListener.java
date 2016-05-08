package com.example.jun.littleweather.util;

/**
 * Created by jun on 16/4/30.
 */
public interface HttpCallbackListener {

    void onFinish(String response);
    void onError(Exception e);
}
