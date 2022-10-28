package com.example.innocvproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.innocvproject.R
import com.example.innocvproject.databinding.FragmentUserDetailBinding
import com.example.innocvproject.viewmodel.UserDetailViewModel

class UserDetailFragment : Fragment(){

    //Viewmodel
    private lateinit var userDetailViewModel: UserDetailViewModel

    //Binding
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Get viewModels
        userDetailViewModel = ViewModelProvider(requireActivity()).get(UserDetailViewModel::class.java)

        //Inflate layout and get access to it
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)

        //Observe the viewmodel. If its changed, UI will be updated
        userDetailViewModel.getUser()!!.observe(viewLifecycleOwner, Observer {
            //Update UI
            binding.fragmentUserDetailUsername.setText(it.name)
            binding.fragmentUserDetailBirthdate.setText(it.birthdate)

            //Configure remove user button
            val user = it
            binding.fragmentUserDetailButtonDelete.setOnClickListener{
                //Remove user
                userDetailViewModel.removeUser(user.id) {
                    when(it){
                        //Callback
                        true -> Toast.makeText(context, R.string.user_detail_user_removed, Toast.LENGTH_SHORT).show()
                        false -> Toast.makeText(context, R.string.user_detail_error_removing_user, Toast.LENGTH_SHORT).show()
                    }
                }

                //Navigate back to home
                this.findNavController().navigate(R.id.navigation_users)
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}