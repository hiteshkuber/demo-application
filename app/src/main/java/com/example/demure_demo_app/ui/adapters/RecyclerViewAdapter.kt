package com.example.demure_demo_app.ui.adapters

import android.graphics.RenderEffect
import android.graphics.Shader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demure_demo_app.R
import com.example.demure_demo_app.data.ProfileData
import com.squareup.picasso.Picasso

const val DUMMY_NAME = "<dummy name>"
const val RADIUS = 50f

class RecyclerViewAdapter(private val dataList: List<ProfileData>, private val isHidden: Boolean) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.item_image_view)
        val textView: TextView = itemView.findViewById(R.id.item_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val itemViewView = inflater.inflate(R.layout.profile_item, parent, false)

        return ViewHolder(itemViewView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val profileData: ProfileData = dataList[position]
        // Set item views based on your views and data model
        Picasso
            .get()
            .load(profileData.image)
            .into(holder.imageView)

        if (isHidden) {
            holder.imageView.setRenderEffect(
                RenderEffect.createBlurEffect(
                    RADIUS,
                    RADIUS,
                    Shader.TileMode.DECAL
                )
            )
        }

        holder.textView.text = profileData.name ?: DUMMY_NAME
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return dataList.size
    }

}