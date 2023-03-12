package org.devstrike.expos.backendstrike.routes

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.collections.*
import io.ktor.util.pipeline.*
import org.devstrike.expos.backendstrike.domain.models.ApiRequest
import org.devstrike.expos.backendstrike.domain.models.Endpoint
import org.devstrike.expos.backendstrike.domain.models.User
import org.devstrike.expos.backendstrike.domain.models.UserSession
import org.devstrike.expos.backendstrike.domain.repository.UserDataSource
import org.devstrike.expos.backendstrike.util.Constants.AUDIENCE
import org.devstrike.expos.backendstrike.util.Constants.ISSUER

fun Route.tokenVerificationRoute(app: Application, userDataSource: UserDataSource) {
    post(Endpoint.TokenVerification.path) {
        val request = call.receive<ApiRequest>()
        if (request.tokenId.isNotEmpty()) {
            val result = verifyGoogleTokenId(tokenId = request.tokenId)
            if (result != null) {
                saveUserToDatabase(
                    app = app,
                    result, userDataSource
                )
            } else {
                app.log.info("TOKEN VERIFICATION FAILED")
                call.respondRedirect(Endpoint.Unauthorized.path)
            }
        } else {
            app.log.info("EMPTY TOKEN ID")
            call.respondRedirect(Endpoint.Unauthorized.path)
        }

    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.saveUserToDatabase(
    app: Application,
    result: GoogleIdToken,
    userDataSource: UserDataSource
) {
    val sub = result.payload["sub"].toString()
    val name = result.payload["name"].toString()
    val emailAddress = result.payload["email"].toString()
    val profilePhoto = result.payload["picture"].toString()
    val user = User(
        sub,
        name,
        emailAddress,
        profilePhoto
    )

    val response = userDataSource.saveUserInfo(user = user)
    if (response) {
        app.log.info("USER SUCCESSFULLY SAVED/RETRIEVED")
        call.sessions.set(UserSession(id = sub, name = name))
        call.respondRedirect(Endpoint.Authorized.path)
    } else {
        app.log.info("ERROR SAVING USER")
        call.respondRedirect(Endpoint.Unauthorized.path)
    }

}

//function to perform the Google verification
//it checks the token ID gotten from the client and checks for its authentication against the Google server
fun verifyGoogleTokenId(tokenId: String): GoogleIdToken? {
    return try {
        val verifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory())
            .setAudience(sharedListOf(AUDIENCE))
            .setIssuer(ISSUER)
            .build()
        verifier.verify(tokenId)
    } catch (e: Exception) {
        null
    }

}