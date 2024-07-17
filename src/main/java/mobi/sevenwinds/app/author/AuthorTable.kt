package mobi.sevenwinds.app.author

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.CurrentDateTime
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


object AuthorTable : IntIdTable("author") {
    val fullName = varchar("full_name", 255)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime())
}

class AuthorEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AuthorEntity>(AuthorTable)

    var fullName by AuthorTable.fullName
    var createdAt by AuthorTable.createdAt

    fun toResponse(): AuthorResponse {
        return AuthorResponse(fullName, createdAt)
    }
}

class DateTimeSerializer : JsonSerializer<DateTime>() {
    override fun serialize(value: DateTime, gen: JsonGenerator, serializers: SerializerProvider) {
        val formattedDate = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss").print(value)
        gen.writeString(formattedDate)
    }
}