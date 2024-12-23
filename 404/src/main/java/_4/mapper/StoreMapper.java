package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.StoreDTO;

@Mapper
public interface StoreMapper {
	public StoreDTO storeSelectOne(String ownerNum);
	
	public List<StoreDTO> storeInfoSelectAll();

	public List<StoreDTO> storeSelectAll();
}
