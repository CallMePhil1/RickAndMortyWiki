package com.github.callmephil1.rickandmortywiki.data

class ServiceError(
    code: Int,
    message: String
) : Throwable("Request failed and received code '$code' with message '$message'")