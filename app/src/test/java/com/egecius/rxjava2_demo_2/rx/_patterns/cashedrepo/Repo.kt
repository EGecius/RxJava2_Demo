package com.egecius.rxjava2_demo_2.rx._patterns.cashedrepo

import io.reactivex.Observable

interface Repo {

    fun getUsers() : Observable<User>
}