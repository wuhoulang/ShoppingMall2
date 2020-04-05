package com.atguigu.shoppingmall.app;

/**
 * @version 1.0  
 * @author XiaoXun  
 * @date 2020年3月24日 下午4:38:47
 */

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog.Builder;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.interf.ModelCallback;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.OkHttpUtil;
import com.atguigu.shoppingmall.utils.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MySplashActivity extends Activity implements View.OnClickListener {
    private String LogTag = "TT";
    private boolean isCheckPermissions = false;
    private static int REQUEST_CODE = 1;
    private List<Integer> splashResIds = new ArrayList<>();
    private int splashIndex = 0;
    private ImageView imageView;
    private Button bt_splash;
    private AnimationSet animationSet;
    private Context mContext = MySplashActivity.this;
    String[] permissions = new String[]{"android.permission.READ_PHONE_STATE",
            "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                Log.e("MySplashActivity'", "--splashResIds:-----------------------" + splashResIds.get(0) + splashResIds.get(1));
//                Glide.with(MySplashActivity.this).load(splashResIds.get(splashIndex)).into(imageView);
//                Glide.with(mContext)
//                        .load(splashResIds.get(splashIndex))
//                        .into(imageView);
//                Log.e("MySplashActivity'", "--mHandler:-----------------------
                if (splashResIds.get(0) == R.drawable.xiao_splash_v_0) {
                    Glide.with(mContext)
                            .load("http://192.168.8.119:80/atguigu/game_img/xiao_splash_v_0.png")
                            .into(imageView);
                }

                if (splashResIds.get(1) == R.drawable.xiao_splash_v_1) {
                    Log.e("MySplashActivity", "---------splashResIdsdsfa-----55555555----");
                    Glide.with(mContext)
                            .load("http://192.168.8.119:80/atguigu/game_img/xiao_splash_v_1.png")
                            .into(imageView);
                }
            }
            return false;
        }
    });

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(1);
//        getWindow().addFlags(1024);
        // if (Build.VERSION.SDK_INT >= 23) {
        // this.needPermissions = getNeedPermissions();
        // } else {
        // this.needPermissions = new ArrayList<>();
        // }

//        String splashPrefix = ScreenUtils.isVerticalScreen(this) ? "xiao_splash_v_" : "xiao_splash_h_";
//        int splashIndex = 0;
//        if (findDrawableId(splashPrefix + splashIndex) != 0) {
//            for (; ; ) {
//                int id = findDrawableId(splashPrefix + splashIndex);
//                if (id == 0) {
//                    break;
//                }
//                this.splashResIds.add(Integer.valueOf(id));
//                splashIndex++;
//            }
//        }
//		this.imageView = new ImageView(this);
//		this.imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
//		this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//		this.imageView.setVisibility(4);
//		this.imageView.setBackgroundColor(getBackgroundColor());
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.iv_splash);
        bt_splash = findViewById(R.id.bt_splash);
        bt_splash.setOnClickListener(this);


//        Glide.with(mContext)
//                .load("http://192.168.8.119:80/atguigu/game_img/xiao_splash_v_1.png")
//                .into(imageView);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpUtil.getInstance().getOkHttp(Constants.BASE_URl_GAME_SPLASH, new ModelCallback() {
//                    @Override
//                    public void onSuccess(String s) {
//                        Log.e("MySplashActivity'", "--onSuccess--"+s);
//                        try {
//                            JSONObject jsonObject = new JSONObject(s);
//                            String content = jsonObject.getString("Content");
//                            JSONObject jsonObject2  = new JSONObject(content);
//                            String picture = jsonObject2.getString("picture");
//                            JSONArray jsonArray = new JSONArray(picture);
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject1  = (JSONObject) jsonArray.get(i);
//                                String picUrl = jsonObject1.getString("picUrl");
//                                Log.e("MySplashActivity'", "--picUrl:--"+picUrl);
//                                splashResIds.add(picUrl);
//                            }
//                            Log.e("MySplashActivity'", "--splashResIds.size():--"+splashResIds.size());
//                            showSplash();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(String responseData) {
//                        Log.e("MySplashActivity'", "--onFailure--"+responseData);
//                    }
//                });
//            }
//        }).start();

        showSplash();

    }

    private int findDrawableId(String resName) {
        return getResources().getIdentifier(resName, "drawable", getPackageName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LogTag, "DJ_GetPermissionsActivity-->onResume");
        if (this.isCheckPermissions) {
            requestPermissions(this.permissions);
            this.isCheckPermissions = false;
        }
    }

    private void showSplash() {
        Log.e("MySplashActivity", "--showSplash11111111");


        if (this.splashIndex < 2) {

//            this.imageView.setImageResource(((Integer) this.splashResIds.get(this.splashIndex)).intValue());
//            mHandler.sendEmptyMessage(1);
            Log.e("MySplashActivity", "--splashIndex:"+splashIndex);

            if (splashIndex== 0) {
                Glide.with(mContext)
                        .load("http://192.168.8.119:80/atguigu/game_img/xiao_splash_v_0.png")
                        .into(imageView);
                splashIndex += 1;
                 showSplash();
            }else if (splashIndex== 1) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(mContext)
                                .load("http://192.168.8.119:80/atguigu/game_img/xiao_splash_v_1.png")
                                .into(imageView);
                        splashIndex += 1;
                        showSplash();
                    }
                },5000);

            }

