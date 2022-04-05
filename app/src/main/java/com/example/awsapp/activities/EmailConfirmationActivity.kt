package com.example.awsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.result.AuthSignInResult
import com.amplifyframework.auth.result.AuthSignUpResult
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.Model
import com.amplifyframework.datastore.DataStoreException
import com.amplifyframework.datastore.DataStoreItemChange
import com.amplifyframework.datastore.generated.model.User
import com.example.awsapp.Cognito
import com.example.awsapp.databinding.ActivityEmailConfirmationBinding

class EmailConfirmationActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmailConfirmationBinding
    lateinit var cognito: Cognito

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmailConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("TAG", intent.getStringExtra("email").toString())
       // Log.i("TAG", getPassword())
        Log.i("TAG", getEmail())

        binding.btnConfirm.setOnClickListener{
            Amplify.Auth.confirmSignUp(
                //intent.getStringExtra("email").toString(),
                getEmail(),
                binding.tvConfCode.text.toString(),
                this::onSuccess,
                this::onError
            )

           // cognito.confirmUser(intent.getStringExtra("username"), binding.tvConfCode.text.toString())
        }

    }

    private fun onError(e: AuthException) {
        runOnUiThread {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG)
                .show()
            Log.e("TAG", e.toString())
        }
    }

    private fun onSuccess(authSignUpResult: AuthSignUpResult){
        val username = getEmail()
        val password = getPassword()
        Amplify.Auth.signIn(
            username,
            password,
            this::onLoginSuccess,
            this::onLoginError
        )

    }

    private fun onLoginError(e: AuthException) {
        runOnUiThread {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG)
                .show()
            Log.i("TAG", e.toString())
        }
    }

    private fun onLoginSuccess(authSignInResult: AuthSignInResult) {
        val userId = Amplify.Auth.currentUser.userId
        val userName = Amplify.Auth.currentUser.username

        val user: User = User.builder()
            .name(getUserName())
            .firstName(intent.getStringExtra("first_name").toString())
            .secondName(intent.getStringExtra("second_name").toString())
            .birthday(intent.getStringExtra("birth_date").toString())
            .school(intent.getStringExtra("school").toString())
            .email(getEmail())
            .build()
      /*  Amplify.DataStore.save(
            item,
            { success -> Log.i("Amplify", "Saved item: " + success.item().name) },
            { error -> Log.e("Amplify", "Could not save item to DataStore", error) }
        )*/

        Amplify.DataStore.save(
            //User.builder().id(userId).name(userName).build(),
            user,
            this::onSavedSuccess,
            this::onSaveError
        )
    }

    private fun onSaveError(e:DataStoreException) {
        runOnUiThread {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG)
                .show()
        }
    }
    private fun <T : Model?> onSavedSuccess(dataStoreItemChange: DataStoreItemChange<T>) {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun getUserName() :String{
        return intent.getStringExtra("username").toString()
    }

    private fun getEmail() : String{
        return intent.getStringExtra("email").toString()
    }

    private fun getPassword() : String{
        return intent.getStringExtra("pwd").toString()
    }
}