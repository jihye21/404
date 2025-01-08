package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.DepositDTO;
import _4.domain.StoreDTO;

@Mapper
public interface StoreMapper {
	public StoreDTO storeSelectOne(String ownerNum);

	public List<StoreDTO> storeSelectAll();

	public String WishCheck(String memNum, String storeNum);

	public List<StoreDTO> wishSelectList(String memberNum);
	
	public int storeUpdate(StoreDTO dto);
	
	public String storeNumSelect(String ownerNum);
	
	public void depositSettingUpdate(DepositDTO dto);
	public List<DepositDTO> depositSettingSelect(String storeNum);
}
