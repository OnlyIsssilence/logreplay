package com.sogou.map.logreplay.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tag_info")
public class TagInfo extends AbstractBean {
	
	public static final Integer COMMON_TAG_NO_MIN_VALUE = 10000;

	@Id
	@Column
	private Long id;
	
	@Column(name = "tag_no")
	private Integer tagNo;

	@Column
	private String name;
	
	@Column(name = "page_info_id")
	private Long pageInfoId;
	
	@Column(name = "page_no")	// ���࣬Ϊ�˲�ѯ����
	private Integer pageNo;
	
	@Transient
	private PageInfo pageInfo;
	
	@Column(name = "action_id")
	private Long actionId;
	
	@Column(name = "target_id")
	private Long targetId;
	
	@Column
	private String comment;
	
	@Column(name = "origin_version")
	private Integer originVersion;
	
	@Column(name = "inspect_status")
	private Integer inspectStatus;
	
	@Column(name = "create_time")
	private Timestamp createTime;
	
	@Column(name = "update_time")
	private Timestamp updateTime;
	
	public TagInfo() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTagNo() {
		return tagNo;
	}

	public void setTagNo(Integer tagNo) {
		this.tagNo = tagNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getPageInfoId() {
		return pageInfoId;
	}

	public void setPageInfoId(Long pageInfoId) {
		this.pageInfoId = pageInfoId;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getOriginVersion() {
		return originVersion;
	}

	public void setOriginVersion(Integer originVersion) {
		this.originVersion = originVersion;
	}

	public Integer getInspectStatus() {
		return inspectStatus;
	}

	public void setInspectStatus(Integer inspectStatus) {
		this.inspectStatus = inspectStatus;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	public enum InspectStatus {
		
		SUCCESS(1), ERROR(2), UNCHECKED(0), UNKNOWN(-1);
		
		private int intValue;
		
		private InspectStatus(int value) {
			this.intValue = value;
		}
		
		public int getIntValue() {
			return intValue;
		}
		
		public static InspectStatus from(Integer intValue) {
			if(intValue == null) {
				return UNKNOWN;
			}
			for(InspectStatus status: values()) {
				if(intValue.equals(status.intValue)) {
					return status;
				}
			}
			return UNKNOWN;
		}
	}
	
}
