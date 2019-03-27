package com.sachinsandbhor.newsapp.entities

data class Data<RequestData> (var responseType: Status, var data: RequestData? = null, var error: Error? = null)

enum class Status{SUCCESS, LOADING, ERROR}