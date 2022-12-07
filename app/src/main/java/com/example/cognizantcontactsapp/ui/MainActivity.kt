package com.example.cognizantcontactsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.cognizantcontactsapp.R
import com.example.cognizantcontactsapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    companion object {
        init {
            System.loadLibrary("ndktest")
        }

        external fun helloWorld(): String
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        //Set content view
        /*binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)*/
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomeFragment>(R.id.container)
            }
        }
    }

}