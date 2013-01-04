package org.hopto.nexoff.race.converter;

import org.hopto.nexoff.race.domain.Horse;
import org.hopto.nexoff.race.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToHorseConverter implements Converter<String, Horse> {

	@Autowired
	private HorseService horseService;
	
	@Override
	public Horse convert(String source) {
		return horseService.findById(Long.parseLong(source));
	}
	

}
