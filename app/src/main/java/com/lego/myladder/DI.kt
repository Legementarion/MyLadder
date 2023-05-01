package com.lego.myladder

import androidx.room.Room
import com.lego.myladder.data.dao.LadderDataBase
import com.lego.myladder.data.repository.RepositoryImpl
import com.lego.myladder.data.source.LocalDataSource
import com.lego.myladder.data.source.LocalDataSourceImpl
import com.lego.myladder.domain.Repository
import com.lego.myladder.domain.usecase.GetSequenceUseCase
import com.lego.myladder.domain.usecase.IncreaseSequenceUseCase
import com.lego.myladder.presentation.ui.MainViewModel
import org.koin.dsl.module

val appModule = module {
    //UI
    single { MainViewModel(get(), get()) }

    //Domain
    single { GetSequenceUseCase(get()) }
    single { IncreaseSequenceUseCase(get()) }

    //Data
    single<Repository> { RepositoryImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get()) }

    single {
        val db = Room.databaseBuilder(
            get(),
            LadderDataBase::class.java, "database-name"
        ).build()

        db.ladderDao()
    }

}