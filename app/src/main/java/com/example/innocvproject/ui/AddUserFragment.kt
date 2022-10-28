package com.example.innocvproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.innocvproject.R
import com.example.innocvproject.databinding.FragmentAddUserBinding
import com.example.innocvproject.viewmodel.AddUserViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class AddUserFragment : Fragment() {

    //Viewmodels
    private lateinit var addUserViewModel: AddUserViewModel

    //Binding
    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Get viewModel
        addUserViewModel = ViewModelProvider(this).get(AddUserViewModel::class.java)

        //Inflate layout and get access to it
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)

        //Configure date and time picking
        binding.fragmentAddUserInsertBirthDate.setOnClickListener {
            //Create datePicker and timePicker
            val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select date").build()

            val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H).setHour(12).setMinute(10)
                    .build()

            var selectedTime : LocalDateTime = LocalDateTime.now() //Needs to be initialized

            //Configure behaviour of datePicker and timePicker
            datePicker.addOnPositiveButtonClickListener {
                selectedTime = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDateTime().truncatedTo(
                    ChronoUnit.DAYS); //{it} is the date in millis

                timePicker.show(childFragmentManager, "materialDatePicker")
            }

            timePicker.addOnPositiveButtonClickListener {
                binding.fragmentAddUserInsertBirthDate.setText(selectedTime.plusMinutes(timePicker.minute.toLong()).plusHours(timePicker.hour.toLong())
                    .toString())
            }

            //Show datePicker (beginning of the interaction)
            datePicker.show(childFragmentManager, "materialDatePicker")
        }

        //Configure add user button
        binding.fragmentAddUserButtonCreateUser.setOnClickListener{
            addUserViewModel.addUser(binding.fragmentAddUserInsertUserName.text.toString()
                ,binding.fragmentAddUserInsertBirthDate.text.toString()
            ) {
                //Callback
                when(it){
                    null -> Toast.makeText(context, R.string.add_user_user_created_error, Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(context, R.string.add_user_user_created_ok , Toast.LENGTH_SHORT).show()
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