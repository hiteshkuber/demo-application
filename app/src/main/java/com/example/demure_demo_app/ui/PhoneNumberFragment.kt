package com.example.demure_demo_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.demure_demo_app.R
import com.example.demure_demo_app.data.RetrofitResponse
import com.example.demure_demo_app.databinding.FragmentPhoneNumberBinding
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneNumberFragment : Fragment() {

    val phoneNumberKey = "phone_number"
    val countryCodeKey = "country_code"

    private lateinit var binding: FragmentPhoneNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_phone_number, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneNumberContinueButton.setOnClickListener {
            binding.phoneNumberMobileNumber.text.toString().let {
                if (it.length == 10) {
                    performNetworkCall(binding.phoneNumberCountryCode.text.toString(), it)
                } else {
                    Toast.makeText(context, "Please enter correct phone number", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun performNetworkCall(countryCode: String, phoneNumber: String) {

        val param = JsonObject().apply {
            addProperty(
                RetrofitResponse.numberKey,
                countryCode + phoneNumber
            )
        }

        RetrofitResponse.getJsonPlaceHolderApi().phoneNumberLogin(param).enqueue(
            object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    // TODO : debug changes until further updates
//                    if (response.isSuccessful) {
                    // todo: add snippet 1 here
//                    } else {
//                        Toast.makeText(context, "Error code " + response.code(), Toast.LENGTH_SHORT)
//                            .show()
//                    }

                    // snippet 1
                    val destinationFragment = OtpFragment()
                    val bundle = Bundle()
                    bundle.putString(phoneNumberKey, phoneNumber)
                    bundle.putString(countryCodeKey, countryCode)
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
}