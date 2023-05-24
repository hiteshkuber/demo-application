package com.example.demure_demo_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.demure_demo_app.R
import com.example.demure_demo_app.databinding.FragmentUserBinding
import com.example.demure_demo_app.ui.adapters.ViewPagerAdapter
import com.example.demure_demo_app.ui.notes.DiscoverFragment
import com.example.demure_demo_app.ui.notes.MatchesFragment
import com.example.demure_demo_app.ui.notes.NotesFragment
import com.example.demure_demo_app.ui.notes.ProfileFragment
import com.google.android.material.tabs.TabLayout

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

        setupCustomTabs()

    }

    private fun setupCustomTabs() {
        val adapter = ViewPagerAdapter(parentFragmentManager)

        adapter.addFragment(DiscoverFragment(), resources.getString(R.string.discover_title))

        val notesFragment = NotesFragment()
        notesFragment.arguments = arguments
        adapter.addFragment(notesFragment, resources.getString(R.string.notes_title))
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
}