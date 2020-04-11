package com.josuearevalodev.remote.httpclient

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClientImpl : HttpClient {

    private val headers: MutableMap<String, HttpHeader> by lazy { mutableMapOf<String, HttpHeader>() }

    override var isDebugMode: Boolean = false

    override fun <T> create(service: Class<T>, baseUrl: String): T {
        return client(baseUrl).create(service)
    }

    override fun setHeader(header: HttpHeader) {
        headers[header.httpField] = header
    }

    private fun client(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient.build())
            .build()
    }

    private val loggingInterceptor: HttpLoggingInterceptor
        get() {
            val instance = HttpLoggingInterceptor()
            instance.level = HttpLoggingInterceptor.Level.BODY

            return instance
        }

    private val okHttpClient: OkHttpClient.Builder
        get() {
            val instance = OkHttpClient.Builder()
            instance.readTimeout(60, TimeUnit.SECONDS)
            instance.connectTimeout(60, TimeUnit.SECONDS)
            instance.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()
                    val newRequest = original.newBuilder()

                    for ((_, httpHeader) in headers) {
                        newRequest.addHeader(httpHeader.httpField, httpHeader.value)
                    }


                    val build = newRequest.method(original.method, original.body).build()

                    return chain.proceed(build)
                }
            })

            takeIf { isDebugMode }?.apply { instance.addInterceptor(loggingInterceptor) }

            return instance
        }
}