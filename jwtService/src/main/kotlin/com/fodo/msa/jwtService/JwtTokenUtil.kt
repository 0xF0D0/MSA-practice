package com.fodo.msa.jwtService

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.Date
import java.util.function.Function

public var JWT_TOKEN_VALIDITY: Long = 5 * 60 * 60

@Component
public class JwtTokenUtil : Serializable {
    companion object {
        private val serialVersionUID: Long = -2550185165626007488
    }
    private val secret = "javainuse"

    public fun getUsernameFromToken(token: String): String {
        return getClaimFromToken(token, Claims::getSubject)
    }

    public fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token, Claims::getExpiration)
    }

    public fun <T> getClaimFromToken(token: String, claimsResolver: (c: Claims) -> T): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver(claims)
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    public fun generateToken(username: String): String {
        val claims = mutableMapOf<String, Any>()
        return doGenerateToken(claims, username)
    }

    private fun doGenerateToken(claims: MutableMap<String, Any>, subject: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    public fun validateToken(token: String, username: String): Boolean {
        val username = getUsernameFromToken(token)
        return username == username && !isTokenExpired(token)
    }
}
