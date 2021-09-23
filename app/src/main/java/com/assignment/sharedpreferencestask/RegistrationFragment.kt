package com.assignment.sharedpreferencestask

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.assignment.sharedpreferencestask.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var nameEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var preferencesConfig: SharedPreferencesConfig

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.registrationToolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Registration"
        preferencesConfig = SharedPreferencesConfig(requireContext())
        if (preferencesConfig.readLoginInStatus())
            navigateToProfile()
        nameEditText = binding.registrationNameEditText
        addressEditText = binding.registrationAddressEditText
        ageEditText = binding.registrationAgeEditText
        binding.registrationSubmitButton.setOnClickListener {
            onSubmitClick()
        }
    }

    private fun onSubmitClick() {
        val name = nameEditText.text.toString().trim()
        val address = addressEditText.text.toString().trim()
        val age = ageEditText.text.toString().trim()
        val nameTil = binding.registrationNameTil
        val addressTil = binding.registrationAddressTil
        val ageTil = binding.registrationAgeTil
        val stringFields = arrayOf(name, address, age)
        for (i in stringFields.indices) {
            if (TextUtils.isEmpty(stringFields[i])) {
                when (i) {
                    0 -> nameTil.error = "Enter your full name"
                    1 -> addressTil.error = "Enter your Address"
                    2 -> ageTil.error = "Enter your Age"
                }
                return
            } else {
                when (i) {
                    0 -> nameTil.isErrorEnabled = false
                    1 -> addressTil.isErrorEnabled = false
                    2 -> ageTil.isErrorEnabled = false
                }
            }
        }
        try {
            val intAge = Integer.parseInt(age)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Enter valid input. \nPossible Error: " + e.message,
                Toast.LENGTH_SHORT
            ).show()
            Log.e("RegistrationFragment", "onSubmitClick: " + e.message)
            return
        }
        preferencesConfig.writeAge(Integer.parseInt(age))
        preferencesConfig.writeName(name)
        preferencesConfig.writeAddress(address)
        preferencesConfig.writeCreationTime(System.currentTimeMillis())
        preferencesConfig.writeLoginInStatus(true)
        Toast.makeText(context, "Profile successfully registered !", Toast.LENGTH_SHORT).show()
        navigateToProfile()

    }

    private fun navigateToProfile() {
        findNavController().navigate(R.id.action_RegistrationFragment_to_ProfileFragment)
    }
}