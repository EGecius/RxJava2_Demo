package com.egecius.rxjava2_demo_2.rx._patterns.cashedrepo

import io.reactivex.Observable

open class PersistenceRepo : Repo {
    override fun getUsers(): Observable<List<User>> {
        TODO("not implemented")
    }
}