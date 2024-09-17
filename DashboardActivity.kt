package com.example.final_assignment.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_assignment.R
import com.example.final_assignment.adapter.MyAdapter
import com.example.final_assignment.api.ApiClient
import com.example.final_assignment.api.ApiService
import com.example.final_assignment.models.DashboardResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import android.widget.Button

class DashboardActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var currentIndex = 0 // Track current index

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // list of images
        val imageList = listOf(
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7
        )

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Initialize Retrofit
        val retrofit = ApiClient.getRetrofitInstance()
        val apiService = retrofit.create(ApiService::class.java)

        // Get API data
        apiService.getEntities().enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if (response.isSuccessful) {
                    val dashboardResponse = response.body()
                    val entityList = dashboardResponse?.entities ?: emptyList()
                    if (entityList.isEmpty()) {
                        Log.e("DashboardActivity", "Received empty entity list from API")
                    } else {
                        adapter = MyAdapter(imageList, entityList)
                        recyclerView.adapter = adapter
                    }
                } else {
                    // Handle response error
                    Log.e("DashboardActivity", "API response error: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                // Handle network failure
                Log.e("DashboardActivity", "Network error: ${t.message}", t)
            }
        })

        // Get Next and Previous buttons
        val nextButton: ImageButton = findViewById(R.id.right_arrow)
        val prevButton: ImageButton = findViewById(R.id.left_arrow)

        // Next Button: Scroll to next item
        nextButton.setOnClickListener {
            if (currentIndex < imageList.size - 1) {
                currentIndex++
                recyclerView.smoothScrollToPosition(currentIndex)
            }
        }

        // Previous Button: Scroll to previous item
        prevButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                recyclerView.smoothScrollToPosition(currentIndex)
            }
        }

        // Logout Button
        val logoutButton: Button = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener {
            // Go back to LoginActivity
            val intent = Intent(this@DashboardActivity, LoginActivity::class.java)
            // Clear the back stack so user can't go back to the Dashboard
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // Close current activity
        }
    }
}
