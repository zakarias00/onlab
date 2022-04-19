package com.example.awsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.awsapp.R
import com.example.awsapp.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()
        val messageFragment = MessageFragment()
        val searchFragment = SearchFragment()

        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("pwd")

        val bundle = Bundle()
        bundle.putString("uName", username)
        bundle.putString("eMail", email)
        bundle.putString("password", password)
        homeFragment.arguments = bundle
        profileFragment.arguments = bundle

        setCurrentFragment(homeFragment)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->setCurrentFragment(homeFragment)
                R.id.messages ->setCurrentFragment(messageFragment)
                R.id.search ->setCurrentFragment(searchFragment)
                R.id.profile ->setCurrentFragment(profileFragment)

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}