package com.nikhil.mybestfriend.feature.commons.application

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.nikhil.mybestfriend.feature.auth.data.repo.LoginRepo
import com.nikhil.mybestfriend.feature.auth.data.repo.LoginRepoImpl
import com.nikhil.mybestfriend.feature.auth.data.repo.RegisterRepo
import com.nikhil.mybestfriend.feature.auth.data.repo.RegisterRepoImpl
import com.nikhil.mybestfriend.feature.auth.login.viewmodel.LoginViewModelFactory
import com.nikhil.mybestfriend.feature.auth.register.viewmodel.RegisterViewModelFactory
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatBreedDataSource
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatBreedDataSourceImpl
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatDetailDataSource
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatDetailDataSourceImpl
import com.nikhil.mybestfriend.feature.cat.data.repo.CatDetailRepo
import com.nikhil.mybestfriend.feature.cat.data.repo.CatDetailRepoImpl
import com.nikhil.mybestfriend.feature.cat.data.repo.CatListRepo
import com.nikhil.mybestfriend.feature.cat.data.repo.CatListRepoImpl
import com.nikhil.mybestfriend.feature.cat.details.viewmodel.CatDetailViewModelFactory
import com.nikhil.mybestfriend.feature.cat.listing.viewmodel.CatListViewModelFactory
import com.nikhil.mybestfriend.feature.commons.data.api.interceptor.ConnectivityInterceptor
import com.nikhil.mybestfriend.feature.commons.data.api.interceptor.ConnectivityInterceptorImpl
import com.nikhil.mybestfriend.feature.commons.data.api.service.CatAPIService
import com.nikhil.mybestfriend.feature.commons.data.db.FriendDatabase
import com.nikhil.mybestfriend.feature.preferences.data.PreferenceHelper
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
        bind() from singleton { PreferenceHelper(instance()) }
        /*CatListing and detils*/
        bind() from singleton { instance<FriendDatabase>().catDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { CatAPIService(instance()) }
        bind<CatBreedDataSource>() with singleton { CatBreedDataSourceImpl(instance()) }
        bind<CatDetailDataSource>() with singleton { CatDetailDataSourceImpl(instance()) }
        bind<CatListRepo>() with singleton { CatListRepoImpl(instance(),instance()) }
        bind<CatDetailRepo>() with singleton { CatDetailRepoImpl(instance(), instance()) }
        bind() from provider { CatListViewModelFactory(instance(),instance()) }
        bind() from provider { CatDetailViewModelFactory(instance(),instance()) }
        /*Login and register*/
        bind() from singleton { instance<FriendDatabase>().userDao() }
        bind<LoginRepo>() with singleton { LoginRepoImpl(instance()) }
        bind<RegisterRepo>() with singleton { RegisterRepoImpl(instance()) }
        bind() from provider { LoginViewModelFactory(instance()) }
        bind() from provider { RegisterViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this);
        //PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}
