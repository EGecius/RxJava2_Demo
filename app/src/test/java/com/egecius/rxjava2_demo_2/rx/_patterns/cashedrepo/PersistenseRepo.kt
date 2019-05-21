package com.egecius.rxjava2_demo_2.rx._patterns.cashedrepo

import io.reactivex.Observable

class PersistenseRepo : Repo {
    override fun getUsers(): Observable<User> {
        TODO("not implemented")
    }
}