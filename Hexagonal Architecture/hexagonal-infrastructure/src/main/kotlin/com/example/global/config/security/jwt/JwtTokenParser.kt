package com.example.global.config.security.jwt

import com.example.global.exception.ExpiredTokenException
import com.example.global.exception.InternalServerErrorException
import com.example.global.exception.InvalidTokenException
import com.example.global.config.security.jwt.property.JwtProperties
import com.example.global.config.security.principle.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtTokenParser(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService
) {

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parser()
                .setSigningKey(jwtProperties.secretKey)
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            when (e) {
                is InvalidClaimException -> throw InvalidTokenException.EXCEPTION
                is ExpiredJwtException -> throw ExpiredTokenException.EXCEPTION
                else -> throw InternalServerErrorException.EXCEPTION
            }
        }
    }

    fun getAuthentication(token: String): Authentication {

        val claims = getClaims(token)

        val authDetails = authDetailsService.loadUserByUsername(
            claims.subject ?: throw InvalidTokenException.EXCEPTION
        )

        return UsernamePasswordAuthenticationToken(authDetails, "", authDetails.authorities)
    }
}
