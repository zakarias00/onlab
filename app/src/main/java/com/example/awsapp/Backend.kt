package com.example.awsapp

import android.app.Activity
import android.content.Context
import android.service.autofill.UserData
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.AuthChannelEventName
import com.amplifyframework.auth.AuthException
import com.amazonaws.mobile.auth.core.signin.SignInPermissionsHandler
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.result.AuthSessionResult
import com.amplifyframework.auth.result.AuthSignInResult
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.InitializationStatus
import com.amplifyframework.hub.HubChannel
import com.amplifyframework.hub.HubEvent

object Backend {

    private const val TAG = "Backend"

    fun initialize(applicationContext: Context) : Backend{
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i(TAG, "initialized Amplify")
        } catch(e: AmplifyException) {
            Log.e(TAG, "Could not initialized Amplify", e)
        }

        Log.i(TAG, "registering hub event")


        //login
        Amplify.Hub.subscribe(HubChannel.AUTH) { hubEvent: HubEvent<*> ->

            when (hubEvent.name) {
                InitializationStatus.SUCCEEDED.toString() -> {
                    Log.i(TAG, "Amplify successfully initialized")
                }
                InitializationStatus.FAILED.toString() -> {
                    Log.i(TAG, "Amplify initialization failed")
                }
                else -> {
                    when (AuthChannelEventName.valueOf(hubEvent.name)) {
                        AuthChannelEventName.SIGNED_IN -> {
                            updateUserData(true)
                            Log.i(TAG, "HUB : SIGNED_IN")
                        }
                        AuthChannelEventName.SIGNED_OUT -> {
                            updateUserData(false)
                            Log.i(TAG, "HUB : SIGNED_OUT")
                        }
                        else -> Log.i(TAG, """HUB EVENT:${hubEvent.name}""")
                    }
                }
            }
        }


        //if the user was signed in
        Log.i(TAG, "retrieving session status")

        Amplify.Auth.fetchAuthSession(
            { result ->
                Log.i(TAG, result.toString())
                val cognitoAuthSession = result as AWSCognitoAuthSession
                // update UI
                this.updateUserData(cognitoAuthSession.isSignedIn)
                when (cognitoAuthSession.identityId.type) {
                    AuthSessionResult.Type.SUCCESS ->  Log.i(TAG, "IdentityId: " + cognitoAuthSession.identityId.value)
                    AuthSessionResult.Type.FAILURE -> Log.i(TAG, "IdentityId not present because: " + cognitoAuthSession.identityId.error.toString())
                }
            },
            { error -> Log.i(TAG, error.toString()) }
        )

        return this

    }

    private fun updateUserData(withSignedInStatus : Boolean) {
      // UserData.setSignedIn(withSignedInStatus)
        //UserData.is
    }

    fun signOut() {
        Log.i(TAG, "Initiate Sign out Sequence")

        Amplify.Auth.signOut(
            { Log.i(TAG, "Signed out!") },
            { error -> Log.e(TAG, error.toString()) }
        )
    }

    fun signIn(callingActivity: Activity) {
        Log.i(TAG, "Initiate Sign in Sequence")

        Amplify.Auth.signInWithWebUI(
            callingActivity,
            { result: AuthSignInResult ->  Log.i(TAG, result.toString()) },
            { error: AuthException -> Log.e(TAG, error.toString()) }
        )
    }

}