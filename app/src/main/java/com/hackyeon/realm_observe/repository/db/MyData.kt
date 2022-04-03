package com.hackyeon.realm_observe.repository.db

import io.realm.RealmObject

open class MyData: RealmObject() {
    var name: String? = ""
    var age: Int? = 0
}