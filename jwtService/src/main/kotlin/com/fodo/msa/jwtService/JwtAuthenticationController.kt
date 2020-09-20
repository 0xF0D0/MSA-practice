package com.fodo.msa.jwtService.controller

import java.util.Objects
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

import com.fodo.msa.jwtService.JwtTokenUtil
import com.fodo.msa.jwtService.model.JwtRequest
import com.fodo.msa.jwtService.model.JwtResponse

@RestController
@CrossOrigin
public class JwtAuthenticationController @Autowired constructor(
    private val jwtTokenUtil: JwtTokenUtil
) {

    @RequestMapping("/generate")
    @PostMapping
    public fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<JwtResponse> {
        val token = jwtTokenUtil.generateToken(authenticationRequest.username)

        return ResponseEntity.ok(JwtResponse(token))
    }

    private fun authenticate(username: String, password: String) {
//        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
    }
}
