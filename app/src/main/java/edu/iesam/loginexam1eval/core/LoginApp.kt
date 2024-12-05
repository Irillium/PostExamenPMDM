package edu.iesam.loginexam1eval.core

import android.app.Application
import edu.iesam.loginexam1eval.core.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class LoginApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LoginApp)
            modules(AppModule().module)
        }
    }
}