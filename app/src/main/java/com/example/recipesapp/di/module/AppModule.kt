package com.example.recipesapp.di.module

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.recipesapp.data.DataRepository
import com.example.recipesapp.data.local.db.AppDatabase
import com.example.recipesapp.data.remote.ApiRepository
import com.example.recipesapp.data.remote.network.ApiClient
import com.example.recipesapp.data.remote.network.ApiService
import com.example.recipesapp.utils.AppConstants
import com.example.recipesapp.utils.NetworkUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RequiresApi(Build.VERSION_CODES.M)
val appModule = module {
    single { get<Retrofit>().create(ApiService::class.java) }
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get() , get ()) }
    single { provideInterceptor(get()) }
    single { provideCache(get()) }

    single { provideSharedPreferences(get())}
}

private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(ApiClient.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


private fun provideCache(context: Context): Cache {
    val cacheSize : Long = 10 * 1024 * 1024
    return Cache(context.cacheDir, cacheSize)
}

private fun provideOkHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(interceptor)
        .build()
}

@RequiresApi(Build.VERSION_CODES.M)
private fun provideInterceptor(context: Context): Interceptor {
    return Interceptor { chain ->
        var request: Request = chain.request()
        request = if (!NetworkUtils.isNetworkAvailable(context)) {
            val maxStale = 60 * 60 * 24 * 28 // 4-weeks-stale
            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale$maxStale")
                .build()
        } else {
            val maxAge = 5 // fresh data
            request.newBuilder()
                .header("Cache-Control", "public, max-age = $maxAge")
                .build()
        }
        chain.proceed(request)
    }
}

private fun provideSharedPreferences(context: Context) = context.getSharedPreferences(AppConstants.PREF_NAME , Context.MODE_PRIVATE)
