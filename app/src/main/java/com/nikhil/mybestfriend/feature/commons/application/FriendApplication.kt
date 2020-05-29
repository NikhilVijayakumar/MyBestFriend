package com.nikhil.mybestfriend.feature.commons.application

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatBreedDataSource
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatBreedDataSourceImpl
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatDetailDataSource
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatDetailDataSourceImpl
import com.nikhil.mybestfriend.feature.cat.data.repo.CatListRepo
import com.nikhil.mybestfriend.feature.cat.data.repo.CatListRepoImpl
import com.nikhil.mybestfriend.feature.cat.listing.viewmodel.CatListViewModelFactory
import com.nikhil.mybestfriend.feature.commons.data.api.interceptor.ConnectivityInterceptor
import com.nikhil.mybestfriend.feature.commons.data.api.interceptor.ConnectivityInterceptorImpl
import com.nikhil.mybestfriend.feature.commons.data.api.service.CatAPIService
import com.nikhil.mybestfriend.feature.commons.data.db.FriendDatabase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class FriendApplication : Application(), KodeinAware {


    override val kodein = Kodein.lazy {
        import(androidXModule(this@FriendApplication))
        bind() from singleton { FriendDatabase(instance()) }
        bind() from singleton { instance<FriendDatabase>().catDao() }
        bind() from singleton { instance<FriendDatabase>().userDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { CatAPIService(instance()) }
        bind<CatBreedDataSource>() with singleton { CatBreedDataSourceImpl(instance()) }
        bind<CatDetailDataSource>() with singleton { CatDetailDataSourceImpl(instance()) }
        bind<CatListRepo>() with singleton { CatListRepoImpl(instance(),instance()) }
         bind() from provider { CatListViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        //PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}
