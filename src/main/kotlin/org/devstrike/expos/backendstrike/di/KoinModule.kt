package org.devstrike.expos.backendstrike.di

import org.devstrike.expos.backendstrike.data.repository.UserDataSourceImpl
import org.devstrike.expos.backendstrike.domain.repository.UserDataSource
import org.devstrike.expos.backendstrike.util.Constants.DATABASE_NAME
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val koinModule = module {
    //provide instance of mongodb
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase(DATABASE_NAME)
    }

    //provide instance of userDataSource
    single<UserDataSource> {
        UserDataSourceImpl(get())
    }
}