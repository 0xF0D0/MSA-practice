package com.fodo.msa.jwtService.model

import java.io.Serializable

public class JwtResponse : Serializable {
    companion object {
        private val serialVersionUID: Long = -8091879091924046844
    }

    private val jwttoken: String

    constructor(jwttoken: String) {
        this.jwttoken = jwttoken
    }

    public fun getToken(): String {
        return this.jwttoken
    }
}
