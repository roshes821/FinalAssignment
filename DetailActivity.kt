package com.example.final_assignment.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.final_assignment.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Getting values from the adapter
        val courseCode = intent.getStringExtra("courseCode")
        val courseName = intent.getStringExtra("courseName")
        val instructor = intent.getStringExtra("instructor")
        val credits = intent.getIntExtra("credits", 0)
        val description = intent.getStringExtra("description")

        // Find views and set data
        val courseCodeTextView: TextView = findViewById(R.id.course_code)
        val courseNameTextView: TextView = findViewById(R.id.course_name)
        val instructorTextView: TextView = findViewById(R.id.instructor_name)
        val creditsTextView: TextView = findViewById(R.id.credits)
        val descriptionTextView: TextView = findViewById(R.id.description)
        val backButton: Button = findViewById(R.id.back_button)

        // Setting the values to their corresponding views
        courseCodeTextView.text = courseCode ?: "N/A"
        courseNameTextView.text = courseName ?: "Unknown Course"
        instructorTextView.text = "Instructor: ${instructor ?: "N/A"}"
        creditsTextView.text = "Credits: $credits"
        descriptionTextView.text = description ?: "No description available"

        // Handle button click
        backButton.setOnClickListener {
            val intent = Intent(this@DetailActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
