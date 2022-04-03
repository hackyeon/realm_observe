package com.hackyeon.realm_observe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackyeon.realm_observe.repository.MyDataDao
import com.hackyeon.realm_observe.repository.db.MyData
import io.realm.Realm
import io.realm.RealmResults

class MainViewModel: ViewModel() {
    var mRealm: Realm? = null

    fun getMyDataLiveData(): LiveData<RealmResults<MyData>>?{
        return mRealm?.MyDataDao()?.getLiveData()
    }
    fun insertMyData(name: String, age: Int) {
        mRealm?.MyDataDao()?.insert(name, age)
    }
    fun deleteMyData(name: String, age: Int){
        mRealm?.MyDataDao()?.delete(name, age)
    }

    init {
        mRealm = Realm.getDefaultInstance()
    }

    override fun onCleared() {
        super.onCleared()
        mRealm?.close()
        mRealm = null
    }
}