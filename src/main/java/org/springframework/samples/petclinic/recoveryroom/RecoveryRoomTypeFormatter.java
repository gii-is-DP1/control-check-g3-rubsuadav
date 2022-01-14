package org.springframework.samples.petclinic.recoveryroom;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class RecoveryRoomTypeFormatter implements Formatter<RecoveryRoomType>{
	
	private RecoveryRoomService recoveryRoomService;
	
	@Autowired
	public RecoveryRoomTypeFormatter(RecoveryRoomService recoveryRoomService) {
		this.recoveryRoomService = recoveryRoomService;
	}

    @Override
    public String print(RecoveryRoomType object, Locale locale) {
        return object.getName();
    }

    @Override
    public RecoveryRoomType parse(String text, Locale locale) throws ParseException {
    	List<RecoveryRoomType> recoveryRoomTypes = this.recoveryRoomService.getAllRecoveryRoomTypes();
		for(RecoveryRoomType recoveryRoomType: recoveryRoomTypes) {
			if(recoveryRoomType.getName().equals(text))
				return recoveryRoomType;
		}
		throw new ParseException("recoveryRoomType not found: " + text, 0);
    }
    
}
