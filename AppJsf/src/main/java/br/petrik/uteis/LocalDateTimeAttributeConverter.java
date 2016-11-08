package br.petrik.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Juan, 8 de nov de 2016
 *
 *  Classe que será utilizada para realizar as conversões de data.
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {


    // Transformando em timestamp na hr de gravar no banco
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {

    	if(localDateTime != null)
    		return Timestamp.valueOf(localDateTime);

    	return null;

    }

    // transforma um timestamp em localDateTime
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {

    	if(timestamp != null)
    		return timestamp.toLocalDateTime();

    	return null;
    }
}