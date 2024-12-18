package _4.service.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _4.command.OwnerCommand;
import _4.domain.OwnerDTO;
import _4.mapper.OwnerMapper;


@Service
public class OwnerRegistService {
	
	@Autowired
	OwnerMapper ownerMapper;
	
	public void execute(OwnerCommand ownerCommand) {
		OwnerDTO ownerDTO = new OwnerDTO();
		
		ownerDTO.setOwnerId(ownerCommand.getOwnerId());
		ownerDTO.setOwnerPw(ownerCommand.getOwnerPw());
		ownerDTO.setOwnerName(ownerCommand.getOwnerName());
		//ownerDTO.setOwnerBirth(ownerCommand.getOwnerBirth());
		ownerDTO.setOwnerEmail(ownerCommand.getOwnerEmail());
		ownerDTO.setOwnerPhone(ownerCommand.getOwnerPhone());
		
		System.out.println(ownerDTO);
		ownerMapper.ownerForm(ownerDTO);
	}
	
}
