package com.example.demure_demo_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.example.demure_demo_app.R
import com.example.demure_demo_app.data.RetrofitResponse
import com.example.demure_demo_app.databinding.FragmentUserBinding
import com.example.demure_demo_app.ui.notes.DiscoverFragment
import com.example.demure_demo_app.ui.notes.MatchesFragment
import com.example.demure_demo_app.ui.notes.NotesFragment
import com.example.demure_demo_app.ui.notes.ProfileFragment
import com.example.demure_demo_app.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val drawableResList = listOf(
        R.drawable.baseline_dashboard_24,
        R.drawable.baseline_email_24,
        R.drawable.baseline_message_24,
        R.drawable.baseline_person_24
    )

    private val titleResList = listOf(
        R.string.discover_title,
        R.string.notes_title,
        R.string.matches_title,
        R.string.profile_title
    )

    private val notifList = listOf(
        null,
        "9",
        "50+",
        null
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(OtpFragment().authKey)?.let {
            performAuthorization(it)
        }

    }

    private fun setupCustomTabs() {
        val adapter = ViewPagerAdapter(parentFragmentManager)

        adapter.addFragment(DiscoverFragment(), resources.getString(R.string.discover_title))
        adapter.addFragment(NotesFragment(), resources.getString(R.string.notes_title))
        adapter.addFragment(MatchesFragment(), resources.getString(R.string.matches_title))
        adapter.addFragment(ProfileFragment(), resources.getString(R.string.profile_title))
        binding.notesViewPager.adapter = adapter
        binding.notesTabs.setupWithViewPager(binding.notesViewPager)

        for (i in 0 until binding.notesTabs.tabCount) {
            val tab: TabLayout.Tab? = binding.notesTabs.getTabAt(i)
            if (tab != null) {
                val customView: View =
                    LayoutInflater.from(context).inflate(R.layout.tab_layout_custom, null)

                customView.findViewById<ImageView>(R.id.tab_icon)
                    .setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            drawableResList[i],
                            null
                        )
                    )
                customView.findViewById<TextView>(R.id.tab_badge).setText(titleResList[i])
                customView.findViewById<TextView>(R.id.tab_superscript).let { view ->
                    notifList[i]?.let {
                        view.text = it
                        view.visibility = View.VISIBLE
                    }
                }

                if (i == 1) {
                    tab.select()
                }

                // Set the custom view for the tab
                tab.customView = customView
            }
        }
    }

    private fun performAuthorization(authToken: String) {
        RetrofitResponse.getJsonPlaceHolderApi().testProfileList(authToken).enqueue(
            object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
//                    if (response.isSuccessful) {
//                        // todo: add snippet 3 here
//                    } else {
//                        Toast.makeText(context, "Error code " + response.code(), Toast.LENGTH_SHORT)
//                            .show()
//                    }

                    // snippet 3
                    binding.notesProgressBar.visibility = View.GONE
                    setupCustomTabs()
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(context, "Failure", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
    }
}