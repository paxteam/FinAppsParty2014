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
import android.os.Build;

import java.io.File;

public class SessionManager {

    private Context context;
    private String mKey;

    public SessionManager(Context context, String key) {
        this.context = context;
        this.mKey = key;
    }

    public boolean storeSession(String user, String pass) {
        EncryptedFile f = new EncryptedFile(context,"session", true, mKey);
        String model = Build.MODEL;
        String manufacturer = Build.MANUFACTURER;
        String cpu = Build.CPU_ABI;
        String hardware = Build.HARDWARE;
        String host = Build.HOST;
        boolean a = f.write(model+";"+user+";"+cpu+";"+hardware+";"+pass+";"+host+";"+manufacturer+";");
        f.close();
        return a;
    }

    public String[] loadSession() {
        EncryptedFile f = new EncryptedFile(context, "session", false, mKey);
        String s = f.read();
        if(s== null) return null;
        String[] split = s.split("[;]");
        f.close();
        String model = split[0], user = split[1], cpu = split[2], hardware = split[3], pass = split[4], host = split[5], manufacturer = split[6];
        if(!model.equals(Build.MODEL) || !cpu.equals(Build.CPU_ABI) ||
           !hardware.equals(Build.HARDWARE) || !host.equals(Build.HOST) || !manufacturer.equals(Build.MANUFACTURER)) {
            return null;
        }
        return new String[]{user, pass};
    }

    public boolean thereIsASessionSaved() {
        EncryptedFile f = new EncryptedFile(context,"session",false, mKey);
        boolean a = !f.isEmptyFile();
        f.close();
        return a;
    }

    public void close() {
        File f = new File(context.getFilesDir(), "session");
        f.delete();
    }
}
