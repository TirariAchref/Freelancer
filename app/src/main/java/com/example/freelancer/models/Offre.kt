package com.example.freelancer.models

import com.google.gson.annotations.SerializedName

data class Offre (
    @SerializedName("subject")
    var subject: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("UserId")
    var UserId: String,
    @SerializedName("imageClient")
    var imageClient: String,
    @SerializedName("_id")
    var id: String,

    @SerializedName("Price")
var Price: String,
@SerializedName("Status")
var Status: String,
@SerializedName("Time")
var Time: String,
    @SerializedName("UserIdAccept")
    var UserIdAccept: String,
)