package com.example.awsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.auth.result.AuthSignInResult
import com.amplifyframework.auth.result.AuthSignUpResult
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.Model
import com.amplifyframework.datastore.DataStoreException
import com.amplifyframework.datastore.DataStoreItemChange
import com.amplifyframework.datastore.generated.model.User
import com.example.awsapp.Cognito
import com.example.awsapp.databinding.ActivitySignUpBinding
import java.sql.Timestamp
import java.text.DateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.properties.Delegates

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var bdate: String
    lateinit var authentication: Cognito

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        val datePicker = binding.datePicker
        val today = Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)) { _, year, month, day ->
            val month = month + 1
            //  bdate = "$day/$month/$year"

        }

        val options = AuthSignUpOptions.builder()
           .userAttribute(AuthUserAttributeKey.givenName(), binding.etFName.text.toString())
           .userAttribute(AuthUserAttributeKey.familyName(), binding.etSName.text.toString())
           .userAttribute(AuthUserAttributeKey.birthdate(), "2000-12-05")
           .userAttribute(AuthUserAttributeKey.custom("school"), binding.etSchool.text.toString())
           .userAttribute(AuthUserAttributeKey.email(), binding.etEmail.text.toString())
           .build()


            /*   val options = AuthSignUpOptions.builder()
                   .userAttribute(AuthUserAttributeKey.givenName(), "szia")
                   .userAttribute(AuthUserAttributeKey.familyName(), "mia")
                   .userAttribute(AuthUserAttributeKey.birthdate(), "2000-12-05")
                   .userAttribute(AuthUserAttributeKey.custom("school"), "BME")
                   .userAttribute(AuthUserAttributeKey.email(), "annazakarias00@gmail.com")
                   .build()

                      Log.i("TAG", binding.etSName.text.toString())
         Log.i("TAG", binding.etFName.text.toString())
         Log.i("TAG", binding.etEmail.text.toString())
        // Log.e("TAG", bdate)

       */
     binding.btnSubmit.setOnClickListener{
            Amplify.Auth.signUp(
                binding.etUserName2.text.toString(),
                binding.etPassword2.text.toString(),
                //AuthSignUpOptions.builder().userAttributes(
                    options,
                //).build(),
                this::onSignUpSuccess,
                this::onSignUpError
            )

        }
    }



    private fun onSignUpError(e: AuthException) {
        runOnUiThread {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG)
                .show()
            Log.e("TAG", e.toString())
        }
    }

    private fun onSignUpSuccess(authSignUpResult: AuthSignUpResult) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("email", binding.etEmail.text.toString())
        intent.putExtra("pwd", binding.etPassword2.text.toString())
        intent.putExtra("username", binding.etUserName2.text.toString())
        intent.putExtra("first_name", binding.etFName.text.toString())
        intent.putExtra("second_name", binding.etSName.text.toString())
        intent.putExtra("school", binding.etSchool.text.toString())
        intent.putExtra("birth_date", "2000-12-05")

        Amplify.DataStore.save(
            User.builder()
                //.id(Amplify.Auth.currentUser.userId)
                .name(binding.etUserName2.text.toString())
                .email(binding.etEmail.text.toString())
                .birthday("2000-12-05")
                .firstName(binding.etFName.text.toString())
                .secondName(binding.etSName.text.toString())
                .school(binding.etSchool.text.toString())
                .password(binding.etPassword2.text.toString())
                .build(),
            this::onSavedSuccess,
            this::onSaveError
        )

        startActivity(intent)
    }

    private fun <T : Model?> onSavedSuccess(dataStoreItemChange: DataStoreItemChange<T>) {
    Log.i("TAG", "juhuu")
    /*Amplify.Auth.signIn(
            binding.etUserName2.text.toString(),
            binding.etPassword2.text.toString(),
            this::onSignInSuccess,
            this::onSignInError

        )*/
    }

   /* private fun onSignInError(e: AuthException) {
        runOnUiThread {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG)
                .show()
            Log.e("TAG", e.toString())
        }
    }

    private fun onSignInSuccess(authSignInResult: AuthSignInResult) {
        val intent = Intent(this, EmailConfirmationActivity::class.java)
        intent.putExtra("email", binding.etEmail.text.toString())
        intent.putExtra("pwd", binding.etPassword2.text.toString())
        intent.putExtra("username", binding.etUserName2.text.toString())
        intent.putExtra("first_name", binding.etFName.text.toString())
        intent.putExtra("second_name", binding.etSName.text.toString())
        intent.putExtra("school", binding.etSchool.text.toString())
        intent.putExtra("birth_date", "2000-12-05")

        startActivity(intent)
    }*/

    private fun onSaveError(e: DataStoreException) {
        runOnUiThread {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG)
                .show()

        }
    }
}