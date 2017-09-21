package com.bawei.gekaixuan.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.bean.Basebean;
import com.bawei.gekaixuan.bean.RegisteBean;
import com.bawei.gekaixuan.net.Api;
import com.bawei.gekaixuan.net.OnNetListener;

import java.util.HashMap;
import java.util.Map;

public class User_registe extends Base_Regin implements View.OnClickListener {
    private ImageView back;
    private TextView name;
    private TextView pwd;
    private TextView pwdagain;
    private TextView email;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reg);
        initview();
    }

    private void initview() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        name = (TextView) findViewById(R.id.writenum);
        name.setOnClickListener(this);
        pwd = (TextView) findViewById(R.id.writepwd);
        pwd.setOnClickListener(this);
        pwdagain = (TextView) findViewById(R.id.writeagain);
        pwdagain.setOnClickListener(this);
        email = (TextView) findViewById(R.id.writeemial);
        email.setOnClickListener(this);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                AlertDialog.Builder builder = new AlertDialog.Builder(User_registe.this)
                        .setTitle("确认你的选择")
                        .setMessage("返回将清空你在输入的内容");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
                break;
            case  R.id.writenum:
                break;
            case  R.id.writepwd:
                break;
            case  R.id.writeagain:
                break;
            case  R.id.writeemial:
                break;
            case  R.id.register:
                if(!name.getText().toString().equals("")&&!pwd.getText().toString().equals("")&&
                        !pwdagain.getText().toString().equals("")&&!email.getText().toString().equals(""))
                {
                    if(!pwd.getText().toString().equals(pwdagain.getText().toString())){
                        return;
                    }
                    Map<String,String> registermap=new HashMap<>();
                    registermap.put("username",name.getText().toString());
                    registermap.put("password",pwd.getText().toString());
                    registermap.put("password_confirm",pwd.getText().toString());
                    registermap.put("email",email.getText().toString());
                    registermap.put("client","android");
                    httpUtil.post(User_registe.this, Api.USER_REGISTER, registermap, RegisteBean.class, new OnNetListener() {
                        @Override
                        public void onSuccess(Basebean basebean) {
                            RegisteBean bean= (RegisteBean) basebean;
                            if("200".equals(bean.getCode())){
                                Toast.makeText(User_registe.this,"注册成功了",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else{
                                String  error=bean.getDatas().getError();
                                Toast.makeText(User_registe.this,error.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(User_registe.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
