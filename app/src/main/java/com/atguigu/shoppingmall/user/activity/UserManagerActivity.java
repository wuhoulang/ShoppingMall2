package com.atguigu.shoppingmall.user.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HAOJI on 2020/4/6.
 */

public class UserManagerActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.tv_change_password)
    TextView tv_change_password;
    @Bind(R.id.tv_set_phone)
    TextView tv_set_phone;
    @Bind(R.id.tv_certification)
    TextView tv_certification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        ButterKnife.bind(this);
        tv_set_phone.setOnClickListener(this);
        tv_change_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change_password:
               AlertDialog alertDialog = new AlertDialog(this);
               alertDialog.show();
                break;
            case R.id.tv_set_phone:
                break;
            case R.id.tv_certification:
                break;
            default:
                break;
        }
    }
}
