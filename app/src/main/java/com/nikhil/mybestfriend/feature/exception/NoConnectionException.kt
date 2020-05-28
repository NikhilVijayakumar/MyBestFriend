package com.nikhil.mybestfriend.feature.exception

import java.lang.RuntimeException

class NoConnectionException : RuntimeException() {
    override val message: String?
        get() = "Unable to connect to internet"
}