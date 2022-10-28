package com.example.innocvproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innocvproject.R
import com.example.innocvproject.databinding.FragmentHomeBinding
import com.example.innocvproject.models.User
import com.example.innocvproject.viewmodel.HomeViewModel
import com.example.innocvproject.viewmodel.UserDetailViewModel

class HomeFragment : Fragment(), UserListClickListener{

    //Viewmodels
    private lateinit var userDetailViewModel: UserDetailViewModel //Set user info for the user detail UI
    private lateinit var homeViewModel: HomeViewModel //Get users from the Rest API

    //Binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.

    private lateinit var allUsers : MutableList<User> //All the users. Filtering will be applied on this list

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Get viewModels
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        userDetailViewModel = ViewModelProvider(requireActivity()).get(UserDetailViewModel::class.java)

        //Inflate layout and get access to it
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //Observe the viewmodel. If its changed, UI will be updated
        homeViewModel.getUsers()!!.observe(viewLifecycleOwner, Observer {
            allUsers = it.toMutableList() //Make it mutable
            // Set up the RecyclerView
            binding.fragmentUserListRecyclerView.setHasFixedSize(true)
            binding.fragmentUserListRecyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            val adapter = UserCardRecyclerViewAdapter(allUsers,this)
            binding.fragmentUserListRecyclerView.adapter = adapter
        })

        //Configure list filtering
        binding.fragmentHomeSearch.doOnTextChanged{ text, start, before, count ->
            // Set up the RecyclerView
            val adapter = UserCardRecyclerViewAdapter(allUsers.filter { text.toString().lowercase() in it.name.lowercase() },this)
            binding.fragmentUserListRecyclerView.adapter = adapter
        }

        //Configure refresh button
        binding.fragmentBtnRefresh.setOnClickListener {
            homeViewModel.getUsers()
        }

        return binding.root
    }

    //Behaviour when a user card its clicked
    override fun onUserListItemClick(view: View, user: User) {
        userDetailViewModel.setUser(user)
        this.findNavController().navigate(R.id.navigation_user_detail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}