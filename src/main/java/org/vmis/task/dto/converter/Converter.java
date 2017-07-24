package org.vmis.task.dto.converter;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface Converter<MODEL, DTO> {

    DTO toDto(MODEL model);

    MODEL fromDto(DTO dto);
}
