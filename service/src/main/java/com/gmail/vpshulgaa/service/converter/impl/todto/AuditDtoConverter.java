package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Audit;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.AuditDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("auditDtoConverter")
public class AuditDtoConverter implements DtoConverter<AuditDto, Audit> {
    @Override
    public AuditDto toDto(Audit entity) {
        if (entity == null) {
            return null;
        }
        AuditDto auditDto = new AuditDto();
        auditDto.setId(entity.getId());
        auditDto.setEventType(entity.getEventType());
        auditDto.setCreated(entity.getCreated());
        return auditDto;
    }

    @Override
    public List<AuditDto> toDtoList(List<Audit> list) {
        return null;
    }
}
