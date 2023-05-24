package com.example.demure_demo_app.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demure_demo_app.R
import com.example.demure_demo_app.data.ProfileData
import com.example.demure_demo_app.data.RetrofitResponse
import com.example.demure_demo_app.data.profile_data.Feed
import com.example.demure_demo_app.data.profile_data.Likes
import com.example.demure_demo_app.databinding.FragmentNotesBinding
import com.example.demure_demo_app.ui.OtpFragment
import com.example.demure_demo_app.ui.adapters.RecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var notesImageLayoutUtil: NotesImageLayoutUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(OtpFragment().authKey)?.let {
            performAuthorization(it)
        }
    }
    private fun performAuthorization(authToken: String) {
        RetrofitResponse.getJsonPlaceHolderApi().testProfileList(authToken).enqueue(
            object : Callback<Feed?> {
                override fun onResponse(
                    call: Call<Feed?>,
                    response: Response<Feed?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.invites?.profiles?.let {
                            notesImageLayoutUtil =
                                NotesImageLayoutUtil(binding.imageLayoutNotes, it[0])
                            notesImageLayoutUtil.setName()
                            notesImageLayoutUtil.setImage()

                            setupRecyclerView(response.body()?.likes)
                        }

                    } else {
                        Toast.makeText(context, "Error code " + response.code(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<Feed?>, t: Throwable) {
                    Toast.makeText(context, "Failure", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
    }

    private fun setupRecyclerView(likes: Likes?) {
        val profiles = likes?.profiles
        profiles ?: return

        val recyclerview = binding.notesRecyclerView
        recyclerview.layoutManager = GridLayoutManager(context, 2)

        val data = profiles.map {
            ProfileData(it.avatar, it.first_name)
        }

        recyclerview.adapter = RecyclerViewAdapter(data, !likes.can_see_profile)
    }
}