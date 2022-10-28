package com.example.innocvproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.innocvproject.R
import com.example.innocvproject.databinding.FragmentSettingsBinding
import com.example.innocvproject.viewmodel.SettingsViewModel
import com.example.innocvproject.viewmodel.UserDetailViewModel

class SettingsFragment : Fragment(){

    //Viewmodels
    private lateinit var settingsViewModel: SettingsViewModel //Get api health info

    //Binding
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Get viewModels
        settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        //Inflate layout and get access to it
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        //Configure test api button
        binding.fragmentSettingsButtonTestServer.setOnClickListener{
            settingsViewModel.getHealth {
                //Callback
                when(it){
                    true -> Toast.makeText(context, R.string.settings_conexion_ok, Toast.LENGTH_SHORT).show()
                    false -> Toast.makeText(context, R.string.settings_conexion_error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}