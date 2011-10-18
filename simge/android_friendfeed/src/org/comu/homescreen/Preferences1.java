/**
 * Android FriendFeed Client
 * Copyright (C) 2010 E & B Labs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.comu.homescreen;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Preferencias de la aplicación.
 * @author Raul Romero (raul@bgta.net)
 */
public class Preferences1 {
    
    public static final String PREFS_NAME = "android_friendfeed";
    
    public static final String PREF_KEY_USERNAME = "Username";
    
    public static final String PREF_KEY_REMOTEKEY = "RemoteKey";
   
    private static Preferences1 instance;
   
    private SharedPreferences settings;
    
    private Preferences1(Activity context) {
        if(settings == null) {
            settings = context.getSharedPreferences(PREFS_NAME, 0);
        }
    }
  
    public static Preferences1 getInstance(Activity context) {
        if (instance == null) {
            instance = new Preferences1(context);
        }

        return instance;
    }
    /**
     * Devuelve el nombre de usuario almacenado.
     * @return Nombre de Usuario
     */
    public String getUsername() {
        return settings.getString(PREF_KEY_USERNAME, null);
    }
    /**
     * Guarda el nombre de usuario en las preferencias.
     * @param username Nombre de usuario.
     */
    public void setUsername(String username) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_KEY_USERNAME, username);
        editor.commit();
    }
    /**
     * Devuelve el Remote Key almacenado.
     * @return Remote Key del usuario.
     */
    public String getRemoteKey() {
        return settings.getString(PREF_KEY_REMOTEKEY, null);
    }
    /**
     * Guarda el Remote Key en las preferencias.
     * @param remotekey RemoteKey del usuario.
     */
    public void setRemoteKey(String remotekey) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_KEY_REMOTEKEY, remotekey);
        editor.commit();
    }
}
