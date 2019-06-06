
package cn.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTest {
    public static void main(String[] args) {
        System.out.println(isValidPassword("11"));
        System.out.println(isValidPassword("a2"));
        System.out.println(isValidPassword("c32"));
        System.out.println(isValidPassword("ce32"));
        System.out.println(isValidPassword("cR32@#@"));
        System.out.println(isValidPassword("AF32#"));
        System.out.println(isValidPassword("cA2"));
    }

/**
 * Purpose:必须包含：大写字符+小写字符+数字
 * @author changle
 * Create Time: 2019年6月6日 
 * @param password
 * @return
 * Version: 1.0
 */
    public static boolean isValidPassword(String password) {
        String regex = "^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{1,100}$";
        Pattern Password_Pattern = Pattern.compile(regex);
        Matcher matcher = Password_Pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

}
