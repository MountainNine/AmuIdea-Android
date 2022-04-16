package com.mtnine.amuidea.dagger

import com.mtnine.amuidea.ui.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun accountActivity(): AccountActivity

    @ContributesAndroidInjector
    abstract fun listActivity(): ListActivity

    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun startActivity(): StartActivity
}