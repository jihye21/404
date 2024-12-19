package _4.mapper;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.StoreApplicationDTO;

@Mapper
public interface StoreMapper {
	public void storeFormInsert(StoreApplicationDTO storeDTO);
	public String checkStoreAppl(String ownerNum);
}
