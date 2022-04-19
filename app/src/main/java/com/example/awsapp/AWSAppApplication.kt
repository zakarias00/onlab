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
        configureAmplify()
    }


    private fun configureAmplify(){

        try {
            //Amplify.addPlugin((AWSApiPlugin()))

            Amplify.addPlugin(AWSCognitoAuthPlugin())

            //val dataStorePlugin = AWSDataStorePlugin.builder()
            Amplify.addPlugin(AWSDataStorePlugin())

            Amplify.configure(applicationContext)
            //Log.i("TAG", "configured")

        } catch (e : AmplifyException) {
            e.printStackTrace()
        }
        //Backend.initialize(applicationContext)
    }
}