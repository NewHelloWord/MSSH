package utils;

import java.util.Random;

/**
 * Created by Yao on 2016/8/5.
 */
public class VerificationUtil {

    public static VerificationUtil vu = null;

    public static synchronized VerificationUtil getInstance(){
        if(vu == null){
            vu = new VerificationUtil();
        }
        return vu;
    }

    public static void main(String[] args) {
        for(int i=0;i<1000;i++)
            VerificationUtil.getInstance().getCode();
    }

    public String getCode(){
        Random random = new Random();
        double s = random.nextDouble();
        String code = String.valueOf(s).substring(2,8);
        return code;
    }
}
