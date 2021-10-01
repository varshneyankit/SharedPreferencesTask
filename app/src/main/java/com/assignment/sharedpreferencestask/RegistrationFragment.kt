package com.assignment.sharedpreferencestask

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.assignment.sharedpreferencestask.databinding.FragmentRegistrationBinding
import com.google.android.material.textfield.TextInputEditText

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var nameEditText: TextInputEditText
    private lateinit var addressEditText: TextInputEditText
    private lateinit var ageEditText: TextInputEditText
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
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.title="Regi"
        Log.e("TAG", "onViewCreated: "+activity.supportActionBar)
        //activity?.actionBar?.title= "Registration"
//        supportActionBar?.title = "R.string.registration_toolbar_title.toString()"
        preferencesConfig = SharedPreferencesConfig(requireContext())
        if (preferencesConfig.readLoginInStatus())
            navigateToProfile()
        nameEditText = binding.registrationNameEditText
        addressEditText = binding.registrationAddressEditText
        ageEditText = binding.registrationAgeEditText
        binding.registrationSubmitButton.setOnClickListener {
            onSubmitClick()
        }
        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.registrationNameTil.isErrorEnabled = s?.isNotEmpty() != true

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        addressEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.registrationAddressTil.isErrorEnabled = s?.isNotEmpty() != true

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        ageEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.registrationAgeTil.isErrorEnabled = s?.isNotEmpty() != true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
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
            if (TextUtils.isEmpty(stringFields[i]) || stringFields[i].isEmpty()) {
                when (i) {
                    0 -> nameTil.error = "Enter your full name"
                    1 -> addressTil.error = "Enter your Address"
                    2 -> ageTil.error = "Enter your Age"
                }
            }
        }
        if (name.isEmpty() || age.isEmpty() || address.isEmpty())
            return
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