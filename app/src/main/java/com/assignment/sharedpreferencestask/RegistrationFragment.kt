package com.assignment.sharedpreferencestask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.assignment.sharedpreferencestask.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
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
        preferencesConfig = SharedPreferencesConfig(requireContext())
        if (preferencesConfig.readLoginInStatus())
            navigateToProfile()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.registration_toolbar_title)
        binding.registrationSubmitButton.setOnClickListener {
            onSubmitClick()
        }
        binding.registrationNameEditText.doAfterTextChanged { text ->
            binding.registrationNameTil.isErrorEnabled = text!!.isEmpty()
        }
        binding.registrationAddressEditText.doAfterTextChanged { text ->
            binding.registrationAddressTil.isErrorEnabled = text!!.isEmpty()
        }
        binding.registrationAgeEditText.doAfterTextChanged { text ->
            binding.registrationAgeTil.isErrorEnabled = text!!.isEmpty()
        }
    }

    private fun onSubmitClick() {
        val name = binding.registrationNameEditText.text.toString().trim()
        val address = binding.registrationAddressEditText.text.toString().trim()
        val age = binding.registrationAgeEditText.text.toString().trim()
        val nameTil = binding.registrationNameTil
        val addressTil = binding.registrationAddressTil
        val ageTil = binding.registrationAgeTil
        val stringFields = arrayOf(name, address, age)
        for (i in stringFields.indices) {
            if (stringFields[i].isEmpty()) {
                when (i) {
                    0 -> nameTil.error = getString(R.string.registration_empty_name)
                    1 -> addressTil.error = getString(R.string.registration_empty_address)
                    2 -> ageTil.error = getString(R.string.registration_empty_age)
                }
            }
        }
        if (name.isEmpty() || age.isEmpty() || address.isEmpty())
            return
        try {
            val intAge = Integer.parseInt(age)
            preferencesConfig.writeAge(intAge)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                getString(R.string.enter_valid_imput_s, e.message),
                Toast.LENGTH_SHORT
            ).show()
            Log.e("RegistrationFragment", "onSubmitClick: " + e.message)
            return
        }
        preferencesConfig.writeName(name)
        preferencesConfig.writeAddress(address)
        preferencesConfig.writeCreationTime(System.currentTimeMillis())
        preferencesConfig.writeLoginInStatus(true)
        Toast.makeText(
            context,
            getString(R.string.registration_profile_created),
            Toast.LENGTH_SHORT
        ).show()
        navigateToProfile()
    }

    private fun navigateToProfile() {
        findNavController().navigate(R.id.action_RegistrationFragment_to_ProfileFragment)
    }
}