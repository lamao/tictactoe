package org.vmis.task.dto.converter;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface Converter<MODEL, DTO> {

    default DTO toDto(MODEL model) {
        throw new UnsupportedOperationException("Not implemented");
    }

    default MODEL fromDto(DTO dto) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
