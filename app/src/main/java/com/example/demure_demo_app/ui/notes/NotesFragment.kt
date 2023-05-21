package com.example.demure_demo_app.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demure_demo_app.R
import com.example.demure_demo_app.data.ProfileData
import com.example.demure_demo_app.data.RetrofitResponse
import com.example.demure_demo_app.databinding.FragmentNotesBinding
import com.example.demure_demo_app.ui.OtpFragment
import com.example.demure_demo_app.ui.adapters.RecyclerViewAdapter
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = binding.notesRecyclerView

        recyclerview.layoutManager = GridLayoutManager(context, 2)

        val data = ArrayList<ProfileData>()

        data.add(ProfileData(R.mipmap.ron, "Ron"))
        data.add(ProfileData(R.mipmap.harry, "Harry"))
        data.add(ProfileData(R.mipmap.black, "Sirius"))
        data.add(ProfileData(R.mipmap.snape, "Snape"))
        data.add(ProfileData(R.mipmap.remus, "Remus"))

        val adapter = RecyclerViewAdapter(data)

        recyclerview.adapter = adapter
    }
}