package com.example.easybuyapi.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    /** sha key.**/
private String
hString = "6A586E5A7234753778214125442A472D4B6150645367566B5970337335763879";

/** This method returns the stored values in the token.
 *
 * @param token
 * @return string
   **/
    public final String extractUserId(final String token) {
        return extractClaim(
                token,
                Claims::getSubject);
    }

    /** The extractClaim method is a utility function for
     * extracting a specific claim from a JWT using a provided
     * function. It abstracts away the process of extracting
     * the claim, making the code more modular and
     * potentially reusable.
 *
 * @param <T>
 * @param claimsResolver
 * @param token
 * @return string
   **/
    public final <T> T extractClaim(final String token,
     final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

        /** The generateToken method is an overloaded method
         * it calls the other generateToken method with an instance of
         * userDetails as a parameter.
        *
        * @param userDetails
        * @return string
        **/
    public final  String generateToken(final UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }


            /** The generateToken method generates a token
             * with stored values such as the user details, time issued,
             * expirationdate
             * .
 *
 * @param extraClaims
 * @param userDetails
 * @return string
   **/

    public final String generateToken(final Map<String,
     Object> extraClaims, final UserDetails userDetails) {
        return Jwts.builder().setClaims(
            extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(
                System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /** Check the valisity of the token.
 *
 * @param token
 * @param userDetails
 * @return string
   **/

    public final boolean isTokenValid(final String token,
    final UserDetails userDetails) {
        final String username = extractUserId(token);
        return (username.equals(
            userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(final String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(hString);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
