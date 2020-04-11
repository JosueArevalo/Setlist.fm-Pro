package com.josuearevalodev.remote.httpclient

interface HttpClient {

    var isDebugMode: Boolean

    fun <T> create(service: Class<T>, baseUrl: String): T

    fun setHeader(header: HttpHeader)
}
