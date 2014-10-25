/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml.io;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptedFile {

    private CipherOutputStream cos;
    private CipherInputStream cis;

    private Cipher cipher;
    private SecretKey secKey;

    private Context context;

    public EncryptedFile(Context c, String name, boolean writing, String key) {
        try {
            this.context = c;
            cipher = Cipher.getInstance("AES");
            secKey = new SecretKeySpec(key.getBytes(), "AES");
            if(writing) {
                FileOutputStream fos = c.openFileOutput(name, Context.MODE_PRIVATE);
                cos = new CipherOutputStream(fos, cipher);
            }else{
                FileInputStream fis = c.openFileInput(name);
                cis = new CipherInputStream(fis, cipher);
            }
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        } catch (FileNotFoundException e) {
        }
    }

    public boolean write(String s) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secKey);
            cos.write(s.getBytes(),0,s.length());
            cos.flush();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public String read() {
        byte[] array = new byte[300];
        try {
            cipher.init(Cipher.DECRYPT_MODE, secKey);
            cis.read(array);
            return new String(array);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isEmptyFile() {
        String a = read();
        if(a == null) return true;
        return read().equals("");
    }


    public void close() {
        try {
            if(cis != null) cis.close();
            if(cos != null) cos.close();
        } catch (IOException e) {}
    }
}
