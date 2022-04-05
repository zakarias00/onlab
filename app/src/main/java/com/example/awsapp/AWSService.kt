package com.example.awsapp

import android.content.Context
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.mobile.auth.core.IdentityManager
import com.amazonaws.mobile.config.AWSConfiguration
import com.amplifyframework.api.aws.sigv4.CognitoUserPoolsAuthProvider

class AWSService(context: Context) {

    private val configuration = AWSConfiguration(context)
    private val identityManager: IdentityManager = IdentityManager(context, configuration)
    private val credentialsProvider: AWSCredentialsProvider

    init {
        //identityManager.addSignInProvider(CognitoUserPoolsAuthProvider::class.java)
        IdentityManager.setDefaultIdentityManager(identityManager)
        credentialsProvider = identityManager.credentialsProvider
    }
}