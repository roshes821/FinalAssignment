package com.example.final_assignment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_assignment.R
import com.example.final_assignment.activities.DetailActivity
import com.example.final_assignment.models.CourseDetails

class MyAdapter(private val imageList: List<Int>, private val entityList: List<CourseDetails>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.entityImageView)
        val entityTitle: TextView = itemView.findViewById(R.id.entity_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_entity_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
        val entity = entityList.getOrNull(position)
        holder.entityTitle.text = entity?.courseName ?: "Unknown Title"

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)

            // Pass course details
            if (entity != null) {
                intent.putExtra("courseCode", entity.courseCode)
                intent.putExtra("courseName", entity.courseName)
                intent.putExtra("instructor", entity.instructor)
                intent.putExtra("credits", entity.credits)
                intent.putExtra("description", entity.description)
            }

            // Pass the image resource
            intent.putExtra("imageResId", imageList[position])

            // Start DetailActivity
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
