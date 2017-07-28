package org.vmis.task.dto.converter;

import org.springframework.stereotype.Component;
import org.vmis.task.dto.model.LocationDto;
import org.vmis.task.model.Location;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Component
public class LocationDtoConverter implements Converter<Location, LocationDto> {

    @Override
    public LocationDto toDto(Location location) {
        LocationDto dto = new LocationDto();
        dto.x = location.getX();
        dto.y = location.getY();
        return dto;
    }

    @Override
    public Location fromDto(LocationDto locationDto) {
        Location entity = new Location();
        entity.setX(locationDto.x);
        entity.setY(locationDto.y);
        return entity;
    }
}
