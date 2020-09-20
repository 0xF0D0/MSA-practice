package com.fodo.msa.jwtService.model

import java.io.Serializable

public class JwtRequest(public var username: String) : Serializable {
    companion object {
        private val serialVersionUID: Long = 5926468583005150707
    }
}
