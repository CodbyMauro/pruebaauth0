package com.example.auth0;

import android.content.Context;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import com.auth0.android.Auth0;
import com.auth0.android.provider.WebAuthProvider;

public class Auth0Cordova extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("login")) {
            this.login(callbackContext);
            return true;
        }
        return false;
    }

    private void login(CallbackContext callbackContext) {
        Context context = cordova.getActivity().getApplicationContext();
        String clientId = context.getString(context.getResources().getIdentifier("com_auth0_client_id", "string", context.getPackageName()));
        String domain = context.getString(context.getResources().getIdentifier("com_auth0_domain", "string", context.getPackageName()));

        Auth0 auth0 = new Auth0(clientId, domain);
        WebAuthProvider.login(auth0)
                .start(cordova.getActivity(), new AuthCallback(callbackContext));
    }
}