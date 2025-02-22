package _4.service.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import _4.command.OwnerCommand;
import _4.domain.OwnerDTO;
import _4.mapper.OwnerMapper;
import _4.mapper.service.AutoNumService;


@Service
public class OwnerRegistService {
	
	@Autowired
	OwnerMapper ownerMapper;
	
	@Autowired
	AutoNumService autoNumService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void execute(OwnerCommand ownerCommand) {
		OwnerDTO ownerDTO = new OwnerDTO();
		
		ownerDTO.setOwnerId(ownerCommand.getOwnerId());
		String encodePw = passwordEncoder.encode(ownerCommand.getOwnerPw());
		ownerDTO.setOwnerPw(encodePw);
		ownerDTO.setOwnerName(ownerCommand.getOwnerName());
		ownerDTO.setOwnerBirth(ownerCommand.getOwnerBirth());
		ownerDTO.setOwnerEmail(ownerCommand.getOwnerEmail());
		ownerDTO.setOwnerPhone(ownerCommand.getOwnerPhone());
		
		String ownerNum = autoNumService.execute("OWNER", "OWNER_NUM", "owner_");
		ownerDTO.setOwnerNum(ownerNum);
		
		ownerMapper.ownerFormInsert(ownerDTO);
	}
	
}
