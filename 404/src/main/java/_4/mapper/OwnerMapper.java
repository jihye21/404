package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.OwnerDTO;

@Mapper
public interface OwnerMapper {

	public void ownerFormInsert(OwnerDTO ownerDTO);
	
}
