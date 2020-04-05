package com.atguigu.shoppingmall.interf;

/**
 * Created by HAOJI on 2020/4/5.
 */

public interface ModelCallback {

    void onSuccess(String s);

    void onFailure(String responseData);

}
