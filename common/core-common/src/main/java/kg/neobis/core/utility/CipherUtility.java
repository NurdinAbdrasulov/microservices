package kg.neobis.core.utility;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CipherUtility {

    public static String encrypt(
            String str,
            String key,
            String algorithm
    ) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(str.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String decrypt(
            String str,
            String key,
            String algorithm
    ) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(str));

            return new String(decryptedBytes);
        } catch (Exception ex) {
            return null;
        }
    }
}
