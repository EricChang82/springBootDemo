
package cn.java;

public class SimpleTest {
    public static void main(String[] args) {
        System.out.println(isValidPassword("11"));
        System.out.println(isValidPassword("a2"));
        System.out.println(isValidPassword("c32"));
        System.out.println(isValidPassword("ce32"));
        System.out.println(isValidPassword("cR32@#@"));
        System.out.println(isValidPassword("AF32#"));
    }

    public static boolean isValidPassword(String value) {
        if (null == value) {
            return false;
        } else {
            String passWord = (String) value;
            if (passWord.matches("\\w+")) {
                String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$";
                return (passWord.matches(regex)) ? true : false;
            } else {
                return false;
            }
        }
    }

}
