package com.tongyuan.testmp1.helper;

import javax.validation.constraints.NotNull;

/**
 * Created by zhangcy on 2018/4/18
 */
public class PwdHelper {
    public static String getPwd(@NotNull String rawPwd){
        if(rawPwd.length()<6){
            String pwd = null;
            switch (rawPwd.length()){
                case 1: pwd="00000"+rawPwd;break;
                case 2: pwd="0000"+rawPwd;break;
                case 3: pwd="000"+rawPwd;break;
                case 4: pwd ="00"+rawPwd;break;
                case 5: pwd="0"+rawPwd;break;
            }
            return pwd;
        }else{
            return rawPwd.substring(rawPwd.length()-6);
        }
    }
}
