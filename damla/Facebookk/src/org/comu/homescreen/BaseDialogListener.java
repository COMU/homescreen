package org.comu.homescreen;

import org.comu.homescreen.*;
import org.comu.homescreen1.DialogError;
import org.comu.homescreen1.Facebook.DialogListener;
import org.comu.homescreen1.FacebookError;
/**
 * Skeleton base class for RequestListeners, providing default error 
 * handling. Applications should handle these error conditions.
 *
 */
public abstract class BaseDialogListener implements DialogListener {

    public void onFacebookError(FacebookError e) {
        e.printStackTrace();
    }

    public void onError(DialogError e) {
        e.printStackTrace();        
    }

    public void onCancel() {        
    }
    
}
