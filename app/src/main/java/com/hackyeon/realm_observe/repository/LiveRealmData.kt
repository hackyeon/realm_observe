package com.hackyeon.realm_observe.repository

import androidx.lifecycle.LiveData
import com.hackyeon.realm_observe.repository.db.MyData
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.kotlin.where

class LiveRealmData<T: RealmObject>(val realmResults: RealmResults<T>): LiveData<RealmResults<T>>() {
    private val listener = RealmChangeListener<RealmResults<T>>{ value = it}
    override fun onActive() = realmResults.addChangeListener(listener)
    override fun onInactive() = realmResults.removeChangeListener(listener)
}

@JvmName("DB_Helper")
fun <T: RealmObject> RealmResults<T>.asLiveData() = LiveRealmData<T>(this)


fun Realm.MyDataDao() = MyDataDao(this)
class MyDataDao(val mRealm: Realm) {
    fun getLiveData(): LiveData<RealmResults<MyData>> {
        return mRealm.where<MyData>()
            .findAll()
            .asLiveData()
    }

    fun insert(name: String, age: Int) {
        mRealm.executeTransactionAsync{
            val data = MyData().apply {
                this.name = name
                this.age = age
            }

            it.insert(data)
        }
    }

    fun delete(name: String, age: Int) {
        mRealm.executeTransactionAsync{
            val delete = it.where<MyData>()
                .equalTo("name", name)
                .equalTo("age", age)
                .findFirst()

            delete?.deleteFromRealm()
        }
    }

}