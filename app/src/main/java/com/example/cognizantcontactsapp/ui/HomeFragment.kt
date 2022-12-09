package com.example.cognizantcontactsapp.ui

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cognizantcontactsapp.a.b
import com.example.cognizantcontactsapp.databinding.FragmentHomeBinding
import com.example.cognizantcontactsapp.models.Contact
import com.example.cognizantcontactsapp.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso


class HomeFragment : Fragment(){

    //Viewmodels
    private lateinit var homeViewModel: HomeViewModel //Get users from the Rest API

    //Binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.

    private lateinit var allContacts : MutableList<Contact> //All the users. Filtering will be applied on this list

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Get viewModels
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        //Inflate layout and get access to it
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //Observe the viewmodel. If its changed, UI will be updated
        getActivity()?.getApplicationContext()?.getContentResolver()
            ?.let { homeViewModel.getContacts(it) }!!.observe(viewLifecycleOwner, Observer {
            allContacts = it.toMutableList() //Make it mutable
            // Set up the RecyclerView
            binding.fragmentUserListRecyclerView.setHasFixedSize(true)
            binding.fragmentUserListRecyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            val adapter = ContactCardRecyclerViewAdapter(allContacts)
            binding.fragmentUserListRecyclerView.adapter = adapter
        })

        val huhu = "p`ukvxukvzpfjdgqwkW" //1Y!-W
        val huhu2 = MainActivity.decY(huhu)

        //Configure post button
        binding.fragmentBtnPost.setOnClickListener {
            homeViewModel.postContacts(allContacts) {
                //Callback
                when(it){
                    null -> Toast.makeText(context, "Unable to post data.", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(context, "Data posted" , Toast.LENGTH_SHORT).show()
                }
            }
        }

        val string_1 = "CTF_JAVA_i"
        val bindingString = "005;9%%%,!='!=#06 ,0"

        //Configure Picasso img
        val imageUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhqI4txTRkj4_pCfr3NlNdbCbLYgX-nqjMX8wHEfx_A6Q8luaudlecd84nMDGZ1a4nwA0&usqp=CAU"
        val ivBasicImage: ImageView = binding.picassoImg
        Picasso.with(context).load(imageUri).into(ivBasicImage)

        //Configure Native string
        binding.nativeString.setText(MainActivity.helloWorld())

        val decrypted_str = b.c(bindingString)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}