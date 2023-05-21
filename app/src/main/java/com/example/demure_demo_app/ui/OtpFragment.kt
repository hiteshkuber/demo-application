package com.example.demure_demo_app.ui

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.demure_demo_app.R
import com.example.demure_demo_app.data.RetrofitResponse
import com.example.demure_demo_app.databinding.FragmentOtpBinding
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpFragment : Fragment() {

    val authKey = "auth"

    private lateinit var binding: FragmentOtpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_otp, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countryCode = arguments?.getString(PhoneNumberFragment().countryCodeKey)
        val phoneNumberKey = arguments?.getString(PhoneNumberFragment().phoneNumberKey)

        binding.otpTitle.text = "$countryCode $phoneNumberKey"


        binding.otpContinueButton.setOnClickListener {
            binding.otpCode.text.toString().let {
                if (it.length == 4) {
                    performNetworkCall(countryCode + phoneNumberKey, it)
                } else {
                    Toast.makeText(context, "Please enter correct otp", Toast.LENGTH_SHORT).show()
                }
            }
        }

        otpTimer()
    }

    private fun performNetworkCall(mobileNumber: String, otp: String) {

        val param = JsonObject().apply {
            addProperty(RetrofitResponse.numberKey, mobileNumber)
            addProperty(RetrofitResponse.otpKey, otp)
        }

        RetrofitResponse.getJsonPlaceHolderApi().verifyOtp(param).enqueue(
            object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
//                    if (response.isSuccessful) {
//                          // todo: add snippet 2 here
//                    } else {
//                        Toast.makeText(context, "Error code " + response.code(), Toast.LENGTH_SHORT)
//                            .show()
//                    }

                    // code snippet 2
                    val destinationFragment = UserFragment()
                    val bundle = Bundle()
                    bundle.putString(authKey, "todo: hitesh send auth val")
                    destinationFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_container, destinationFragment).commit()
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(context, "Failure", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
    }

    private fun otpTimer() {

        object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val remainingTime = millisUntilFinished / 1000
                val text = if (remainingTime > 10) {
                    "00:$remainingTime"
                } else {
                    "00:0$remainingTime"
                }

                binding.otpCountdown.text = text
            }

            override fun onFinish() {
                binding.otpCountdown.text = "00:00"
            }
        }.start()
    }
}