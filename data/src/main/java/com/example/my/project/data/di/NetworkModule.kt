package com.example.my.project.data.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.my.project.data.repository.AlbumRepositoryImp
import com.example.my.project.data.repository.PhotoRepositoryImp
import com.example.my.project.data.source.local.AppDatabase
import com.example.my.project.data.source.remote.RetrofitService
import com.example.my.project.domain.repository.AlbumRepository
import com.example.my.project.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    @Singleton
    @Provides
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(gsonConverterFactory).addCallAdapterFactory(rxJava2CallAdapterFactory).client(okHttpClient).build()
    }


    @Singleton
    @Provides
    fun providesOkhttpClient(@ApplicationContext context: Context): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().cache(mCache).connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor).addInterceptor { chain ->
                var request = chain.request()
                request = if (true) request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
                chain.proceed(request)

            }
        return client.build()
    }



    @Provides
    @Singleton
    fun provideIsNetworkAvailable(@ApplicationContext context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }



    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }


    @Singleton
    @Provides
    fun providesAlbumRepository(
        retrofitService: RetrofitService
    ): AlbumRepository {
        return AlbumRepositoryImp(retrofitService)
    }


    @Singleton
    @Provides
    fun providesPhotoRepository(
        appDatabase: AppDatabase, retrofitService: RetrofitService
    ): PhotoRepository {
        return PhotoRepositoryImp(appDatabase, retrofitService)
    }

}