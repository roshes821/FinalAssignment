package com.example.final_assignment.api
import com.example.final_assignment.models.DashboardResponse
import com.example.final_assignment.models.CourseDetails
import com.example.final_assignment.models.LoginRequest
import com.example.final_assignment.models.LoginResponse
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @POST("/sydney/auth")
    fun userLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("/dashboard/courses")
    fun getEntities(): Call<DashboardResponse>
}