//            AlphaAnimation alpha1 = new AlphaAnimation(1.0F, 1.0F);
//            alpha1.setInterpolator(new DecelerateInterpolator());
//            alpha1.setDuration(2000L);
//            AlphaAnimation alpha2 = new AlphaAnimation(1.0F, 0F);
//            alpha2.setInterpolator(new AccelerateInterpolator());
//            alpha2.setStartOffset(500L);
//            alpha2.setDuration(5000L);
//            animationSet = new AnimationSet(false);
////            animationSet.addAnimation(alpha1);
//            animationSet.addAnimation(alpha2);
//            animationSet.setAnimationListener(new Animation.AnimationListener() {
//                public void onAnimationEnd(Animation animation) {
//                    Log.e("MySplashActivity", "-----onAnimationEnd-----------");
//                    splashIndex += 1;
////                    MySplashActivity.this.imageView.setVisibility(View.INVISIBLE);
////                    showSplash();
//                }
//
//                public void onAnimationRepeat(Animation animation) {
//                }
//
//                public void onAnimationStart(Animation animation) {
//                }
//            });
//            imageView.startAnimation(animationSet);
//            this.imageView.setVisibility(View.VISIBLE);
        } else {
            requestPermissions(this.permissions);
            Log.e("MySplashActivity'", "--requestPermissions:--");
        }
    }

    @SuppressLint({"NewApi"})
    public void requestPermissions(@NonNull String[] permissions) {
        List<String> requestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            Log.d(LogTag, "DJ_GetPermissionsActivity-->checkSelfPermission---111");
            if (ContextCompat.checkSelfPermission(this, permission) != 0) {
                Log.d(LogTag, "checkSelfPermission---222");
                requestPermissionList.add(permission);
            }
        }
        if (requestPermissionList.isEmpty()) {
            Log.d(LogTag, "DJ_GetPermissionsActivity-->checkSelfPermission---333");
            startActivity();
        } else {
            requestPermissions((String[]) requestPermissionList.toArray(new String[requestPermissionList.size()]),
                    REQUEST_CODE);
            // ActivityCompat.requestPermissions(this,
            // (String[]) requestPermissionList.toArray(new
            // String[requestPermissionList.size()]), REQUEST_CODE);
        }
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions != null) {
            for (int i = 0; i < permissions.length; i++) {
                Log.d("TT", "DJ_GetPermissionsActivity-->requestCode:" + requestCode + ",permissions[" + i + "]:"
                        + permissions[i] + ",grantResults:" + grantResults[i]);
            }
        }

        Log.d("TT", "DJ_GetPermissionsActivity-->requestCode:" + requestCode + ",permissions:" + permissions
                + ",grantResults:" + grantResults);
        if (requestCode == REQUEST_CODE && grantResults.length > 0) {
            final List<String> deniedPermissionList = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != 0) {
                    deniedPermissionList.add(permissions[i]);
                }
            }
            if (deniedPermissionList.isEmpty()) {
                startActivity();
                return;
            }
            for (String deniedPermission : deniedPermissionList) {
                if (!shouldShowRequestPermissionRationale(deniedPermission)) {
                    new Builder(this).setMessage("游戏需要您授权，如果没有权限，无法正常游戏！！").setCancelable(false)
                            .setPositiveButton("设置", new OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    MySplashActivity.this.toAppDetail();
                                    MySplashActivity.this.isCheckPermissions = true;
                                }
                            }).setNegativeButton("退出", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    }).create().show();
                    return;
                }
            }
            new Builder(this).setMessage("游戏需要您授权，如果没有权限，无法正常游戏！").setCancelable(false)
                    .setPositiveButton("设置", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MySplashActivity.this.requestPermissions(
                                    (String[]) deniedPermissionList.toArray(new String[deniedPermissionList.size()]));
                        }
                    }).setNegativeButton("退出", new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    System.exit(0);
                }
            }).create().show();
        }
    }

    private void startActivity() {
        String firstActivity = "com.atguigu.shoppingmall.app.MainActivity";
        Intent intent = new Intent();
        intent.setClassName(this, firstActivity);
        this.startActivity(intent);
        this.finish();
//		Intent intent = new Intent("com.xiao.demo.START");
//		intent.setPackage(getPackageName());
//		startActivity(intent);
//		this.finish();
    }

    // private void showPermissionsPromptDialog() {
    // CharSequence appName =
    // getApplicationInfo().loadLabel(getPackageManager());
    // StringBuilder message = new StringBuilder().append("【").append(appName)
    // .append("】需要以下权限，以正常使用。\n\n设置路径：设置->应用->").append(appName).append("->权限\n");
    // List<String> descriptions = new ArrayList<>();
    // for (String permission : this.ungrantedPermissions) {
    // String description = (String) NEED_PERMISSIONS.get(permission);
    // if (!descriptions.contains(description)) {
    // descriptions.add(description);
    // message.append("\n•" + description);
    // }
    // }
    // AlertDialog dialog = new
    // AlertDialog.Builder(this).setTitle("权限").setMessage(message)
    // .setNegativeButton("退出游戏", new DialogInterface.OnClickListener() {
    // public void onClick(DialogInterface dialog, int which) {
    // MySplashActivity.this.finish();
    // }
    // }).setPositiveButton("去设置", new DialogInterface.OnClickListener() {
    // public void onClick(DialogInterface dialog, int which) {
    // MySplashActivity.this.toAppDetail();
    // }
    // }).create();
    // dialog.setCanceledOnTouchOutside(false);
    // dialog.show();
    // }

    private void toAppDetail() {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
            startActivity(localIntent);
        } catch (Exception e) {
            startActivity(new Intent("android.settings.SETTINGS"));
        }
    }

    public int getBackgroundColor() {
        return -1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_splash:
//                 startActivity();
//                 finish();
                requestPermissions(this.permissions);
                break;
            default:
                break;
        }
    }
}
