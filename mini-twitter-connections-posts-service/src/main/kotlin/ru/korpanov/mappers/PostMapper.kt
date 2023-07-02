package ru.korpanov.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.openapitools.model.Post
import org.openapitools.model.User
import ru.korepanov.minitwitterservice.tables.pojos.Posts
import ru.korepanov.minitwitterservice.tables.pojos.Users


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PostMapper {
    @Mappings(
        Mapping(source = "date", target = "postCreate")
    )
    fun toJooqPost(post: Post): Posts

    @Mappings(
        Mapping(source = "postCreate", target = "date")
    )
    fun toOpenapiPost(post: Posts): Post
}