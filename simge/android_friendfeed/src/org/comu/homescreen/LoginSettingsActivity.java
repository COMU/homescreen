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

import java.util.prefs.Preferences;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Actividad para configurar el Login.
 * @author Raul Romero (raul@bgta.net)
 */
public class LoginSettingsActivity extends Activity {

    /* {@inheritDoc}. */
    @Override
    public final void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.loginsettings);
        setTitle(R.string.app_name);
        this.restorePreferences();

        TextView link = (TextView) findViewById(R.id.linkText);
        final Activity activity = this;
        link.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Toast.makeText(activity
                        , R.string.label_copy_remotekey
                        , Toast.LENGTH_LONG).show();

                WebView wv = new WebView(activity);

                wv.loadUrl("http://friendfeed.com/remotekey");
            }
        });
    }

    /* {@inheritDoc}. */
    @Override
    protected final void onStop() {
        super.onStop();
        savePreferences();
    }

 
    private void savePreferences() {
        Preferences1 pref = Preferences1.getInstance(this);
        EditText usernameEditText =
                (EditText) findViewById(R.id.usernameEditText);
        EditText remoteKeyEditText =
                (EditText) findViewById(R.id.remoteKeyEditText);

        pref.setUsername(usernameEditText.getText().toString());
        pref.setRemoteKey(remoteKeyEditText.getText().toString());
    }
   
    private void restorePreferences() {
        Preferences1 pref = Preferences1.getInstance(this);
        EditText usernameEditText =
                (EditText) findViewById(R.id.usernameEditText);
        EditText remoteKeyEditText =
                (EditText) findViewById(R.id.remoteKeyEditText);

        usernameEditText.setText(pref.getUsername());
        remoteKeyEditText.setText(pref.getRemoteKey());
    }
}
