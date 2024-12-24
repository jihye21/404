package _4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import _4.domain.InquireDTO;

@Mapper
public interface InquireMapper {

	public void memberInquireInsert(InquireDTO inquireDTO);

	public void ownerInquireInsert(InquireDTO inquireDTO);

	public List<InquireDTO> memberInquireSelectAll(String memNum);

	public List<InquireDTO> ownerInquireSelectAll(String ownerNum);
	
	public List<InquireDTO> employeeInquireSelectAll();

	public InquireDTO memberInquireSelect(String memNum, String inquireNum);

	public InquireDTO ownerInquireSelect(String ownerNum, String inquireNum);
	
	public InquireDTO employeeInquireSelect(String inquireNum);
	
	public void memberInquireUpdate(InquireDTO inquireDTO);

	public void ownerInquireUpdate(InquireDTO inquireDTO);

	public String memberInquireCheckService(String memNum, String inquireNum);

	public String ownerInquireCheckService(String ownerNum, String inquireNum);
	
	public String employeeInquireCheckService(String inquireNum);

	public void memberInquireDelete(String memNum, String inquireNum);

	public void ownerInquireDelete(String ownerNum, String inquireNum);

	public void employeeMemberInquireUpdate(InquireDTO inquireDTO);

	public void employeeOwnerInquireUpdate(InquireDTO inquireDTO);

	
	
}
