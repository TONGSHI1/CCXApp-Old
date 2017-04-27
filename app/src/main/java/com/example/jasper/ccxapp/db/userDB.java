package com.example.jasper.ccxapp.db;

import android.util.Log;

import com.example.jasper.ccxapp.entitiy.UserInfo;
import com.example.jasper.ccxapp.interfaces.userBackListener;

import java.io.File;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class userDB {

    private static UserInfo userInfo;

    //需输入用户名，密码新建用户
    public static void addNewUser(String userName, String pwd, final userBackListener ubl) {
        JMessageClient.register(userName, pwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if(s.equals("Success")) {
                    ubl.showResult(true, "");
                }else{
                    ubl.showResult(false, s);
                }
            }
        });
    }

    //判断是否成功登陆
    public static void forUserLogin(String userName, final String password, final userBackListener ubl) {
        JMessageClient.login(userName, password, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if(i == 0){
                    ubl.showResult(true, "");
                }else{
                    ubl.showResult(false, s);
                }
            }
        });
    }

    //添加用户相关信息
    public static void addUserMessage(String imgPath, String nickName, String sex, String birthday, String address, String explain, final userBackListener ubl) {
        if(imgPath != null) {
            File file = new File(imgPath);
            if(file.exists()) {
                JMessageClient.updateUserAvatar(file, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        Log.i("user", "aaaaaaaaaaaafile" + i + "    " + s);
                        if (i != 0) {
                            ubl.showResult(false, "");
                        }
                    }
                });
            }
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(nickName);
        userInfo.setAddress(address);
        userInfo.setSignature(explain);
        if(nickName != null){
            JMessageClient.updateMyInfo(UserInfo.Field.nickname, userInfo, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    Log.i("user", "aaaaaaaaaaaanickname"+i+"    "+s);
                    if(i != 0){
                        ubl.showResult(false, s);
                    }
                }
            });
        }
        if(sex != null){
            if(sex.equals("male")) {
                userInfo.setGender(cn.jpush.im.android.api.model.UserInfo.Gender.male);
            }else{
                userInfo.setGender(cn.jpush.im.android.api.model.UserInfo.Gender.female);
            }
            JMessageClient.updateMyInfo(UserInfo.Field.gender, userInfo, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    Log.i("user", "aaaaaaaaaaaagender"+i+"    "+s);
                    if(i != 0){
                        ubl.showResult(false, s);
                    }
                }
            });
        }
        if(birthday != null){
            userInfo.setBirthday(Long.valueOf(birthday));
            JMessageClient.updateMyInfo(UserInfo.Field.birthday, userInfo, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    Log.i("user", "aaaaaaaaaaaabirthday"+i+"    "+s);
                    if(i != 0){
                        ubl.showResult(false, s);
                    }
                }
            });
        }
        if(address != null){
            JMessageClient.updateMyInfo(UserInfo.Field.address, userInfo, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    Log.i("user", "aaaaaaaaaaaaaddress"+i+"    "+s);
                    if(i != 0){
                        ubl.showResult(false, s);
                    }
                }
            });
        }

        if(explain != null){
            JMessageClient.updateMyInfo(UserInfo.Field.signature, userInfo, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    Log.i("user", "aaaaaaaaaaaasignture"+i+"    "+s);
                    if(i != 0){
                        ubl.showResult(false, s);
                    }
                }
            });
        }
    }

}