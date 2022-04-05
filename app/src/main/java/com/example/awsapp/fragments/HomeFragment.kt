package com.example.awsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread
import com.amplifyframework.auth.AuthException
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.User
import com.example.awsapp.activities.MainActivity
import com.example.awsapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvUserNameHome.text = arguments?.getString("uName")

        Amplify.DataStore.query(
            User::class.java,
            { items ->
                while (items.hasNext()) {
                    val item = items.next()
                    Log.i("Amplify", "Queried item: " + item.id)
                }
            },
            { failure -> Log.e("Tutorial", "Could not query DataStore", failure) }
        )

        binding.btnLogout.setOnClickListener{

           Amplify.Auth.signOut(
                    this::onSignOutSuccess,
                    this::onSignOutError
                )
        }
    }

    private fun onSignOutError(e:AuthException) {
        runOnUiThread {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG)
                .show()
            Log.e("TAG", e.toString())
        }
    }

    private fun onSignOutSuccess() {
        val intent = Intent(activity, MainActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }
}