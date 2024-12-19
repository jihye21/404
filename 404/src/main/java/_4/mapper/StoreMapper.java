package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.StoreDTO;

@Mapper
public interface StoreMapper {

	public void storeFormInsert(StoreDTO storeDTO);
	
}
