package com.hackyeon.realm_observe

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initRealm()
    }


    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("default.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)
    }
}