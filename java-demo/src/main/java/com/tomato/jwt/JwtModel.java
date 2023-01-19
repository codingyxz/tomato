package com.tomato.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;


/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/11/3 10:43 上午
 * @Version V1.0
 **/
public class JwtModel {


    public static void main(String[] args) {


        DefaultClaims claims = new DefaultClaims();
        claims.setSubject("forever");

        // 根据密钥将用户相关信息生成token
        String token = Jwts.builder().setClaims(claims)
                .setExpiration(null)
                .signWith(SignatureAlgorithm.HS512, "open@maycurECM")
                .compact();

        System.out.println(token);
    }
}
