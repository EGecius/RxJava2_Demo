package com.egecius.rxjava2_demo_2.rx._patterns.cashedrepo

import io.reactivex.Observable

class CombinedRepo(private val networkRepo: NetworkRepo,
                   private val persistenceRepo: PersistenceRepo) : Repo {

    override fun getUsers(): Observable<List<User>> {
        return Observable.concat(persistenceRepo.getUsers(),
                networkRepo.getUsers())
    }
}