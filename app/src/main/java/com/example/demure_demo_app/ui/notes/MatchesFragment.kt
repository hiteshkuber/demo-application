package com.example.demure_demo_app.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.demure_demo_app.R
import com.example.demure_demo_app.databinding.FragmentMatchesBinding

class MatchesFragment : Fragment() {

    private lateinit var binding: FragmentMatchesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_matches, container, false)
        return binding.root
    }
}