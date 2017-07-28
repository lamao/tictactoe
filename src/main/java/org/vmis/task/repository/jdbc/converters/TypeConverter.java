package org.vmis.task.repository.jdbc.converters;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
public interface TypeConverter<STORAGE, MODEL> {
    STORAGE toStorage(MODEL source);
    MODEL toModel(STORAGE source);
}
