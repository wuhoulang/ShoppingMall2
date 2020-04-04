//package com.atguigu.shoppingmall.utils;
//
//import android.util.Log;
//
//import com.example.haoji.phoneticsymbol.home.interf.ModelCallback;
//import com.example.haoji.phoneticsymbol.myContents.ContentsJson;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import okhttp3.logging.HttpLoggingInterceptor;
//
///**
// * Created by HAOJI on 2019/12/18.
// */
//
//public class OkHttpUtil {
//
//    private static OkHttpUtil instance;
//    private final OkHttpClient client;
//
//    public static OkHttpUtil getInstance(){
//        if (instance==null){
//            synchronized (OkHttpUtil.class){
//                if (instance==null){
//                    instance = new OkHttpUtil();
//                }
//            }
//        }
//        return instance;
//    }
//
//    public OkHttpUtil(){
//         client = new OkHttpClient().newBuilder()
//                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
//                .connectTimeout(10, TimeUnit.SECONDS)//设置请求超时时间
//                .writeTimeout(10,TimeUnit.SECONDS)//设置写入超时时间
//                .addInterceptor(new HttpLoggingInterceptor())//添加打印拦截器
//                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
//                .build();
//    }
//
//    //get请求
//    public void getOkHttp(String url, final ModelCallback modelCallback){
//        Request request = new Request.Builder().url(url).build();
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String string = response.body().string();
//                    Log.e("string", string);
//                    modelCallback.onSuccess(string);
//                }
//            }
//        });
//    }
//
////    post 请求
//
//    public void postOkHttp(String url, FormBody.Builder formBody, final ModelCallback modelCallback){
////        FormBody.Builder formBody =new FormBody.Builder();
////        formBody.add("name",account);
////        formBody.add("password",password);
//        Request request =new Request.Builder().url(url).post(formBody.build()).build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if(response.isSuccessful()) {//回调的方法执行在子线程。
//                    String responseData = response.body().string();
//                    JSONObject jsonObject = null;
//                    try {
//                        jsonObject = new JSONObject(responseData.toString());
//                        int code = jsonObject.getInt("Code");
//                        if (code==0){
//                            modelCallback.onSuccess(responseData);
//                        }else {
//                            modelCallback.onFailure(responseData);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    Log.e("responseData", "responseData:"+responseData);
//                }
//            }
//        });
//    }
//
//}
