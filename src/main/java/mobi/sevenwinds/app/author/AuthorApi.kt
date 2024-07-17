package mobi.sevenwinds.app.author

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.papsign.ktor.openapigen.route.info
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route
import org.joda.time.DateTime

fun NormalOpenAPIRoute.author() {
    route("/author") {
        route("/add").post<Unit, AuthorResponse, AuthorRecord>(info("Добавить автора")) { _, body ->
            respond(AuthorService.addAuthor(body))
        }
    }
}

data class AuthorRecord(
    val fullName: String
)


data class AuthorResponse(
    val fullName: String,
    @JsonSerialize(using = DateTimeSerializer::class)
    val createdAt: DateTime
)