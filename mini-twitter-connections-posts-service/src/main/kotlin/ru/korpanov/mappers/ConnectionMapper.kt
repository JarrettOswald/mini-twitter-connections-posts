package ru.korpanov.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.openapitools.model.Connection
import ru.korepanov.minitwitterservice.tables.pojos.Connections


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ConnectionMapper {
    @Mappings(
        Mapping(source = "uuid", target = "id")
    )
    fun toJooqConnection(connection: Connection): Connections

    @Mappings(
        Mapping(source = "id", target = "uuid")
    )
    fun toOpenapiConnection(connections: Connections): Connection
}