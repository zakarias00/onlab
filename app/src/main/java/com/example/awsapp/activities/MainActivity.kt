package com.example.awsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.result.AuthSignInResult
import com.amplifyframework.core.Amplify
import com.example.awsapp.Cognito
import com.example.awsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*        Amplify.addPlugin(AWSCognitoAuthPlugin())
        Amplify.configure(applicationContext)

        Amplify.Auth.fetchAuthSession(
            { Log.i("AmplifyQuickstart", "Auth session = $it") },
            { error -> Log.e("AmplifyQuickstart", "Failed to fetch auth session", error) }
        )
*/
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {

         /*   val authentication : Cognito = Cognito(applicationContext)
            authentication.userLogin(
                binding.etUserName1.text.toString(),
                binding.etPassword1.text.toString()
            )

            Amplify.Auth.signIn(binding.etUserName1.text.toString(),  binding.etPassword1.text.toString(),
                { result ->
                    if (result.isSignInComplete) {
                        Log.i("AuthQuickstart", "Sign in succeeded")
                    } else {
                        Log.i("AuthQuickstart", "Sign in not complete")
                    }
                },
                { Log.e("AuthQuickstart", "Failed to sign in", it) }
            )*/


             Amplify.Auth.signIn(
               binding.etUserName1.text.toString(),
               binding.etPassword1.text.toString(),
               this::onLoginSuccess,
               this::onLoginError
               )
              }


    }

    private fun onLoginSuccess(authSignInResult: AuthSignInResult){
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("username", binding.etUserName1.text.toString())
        intent.putExtra("pwd", binding.etPassword1.text.toString())
        startActivity(intent)
    }

    private fun onLoginError(e:AuthException) {
        runOnUiThread {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG)
                .show()
        }
    }

}