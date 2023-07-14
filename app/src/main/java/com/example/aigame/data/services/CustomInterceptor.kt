package com.example.aigame.data.services

import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Get the original request from the chain
        val originalRequest = chain.request()

        // Perform any modifications to the request if needed
        val modifiedRequest = originalRequest.newBuilder()
            // Add or modify headers
            .build()

        // Proceed with the modified request
        val response = chain.proceed(modifiedRequest)

        // Perform any modifications to the response if needed
        val modifiedResponse = response.newBuilder()
            // Add or modify headers
            // .addHeader("Custom-Header", "Value")
            .build()

        // Return the modified response
        return modifiedResponse
    }
}