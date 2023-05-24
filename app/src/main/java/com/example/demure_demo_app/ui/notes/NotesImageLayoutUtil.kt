package com.example.demure_demo_app.ui.notes

import com.example.demure_demo_app.data.profile_data.Profile
import com.example.demure_demo_app.databinding.ImageLayoutNotesBinding
import com.squareup.picasso.Picasso
import java.time.LocalDate

const val DUMMY_NAME = "<dummy name>"
const val DUMMY_AGE = "<dummy age>"
const val COMMA = ","
const val SPACE = " "

class NotesImageLayoutUtil(val binding: ImageLayoutNotesBinding, private val profile: Profile) {

    fun setName() {
        val name = profile.general_information?.first_name
        val age: Int? = getAge()
        val layoutName = (name ?: DUMMY_NAME) + COMMA + SPACE + (age ?: DUMMY_AGE)
        binding.imageLayoutName.text = layoutName
    }

    fun setImage() {
        profile.photos?.find { it.selected }?.photo?.let {
            Picasso
                .get()
                .load(it).into(binding.imageLayoutImageView)
        }
    }

    private fun getAge(): Int? {
        var year = 0
        var month = 0
        var day = 0
        profile.general_information?.date_of_birth?.let {
            if (it.length == 10) {
                year = it.subSequence(0, 4).toString().toInt()
                month = it.subSequence(5, 7).toString().toInt()
                day = it.subSequence(8, 10).toString().toInt()
            } else {
                return null
            }
        }

        val today = LocalDate.now()
        val yearDiff = today.year - year
        val monthNow = today.monthValue
        val dayNow = today.dayOfMonth

        return if (month > monthNow) {
            yearDiff - 1
        } else if (month < monthNow) {
            yearDiff
        } else {
            if (day > dayNow) {
                yearDiff - 1
            } else {
                yearDiff
            }
        }

    }
}
