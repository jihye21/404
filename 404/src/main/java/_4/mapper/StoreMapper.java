package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import _4.domain.DepositDTO;
import _4.domain.StoreDTO;
import _4.domain.WaitNumDTO;

@Mapper
public interface StoreMapper {
	public Integer waitNumCheck(String storeNum);
	public void waitNumInsert(WaitNumDTO dto);
	public void calledWaitNumInsert(@Param("bookNum") String bookNum, @Param("storeNum") String storeNum);
	public WaitNumDTO waitNumSelectOne(String bookNum);
	public List<WaitNumDTO> waitNumSelectAll(String storeNum);
	public void waitNumDelete(String bookNum);
	public String calledWaitNumSelect(String storeNum);
	public void calledWaitNumDelete(String bookNum);
	
	
	public StoreDTO storeSelectOne(String ownerNum);
	public String storeNumSelect(String ownerNum);
	public List<DepositDTO> depositSettingSelect(String storeNum);
	public List<StoreDTO> categoryStoreSelect(String category);
	
	public List<StoreDTO> storeSelectAll();
	public List<StoreDTO> storeSearch(String storeName);

	public String WishCheck(String memNum, String storeNum);
	public List<StoreDTO> wishSelectList(String memberNum);
	
	public int storeUpdate(StoreDTO dto);
	public void depositSettingUpdate(DepositDTO dto);
	
	
	
	
	
	
}
