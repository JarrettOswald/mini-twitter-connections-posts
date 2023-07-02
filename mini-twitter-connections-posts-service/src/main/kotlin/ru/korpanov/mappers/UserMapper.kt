package ru.korpanov.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.openapitools.model.User
import ru.korepanov.minitwitterservice.tables.pojos.Users


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {
    @Mappings(
        Mapping(source = "uuid", target = "id")
    )
    fun map(user: User): Users
}