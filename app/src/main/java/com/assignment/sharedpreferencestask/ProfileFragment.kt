package com.assignment.sharedpreferencestask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.assignment.sharedpreferencestask.databinding.FragmentProfileBinding
import java.util.concurrent.TimeUnit

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var nameTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var profileCreationTime: TextView
    private lateinit var preferencesConfig: SharedPreferencesConfig

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.profileToolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Profile"
        preferencesConfig = SharedPreferencesConfig(requireContext())
        nameTextView = binding.profileNameText
        addressTextView = binding.profileAddressText
        ageTextView = binding.profileAgeText
        profileCreationTime = binding.profileCreationText
        if (preferencesConfig.readName() != null && preferencesConfig.readAddress() != null) {
            nameTextView.text = "Name: ${preferencesConfig.readName()}"
            addressTextView.text = "Address: ${preferencesConfig.readAddress()}"
            ageTextView.text = "Age: ${preferencesConfig.readAge()} years"
            val calculatedProfileAge =
                System.currentTimeMillis() - preferencesConfig.readCreationTime()
            val timeInSeconds = TimeUnit.SECONDS.convert(calculatedProfileAge,TimeUnit.MILLISECONDS)
            val timeInMinutes = TimeUnit.MINUTES.convert(calculatedProfileAge,TimeUnit.MILLISECONDS)
            val timeInHours =  TimeUnit.HOURS.convert(calculatedProfileAge,TimeUnit.MILLISECONDS)
            val timeInDays = TimeUnit.DAYS.convert(calculatedProfileAge,TimeUnit.MILLISECONDS)
            val timeInMonths = timeInDays/30
            val timeInYears = timeInMonths/12
            var timeString = ""
            when {
                timeInSeconds<60 -> timeString = "Created $timeInSeconds seconds ago"
                timeInMinutes>=1 && timeInMinutes<60 -> timeString = "Created $timeInMinutes min ${timeInSeconds-timeInMinutes*60} sec ago"
                timeInHours>=1 && timeInHours<60 -> timeString = "Created $timeInHours hr ${timeInMinutes-timeInHours*60} min ago"
                timeInDays>=1 && timeInDays < 30 -> timeString = "Created $timeInDays days ${timeInHours-timeInDays*24} ago"
                timeInMonths>=1 && timeInMonths <12 -> timeString = "Created $timeInMonths months ago"
                timeInYears>=1 -> timeString = "Created $timeInYears years ago"
            }
            profileCreationTime.text = timeString
        }
    }
}