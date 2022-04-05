package com.example.awsapp

import android.R.attr
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.amazonaws.regions.Regions
import android.widget.Toast

import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler

import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler
import com.amazonaws.services.cognitoidentityprovider.model.SignUpResult
import java.lang.Exception
import android.R.attr.password
import com.amazonaws.mobileconnectors.cognitoidentityprovider.*
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler


public class Cognito(var appContext: Context) {

    private val poolID : String = "eu-central-1_xcQI9ZigX"
    private val clientID : String = "7709agtqhruv88s2tuts01t7l8"
    private val clientSecret : String = "17rbkpm2ri41a74jll329ur89mc2a60ab3vcgnn50a4oojh9npej"
    private val awsRegion: Regions = Regions.DEFAULT_REGION

    private var userPool: CognitoUserPool =
        CognitoUserPool(appContext, this.poolID, this.clientID, this.clientSecret, this.awsRegion)
    private var userAttributes: CognitoUserAttributes = CognitoUserAttributes()
    lateinit var userPwd: String

    fun signUpInBackground(userId: String?, password: String?) {
        userPool.signUpInBackground(userId, password, userAttributes, null, signUpCallback)
        //userPool.signUp(userId, password, this.userAttributes, null, signUpCallback);
    }

    private var signUpCallback: SignUpHandler = object : SignUpHandler {

        override fun onSuccess(user: CognitoUser?, signUpResult: SignUpResult?) {
            Log.i(TAG, "Sign-up success")
            Toast.makeText(appContext, "Sign-up success", Toast.LENGTH_LONG).show()

            /*if (signUpResult) {
                // This user must be confirmed and a confirmation code was sent to the user
                // cognitoUserCodeDeliveryDetails will indicate where the confirmation code was sent
                // Get the confirmation code from user
            } else {
                Toast.makeText(appContext, "Error: User Confirmed before", Toast.LENGTH_LONG).show()
                // The user has already been confirmed
            }*/
        }

        override fun onFailure(e: Exception) {
            Toast.makeText(appContext, e.message.toString(), Toast.LENGTH_LONG).show()
            Log.i(TAG, e.message.toString())
        }
    }

    fun confirmUser(userId: String?, code: String?) {
        val cognitoUser = userPool.getUser(userId)
        cognitoUser.confirmSignUpInBackground(code, false, confirmationCallback)
        //cognitoUser.confirmSignUp(code,false, confirmationCallback);
    }

    private var confirmationCallback: GenericHandler = object : GenericHandler{
        override fun onSuccess() {
            Toast.makeText(appContext, "User confirmed successfully!", Toast.LENGTH_LONG).show()
        }

        override fun onFailure(e: Exception?) {
            Toast.makeText(appContext, e?.message.toString(), Toast.LENGTH_LONG).show()
            Log.i(TAG, e?.message.toString())
        }

    }

    fun addAttribute(key:String, value:String){
        userAttributes.addAttribute(key, value)
    }

    fun userLogin(userId:String, password:String){
       // val cognitoUser: CognitoUser = userPool.user
        val cognitoUser = userPool.getUser(userId)
        this.userPwd = password
        cognitoUser.getSessionInBackground(authenticationHandler)
    }

    private var authenticationHandler:AuthenticationHandler = object : AuthenticationHandler {
        override fun onSuccess(userSession: CognitoUserSession?, newDevice: CognitoDevice?) {
            Toast.makeText(appContext,"Successful sign in!", Toast.LENGTH_LONG).show();
        }

        override fun getAuthenticationDetails(
            authenticationContinuation: AuthenticationContinuation?,
            userId: String?
        ) {
            val authenticationDetails = AuthenticationDetails(userId, userPwd, null)
            authenticationContinuation?.setAuthenticationDetails(authenticationDetails)
            authenticationContinuation?.continueTask()
        }

        override fun getMFACode(continuation: MultiFactorAuthenticationContinuation?) {
            continuation?.continueTask()
        }

        override fun authenticationChallenge(continuation: ChallengeContinuation?) {
            //
        }

        override fun onFailure(e: Exception?) {
            Toast.makeText(appContext, e?.message.toString(), Toast.LENGTH_LONG).show();
        }

    }

}