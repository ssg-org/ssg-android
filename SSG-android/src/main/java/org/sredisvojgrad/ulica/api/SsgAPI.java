package org.sredisvojgrad.ulica.api;


import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by harisdautovic on 22/04/14.
 */
public final class SsgAPI {


    public static String getHostName() {

        return "https://dev.ulica.ba/api/v1";
    }

    public static String buildSignature(HashMap<String, String> params) {

        //Signature
        String signature = "";

        //Sort params keys alphabeticaly
        List<String> sortedKeys = new ArrayList(params.keySet());
        Collections.sort(sortedKeys);


        for (int i = 0; i < sortedKeys.size(); i++) {

            signature += sortedKeys.get(i).toString();
            signature += "=";
            signature += params.get(sortedKeys.get(i));
            signature += "&";


        }

        signature = signature.substring(0, signature.length() - 1);

        String generated_signature = "";

        try {
            generated_signature = base64forData(signature);
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return generated_signature;
    }


    public static String base64forData(String signature) throws SignatureException, UnsupportedEncodingException {

        byte[] sha256 = hashMac(signature, "secret"); // ok
        String base64 = toBase64fromString(sha256);
        return base64;
    }

    private static String toBase64fromString(byte[] text) throws UnsupportedEncodingException {

        System.out.println("--- " + Base64.encodeToString(text, Base64.DEFAULT));
        return Base64.encodeToString(text, Base64.NO_WRAP);
    }


    public static byte[] hashMac(String text, String secretKey)
            throws SignatureException {

        try {

            Key sk = new SecretKeySpec(secretKey.getBytes(), HASH_ALGORITHM);
            Mac mac = Mac.getInstance(sk.getAlgorithm());
            mac.init(sk);
            return mac.doFinal(text.getBytes());

        } catch (NoSuchAlgorithmException e1) {
            // throw an exception or pick a different encryption method
            throw new SignatureException(
                    "error building signature, no such algorithm in device "
                            + HASH_ALGORITHM
            );
        } catch (InvalidKeyException e) {
            throw new SignatureException(
                    "error building signature, invalid key " + HASH_ALGORITHM);
        }
    }


    private static final String HASH_ALGORITHM = "HmacSHA256";

    public static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);


        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return sb.toString();
    }
}
