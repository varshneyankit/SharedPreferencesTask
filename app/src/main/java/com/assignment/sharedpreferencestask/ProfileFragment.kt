package com.assignment.sharedpreferencestask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.assignment.sharedpreferencestask.databinding.FragmentProfileBinding
import java.util.concurrent.TimeUnit

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var preferencesConfig: SharedPreferencesConfig

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.profile_toolbar_title)
        preferencesConfig = SharedPreferencesConfig(requireContext())
        if (preferencesConfig.readName().isNotEmpty() && preferencesConfig.readAddress().isNotEmpty()
        ) {
            binding.profileNameText.text = preferencesConfig.readName()
            binding.profileAddressText.text = preferencesConfig.readAddress()
            binding.profileAgeText.text = preferencesConfig.readAge().toString()
            val calculatedProfileAge =
                System.currentTimeMillis() - preferencesConfig.readCreationTime()
            val timeInSeconds =
                TimeUnit.SECONDS.convert(calculatedProfileAge, TimeUnit.MILLISECONDS)
            val timeInMinutes =
                TimeUnit.MINUTES.convert(calculatedProfileAge, TimeUnit.MILLISECONDS)
            val timeInHours = TimeUnit.HOURS.convert(calculatedProfileAge, TimeUnit.MILLISECONDS)
            val timeInDays = TimeUnit.DAYS.convert(calculatedProfileAge, TimeUnit.MILLISECONDS)
            val timeInMonths = timeInDays / 30
            val timeInYears = timeInMonths / 12
            val timeString = when {
                timeInSeconds < 60 -> "$timeInSeconds seconds ago"
                timeInMinutes in 1..59 -> "$timeInMinutes min ${timeInSeconds - timeInMinutes * 60} sec ago"
                timeInHours in 1..24 -> "$timeInHours hr ${timeInMinutes - timeInHours * 60} min ago"
                timeInDays in 1..30 -> "$timeInDays day ${timeInHours - timeInDays * 24} hr ago"
                timeInMonths in 1..12 -> "$timeInMonths month ago"
                timeInYears >= 1 -> "$timeInYears yr ago"
                else -> ""
            }
            binding.profileCreationText.text = timeString
        }
    }
}