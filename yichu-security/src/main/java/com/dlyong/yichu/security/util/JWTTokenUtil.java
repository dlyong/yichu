package com.dlyong.yichu.security.util;

import com.dlyong.yichu.common.constant.JWTConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWTToken 生成工具类
 *
 */
public class JWTTokenUtil {
    /*日志*/
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTTokenUtil.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     * 通过JWT负载 生成token
     * claims 格式 ：【用户名】【创建时间】【过期时间】
     */
    private String generateToken(Map<String,Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpiration())
                .signWith(SignatureAlgorithm.ES512,secret)
                .compact();
    }

    /**
     * 生成过期时间
     * @return
     */
    private Date generateExpiration() {
        return new Date(System.currentTimeMillis()+expiration);
    }

    /**
     * 从token 中获取JWT的负载
     */
    private Claims getClaimsFromToken(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            LOGGER.info("JWT格式验证失败:{}",token);
        }
        return claims;
    }
    /**
     * 判断token 是否已经失效
     */
    private boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(JWTConstant.JWT_CLAIMS_USERNAME,userDetails.getUsername());
        claims.put(JWTConstant.JWT_CLAIMS_CREATEDATE,new Date());
        claims.put(JWTConstant.JWT_CLAIMS_EXPIREDDATE,expiration);
        return generateToken(claims);
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
