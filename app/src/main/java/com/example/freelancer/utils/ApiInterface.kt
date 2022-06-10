package com.example.freelancer.utils

import com.example.freelancer.models.Offre
import com.example.freelancer.models.User
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

import java.util.concurrent.TimeUnit


interface ApiInterface {

    @POST("loginClient")
    fun seConnecter(@Body info: RequestBody): Call<User>

    @POST("createuser")
    fun SignIn(@Body info: RequestBody): Call<User>

    @PUT("updateuser/{id}")
    fun UpdateUser(@Body info: RequestBody,@Path("id") id : String): Call<User>

    @PUT("updateuserpass/{id}")
    fun updateusernotpass(@Body info: RequestBody,@Path("id") id : String): Call<User>


    @GET("allusers")
    fun allusers(): Call<MutableList<User>>

    @GET("getuserEmail/{email}")
    fun getuserbyemail( @Path("email") email : String): Call<MutableList<User>>

    @GET("getuser/{userId}")
    fun getByid(@Path("userId") userId : String): Call<User>



    @Multipart
    @POST("/updateImageClient/{id}")
    fun upload(@Part image: MultipartBody.Part,@Path("id") id : String): Call<User>


    @GET("alloffre")
    fun alloffre(): Call<MutableList<Offre>>

    @POST("createoffre")
    fun createoffre(@Body info: RequestBody): Call<Offre>

    @PUT("updateoffre/{id}")
    fun updateoffre(@Body info: RequestBody,@Path("id") id : String): Call<Offre>

    companion object {

        var BASE_URL = "http://192.168.1.137:3000/"

        fun create() : ApiInterface {
            val httpClient = OkHttpClient.Builder()
                .callTimeout(60, TimeUnit.MINUTES)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}