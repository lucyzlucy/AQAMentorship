package data;

import environment.Environment;
import utils.CryptUtil;

public class CryptedPass {
    private static final String pathName = "user.password.key";
    private static final String encryptedValue = "user.password";


    public static String getDecodedPass() {
        return CryptUtil.decrypt(Environment.getEnvProperty(encryptedValue), Environment.getEnvProperty(pathName));
    }

    public static void changePass(String pass) {
        Environment.setEnvProperty(encryptedValue, CryptUtil.encrypt(pass, Environment.getEnvProperty(pathName)));
    }

}
