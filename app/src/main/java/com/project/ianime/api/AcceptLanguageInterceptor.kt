package com.project.ianime.api

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class AcceptLanguageInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val acceptLanguage = Locale.getDefault().language

        val updatedRequest = request.newBuilder()
            .addHeader(HEADER_ACCEPT_LANGUAGE, acceptLanguage)
            .build()
        return chain.proceed(updatedRequest)
    }

    companion object {
        const val HEADER_ACCEPT_LANGUAGE = "Accept-language"
    }

}