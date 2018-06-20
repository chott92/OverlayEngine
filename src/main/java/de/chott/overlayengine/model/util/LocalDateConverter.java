package de.chott.overlayengine.model.util;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate field) {
		return field == null ? null : Date.valueOf(field);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbValue) {
		return dbValue == null ? null : dbValue.toLocalDate();
	}

}
