package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.BookDTO;
import _4.domain.GroupDTO;
import _4.domain.GroupShareDTO;
import jakarta.servlet.http.HttpSession;

@Mapper
public interface GroupMapper {
	
	public List<GroupDTO> groupMemberSearch(String memName, String memNum);

	public void groupInsert(GroupDTO groupDTO);

	public void groupMemberAlarmInsert(GroupDTO groupDTO);

	public List<GroupDTO> groupSelectAll(String memNum);

	public void groupMemberInsert(GroupDTO groupDTO);

	public Integer groupAlarmCount(String memNum);

	public List<GroupDTO> groupAlarmSelectAll(String memNum);

	public void groupAlarmEnter(GroupDTO groupDTO);

	public void groupAlarmQuit(GroupDTO groupDTO);

	public List<GroupDTO> groupMemberSelectAll(GroupDTO groupNumList);

	public String groupNameSelect(GroupDTO groupDTO);

	public void groupDutchAlarmInsert(String groupNum, Integer dutchPrice, String memNum, String bookNum);

	public Integer groupDutchAlarmCount(String memNum);

	public List<GroupDTO> groupDutchAlarmSelectAll(String memNum);

	public void groupDutchFail(List<BookDTO> bookDTO);

	public List<GroupDTO> groupPaymentHistorySelectAll(String groupNum);

	public List<GroupDTO> memberDutchPaymentCheckSelectOne(String memNum, String groupNum);

	public GroupShareDTO groupMemberSelectOne(GroupShareDTO groupShareDTO);

	public BookDTO groupMemberReview(String memNum, String bookNum);

	
}
