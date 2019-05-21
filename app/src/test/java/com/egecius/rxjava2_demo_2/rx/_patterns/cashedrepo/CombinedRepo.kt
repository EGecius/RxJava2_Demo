package com.egecius.rxjava2_demo_2.rx._patterns.cashedrepo

import io.reactivex.Observable

class CombinedRepo(val networkRepo: NetworkRepo, val persistenseRepo: PersistenseRepo) : Repo {

    override fun getUsers(): Observable<User> {
        return Observable.empty()
    }
}