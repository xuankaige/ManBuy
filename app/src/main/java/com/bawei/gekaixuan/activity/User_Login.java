package com.bawei.gekaixuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.bean.Basebean;
import com.bawei.gekaixuan.bean.LoginBean;
import com.bawei.gekaixuan.net.Api;
import com.bawei.gekaixuan.net.OnNetListener;

import java.util.HashMap;
import java.util.Map;

public class User_Login extends Base_Login implements View.OnClickListener {

    private ImageView back;
    private TextView zhuce;
    private TextView name;
    private TextView pwd;
    private Button login;
    private TextView findpwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initid();

    }

    private void initid() {
        back = (ImageView) findViewById(R.id.back);
        zhuce = (TextView) findViewById(R.id.zhuce);
        name = (TextView) findViewById(R.id.name);
        pwd = (TextView) findViewById(R.id.pwd);
        login = (Button) findViewById(R.id.login);
        findpwd = (TextView) findViewById(R.id.findpwd);
        back.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        name.setOnClickListener(this);
        pwd.setOnClickListener(this);
        login.setOnClickListener(this);
        findpwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.zhuce:
                startActivity(new Intent(this, User_registe.class));
                break;
            case R.id.pwd:
                break;
            case R.id.name:
                break;
            case R.id.login:
                if (!name.getText().toString().equals("") && !pwd.getText().toString().equals("")) {
                    Map<String, String> loginmap = new HashMap<>();
                    loginmap.put("username", name.getText().toString());
                    loginmap.put("password", pwd.getText().toString());
                    loginmap.put("client", "android");

                    httpUtil.post(User_Login.this, Api.USER_LOGIN, loginmap, LoginBean.class, new OnNetListener() {
                        @Override
                        public void onSuccess(Basebean basebean) {
                            LoginBean bean = (LoginBean) basebean;
                            if ("200".equals(bean.getCode())) {
                                Toast.makeText(User_Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                String error = bean.getDatas().getError();
                                Toast.makeText(User_Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(User_Login.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
              break;


            case R.id.findpwd:
                break;
        }

    }
}
