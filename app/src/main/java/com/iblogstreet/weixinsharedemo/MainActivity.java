package com.iblogstreet.weixinsharedemo;

import android.app.Activity;
import android.os.Bundle;

import com.iblogstreet.weixinlib.WeiXinShareBean;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeiXinShareBean weiXinShareBean = new WeiXinShareBean(this, "wxd930ea5d5a258f4f");
    }
}
