package com.example.awsapp

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.model.SimpleModelProvider

class AWSAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        try {
            //  val modelProvider : SimpleModelProvider = SimpleModelProvider.getInstance()
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.addPlugin((AWSApiPlugin()))

            Amplify.addPlugin(AWSCognitoAuthPlugin())

            Amplify.configure(applicationContext)
            Log.i("TAG", "configured")

        } catch (e : AmplifyException) {
            e.printStackTrace()
        }
        //Backend.initialize(applicationContext)
    }
}