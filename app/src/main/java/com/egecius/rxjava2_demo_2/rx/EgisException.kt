package com.egecius.rxjava2_demo_2.rx

class EgisException(message: String? = null) : Exception(message) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EgisException) return false
        return true
    }

}