package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Audit;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.AuditDto;
import java.util.List;

public class AuditConverter implements Converter<AuditDto, Audit> {
    @Override
    public Audit toEntity(AuditDto dto) {
        if (dto == null) {
            return null;
        }
        Audit audit = new Audit();
        audit.setId(dto.getId());
        audit.setUserId(dto.getUserId());
        audit.setEventType(dto.getEventType());
        audit.setCreated(dto.getCreated());
        return audit;
    }

    @Override
    public List<Audit> toEntityList(List<AuditDto> list) {
        return null;
    }
}
