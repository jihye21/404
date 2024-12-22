package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.StoreApplicationDTO;
import _4.domain.StoreDTO;

@Mapper
public interface StoreApplMapper {
	public void storeApplicationInsert(StoreApplicationDTO storeDTO);
	public String checkStoreAppl(String ownerNum);
	public List<StoreApplicationDTO> storeApplSelectAll();
	public StoreApplicationDTO storeApplSelectOne(String storeApplNum);
	public void storeApply(StoreDTO dto);
	public void storeApplDelete(String storeApplNum);
}
