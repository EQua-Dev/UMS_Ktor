package org.devstrike.expos.backendstrike.plugins

import io.ktor.server.application.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import org.devstrike.expos.backendstrike.domain.models.UserSession
import java.io.File

fun Application.configureSession(){
    install(Sessions){
        val secretEncryptKey = hex("00112233445566778899aabbccddeeff") //choose a key for the hashing
        val secretAuthKey = hex("02030405060708090a0b0c") //choose a key for the hashing
        cookie<UserSession>(
            name = "USER_SESSION", //name of the cookie,
        storage = directorySessionStorage(File(".sessions")) //directory to save the cookies
        ){
            //cookie.secure = true use if you are hosting your server in cloud
            //cookie.maxAge = 30.minutes to set the expiration of the cookie
            //code below is used to encrypt the cookie data before sending to the client
            //it is used if you decide to save the cookie session on the client side
            transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretAuthKey))
        }
    }
}