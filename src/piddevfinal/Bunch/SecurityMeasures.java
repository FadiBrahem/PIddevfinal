/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Bunch;

/**
 *
 * @author Fedi
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;

public class SecurityMeasures {
     public String encryptMessagetoMD5(String msg) throws Exception{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] dg = md5.digest(msg.getBytes());
        return String.format("%032x%n", new BigInteger(1, dg));
    }
}
