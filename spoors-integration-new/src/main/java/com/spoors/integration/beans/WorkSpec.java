package com.spoors.integration.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class WorkSpec implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long workId;
	private Long workSpecId;
	private String workSpecTitle;
	private String workSpecDescription;
	private String formSpecUniqueId;
	private Long createdBy;
	private Long modifiedBy;
	private int companyId;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String createdTime;
	private String modifiedTime;
	private Long copiedFrom=0l;
	private String copiedFromTitle;
	private Long formId;
	private int isSystemDefined=0;
	private int purpose=-1;
	@JsonProperty(access = Access.READ_ONLY)
	private long formSpecId;
	private Long skeletonWorkSpecId;
	private int productId;
	private String externalWorkSpecId;
	
	
	private Boolean deleted=false;
	
	private boolean importWork = true;
	
	private String mobileLayout;
	private boolean removeBlankLines;
	
	private String rejectionFormSpecUniqueId;
	private long rejectionFormSpecId;
	private String rejectionFormSpecTitle;
	private boolean createRejectionFormSpec = false;
	
	private boolean captureRejectionReasons = false;
	
	private boolean workSharing;
	
	private boolean enableWorkCheckIn;
	
	private boolean enableWorkOwnerToPerformActions = true;
	private boolean enableWorkCreatorToPerformActions = true;
	private boolean restrictHierarchyToPerformActions ;
	private boolean enableAssignActions = true;
	private long count;
	
	private Integer syncIncompletedWorksForMobile;
	
	private boolean canRejectWorkInvitation = true;
	
	private boolean canRejectWork = true;
	
	private boolean visibleOnlyManagerWorks = false;
	private boolean sendOnlyActionableWorksToManager = false;
	
	private List<Long> eligibleEmployeeIdsForWorkSync;
	
	private boolean globalSearch;
	
	private boolean hideOnEndTimeComplete = false;
	
	private boolean hideOnWorkComplete =false;
	
	private boolean deleteOnEndTimeComplete=false;
	private boolean cleanOnWorkComplete=false;
	
	private boolean canModifyWork = true; 
	
	
	private boolean cleanUpNotActionableWorks;
	private boolean openToCustomer;
	private boolean allowWorkCreationFromMobile = true;
	private boolean onlineWork = false;
	private boolean accessibleToEmp = false;
	
	private boolean enableEndTimeDurationCheck = false;
	private long workEndTimeDuration;
	private boolean enableAssigmentService = false;
	private boolean resendUnAssignedWorks = false;
	private Integer limitForWorkAssignMentService;
	private Integer durationForUnAssigment;
	private Integer maxWorksForAcceptence;
	
	private boolean workSpecPermission;
	private boolean viewPermission;
	private boolean addPermission;
	private boolean modifyPermission;
	private boolean deletePermission;
	
	private boolean downloadWorkMedias;
	
	public boolean isViewPermission() {
		return viewPermission;
	}
	public void setViewPermission(boolean viewPermission) {
		this.viewPermission = viewPermission;
	}
	public boolean isAddPermission() {
		return addPermission;
	}
	public void setAddPermission(boolean addPermission) {
		this.addPermission = addPermission;
	}
	public boolean isModifyPermission() {
		return modifyPermission;
	}
	public void setModifyPermission(boolean modifyPermission) {
		this.modifyPermission = modifyPermission;
	}
	public boolean isDeletePermission() {
		return deletePermission;
	}
	public void setDeletePermission(boolean deletePermission) {
		this.deletePermission = deletePermission;
	}
	public boolean isEnableEndTimeDurationCheck() {
		return enableEndTimeDurationCheck;
	}
	public void setEnableEndTimeDurationCheck(boolean enableEndTimeDurationCheck) {
		this.enableEndTimeDurationCheck = enableEndTimeDurationCheck;
	}
	public long getWorkEndTimeDuration() {
		return workEndTimeDuration;
	}
	public void setWorkEndTimeDuration(long workEndTimeDuration) {
		this.workEndTimeDuration = workEndTimeDuration;
	}
	public boolean isAllowWorkCreationFromMobile() {
		return allowWorkCreationFromMobile;
	}
	public void setAllowWorkCreationFromMobile(boolean allowWorkCreationFromMobile) {
		this.allowWorkCreationFromMobile = allowWorkCreationFromMobile;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public String getWorkSpecTitle() {
		return workSpecTitle;
	}
	public void setWorkSpecTitle(String workSpecTitle) {
		this.workSpecTitle = workSpecTitle;
	}
	public String getWorkSpecDescription() {
		return workSpecDescription;
	}
	public void setWorkSpecDescription(String workSpecDescription) {
		this.workSpecDescription = workSpecDescription;
	}
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	public int getIsSystemDefined() {
		return isSystemDefined;
	}
	public void setIsSystemDefined(int isSystemDefined) {
		this.isSystemDefined = isSystemDefined;
	}
	public int getPurpose() {
		return purpose;
	}
	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Long getCopiedFrom() {
		return copiedFrom;
	}
	public void setCopiedFrom(Long copiedFrom) {
		this.copiedFrom = copiedFrom;
	}
	
	public String getCopiedFromTitle() {
		return copiedFromTitle;
	}
	public void setCopiedFromTitle(String copiedFromTitle) {
		this.copiedFromTitle = copiedFromTitle;
	}

	
	public String getCreatedDate() {
		return getCreatedTime().substring(0, getCreatedTime().indexOf(" "));
	}
	
	public long getFormSpecId() {
		return formSpecId;
	}
	
	public void setFormSpecId(long formSpecId) {
		this.formSpecId = formSpecId;
	}
	public Long getSkeletonWorkSpecId() {
		return skeletonWorkSpecId;
	}
	public void setSkeletonWorkSpecId(Long skeletonWorkSpecId) {
		this.skeletonWorkSpecId = skeletonWorkSpecId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getExternalWorkSpecId() {
		return externalWorkSpecId;
	}
	public void setExternalWorkSpecId(String externalWorkSpecId) {
		this.externalWorkSpecId = externalWorkSpecId;
	}
	public boolean isImportWork() {
		return importWork;
	}
	public void setImportWork(boolean importWork) {
		this.importWork = importWork;
	}
	public String getMobileLayout() {
		return mobileLayout;
	}
	public void setMobileLayout(String mobileLayout) {
		this.mobileLayout = mobileLayout;
	}
	public boolean isRemoveBlankLines() {
		return removeBlankLines;
	}
	public void setRemoveBlankLines(boolean removeBlankLines) {
		this.removeBlankLines = removeBlankLines;
	}
	public String getRejectionFormSpecUniqueId() {
		return rejectionFormSpecUniqueId;
	}
	public void setRejectionFormSpecUniqueId(String rejectionFormSpecUniqueId) {
		this.rejectionFormSpecUniqueId = rejectionFormSpecUniqueId;
	}
	public long getRejectionFormSpecId() {
		return rejectionFormSpecId;
	}
	public void setRejectionFormSpecId(long rejectionFormSpecId) {
		this.rejectionFormSpecId = rejectionFormSpecId;
	}
	public boolean isCreateRejectionFormSpec() {
		return createRejectionFormSpec;
	}
	public void setCreateRejectionFormSpec(boolean createRejectionFormSpec) {
		this.createRejectionFormSpec = createRejectionFormSpec;
	}
	public boolean isCaptureRejectionReasons() {
		return captureRejectionReasons;
	}
	public void setCaptureRejectionReasons(boolean captureRejectionReasons) {
		this.captureRejectionReasons = captureRejectionReasons;
	}
	public String getRejectionFormSpecTitle() {
		return rejectionFormSpecTitle;
	}
	public void setRejectionFormSpecTitle(String rejectionFormSpecTitle) {
		this.rejectionFormSpecTitle = rejectionFormSpecTitle;
	}
	public boolean isWorkSharing() {
		return workSharing;
	}
	public void setWorkSharing(boolean workSharing) {
		this.workSharing = workSharing;
	}
	public boolean isEnableWorkCheckIn() {
		return enableWorkCheckIn;
	}
	public void setEnableWorkCheckIn(boolean enableWorkCheckIn) {
		this.enableWorkCheckIn = enableWorkCheckIn;
	}
	public boolean isEnableWorkOwnerToPerformActions() {
		return enableWorkOwnerToPerformActions;
	}
	public void setEnableWorkOwnerToPerformActions(
			boolean enableWorkOwnerToPerformActions) {
		this.enableWorkOwnerToPerformActions = enableWorkOwnerToPerformActions;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public Integer getSyncIncompletedWorksForMobile() {
		return syncIncompletedWorksForMobile;
	}
	public void setSyncIncompletedWorksForMobile(Integer syncIncompletedWorksForMobile) {
		this.syncIncompletedWorksForMobile = syncIncompletedWorksForMobile;
	}
	public boolean isCanRejectWorkInvitation() {
		return canRejectWorkInvitation;
	}
	public void setCanRejectWorkInvitation(boolean canRejectWorkInvitation) {
		this.canRejectWorkInvitation = canRejectWorkInvitation;
	}
	public boolean isEnableAssignActions() {
		return enableAssignActions;
	}
	public void setEnableAssignActions(boolean enableAssignActions) {
		this.enableAssignActions = enableAssignActions;
	}
	public boolean isCanRejectWork() {
		return canRejectWork;
	}
	public void setCanRejectWork(boolean canRejectWork) {
		this.canRejectWork = canRejectWork;
	}
	public boolean isVisibleOnlyManagerWorks() {
		return visibleOnlyManagerWorks;
	}
	public void setVisibleOnlyManagerWorks(boolean visibleOnlyManagerWorks) {
		this.visibleOnlyManagerWorks = visibleOnlyManagerWorks;
	}
	public List<Long> getEligibleEmployeeIdsForWorkSync() {
		return eligibleEmployeeIdsForWorkSync;
	}
	public void setEligibleEmployeeIdsForWorkSync(
			List<Long> eligibleEmployeeIdsForWorkSync) {
		this.eligibleEmployeeIdsForWorkSync = eligibleEmployeeIdsForWorkSync;
	}
	public boolean isGlobalSearch() {
		return globalSearch;
	}
	public void setGlobalSearch(boolean globalSearch) {
		this.globalSearch = globalSearch;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WorkSpec) {
			return getWorkSpecId().equals(((WorkSpec) obj).getWorkSpecId());
		} else {
			return super.equals(obj);
		}
	}
	public boolean isHideOnEndTimeComplete() {
		return hideOnEndTimeComplete;
	}
	public void setHideOnEndTimeComplete(boolean hideOnEndTimeComplete) {
		this.hideOnEndTimeComplete = hideOnEndTimeComplete;
	}
	public boolean isHideOnWorkComplete() {
		return hideOnWorkComplete;
	}
	public void setHideOnWorkComplete(boolean hideOnWorkComplete) {
		this.hideOnWorkComplete = hideOnWorkComplete;
	}
	public boolean isEnableWorkCreatorToPerformActions() {
		return enableWorkCreatorToPerformActions;
	}
	public void setEnableWorkCreatorToPerformActions(boolean enableWorkCreatorToPerformActions) {
		this.enableWorkCreatorToPerformActions = enableWorkCreatorToPerformActions;
	}
	public boolean isCanModifyWork() {
		return canModifyWork;
	}
	public void setCanModifyWork(boolean canModifyWork) {
		this.canModifyWork = canModifyWork;
	}
	public boolean isDeleteOnEndTimeComplete() {
		return deleteOnEndTimeComplete;
	}
	public void setDeleteOnEndTimeComplete(boolean deleteOnEndTimeComplete) {
		this.deleteOnEndTimeComplete = deleteOnEndTimeComplete;
	}
	public boolean isCleanOnWorkComplete() {
		return cleanOnWorkComplete;
	}
	public void setCleanOnWorkComplete(boolean cleanOnWorkComplete) {
		this.cleanOnWorkComplete = cleanOnWorkComplete;
	}
	public boolean isCleanUpNotActionableWorks() {
		return cleanUpNotActionableWorks;
	}
	public void setCleanUpNotActionableWorks(boolean cleanUpNotActionableWorks) {
		this.cleanUpNotActionableWorks = cleanUpNotActionableWorks;
	}
	public boolean isRestrictHierarchyToPerformActions() {
		return restrictHierarchyToPerformActions;
	}
	public void setRestrictHierarchyToPerformActions(
			boolean restrictHierarchyToPerformActions) {
		this.restrictHierarchyToPerformActions = restrictHierarchyToPerformActions;
	}
	public boolean isOpenToCustomer() {
		return openToCustomer;
	}
	public void setOpenToCustomer(boolean openToCustomer) {
		this.openToCustomer = openToCustomer;
	}
	public boolean isSendOnlyActionableWorksToManager() {
		return sendOnlyActionableWorksToManager;
	}
	public void setSendOnlyActionableWorksToManager(
			boolean sendOnlyActionableWorksToManager) {
		this.sendOnlyActionableWorksToManager = sendOnlyActionableWorksToManager;
	}
	public boolean isOnlineWork() {
		return onlineWork;
	}
	public void setOnlineWork(boolean onlineWork) {
		this.onlineWork = onlineWork;
	}
	public boolean isAccessibleToEmp() {
		return accessibleToEmp;
	}
	public void setAccessibleToEmp(boolean accessibleToEmp) {
		this.accessibleToEmp = accessibleToEmp;
	}
	public boolean isWorkSpecPermission() {
		return workSpecPermission;
	}
	public void setWorkSpecPermission(boolean workSpecPermission) {
		this.workSpecPermission = workSpecPermission;
	}
	
	public boolean isEnableAssigmentService() {
		return enableAssigmentService;
	}
	public void setEnableAssigmentService(boolean enableAssigmentService) {
		this.enableAssigmentService = enableAssigmentService;
	}
	public boolean isResendUnAssignedWorks() {
		return resendUnAssignedWorks;
	}
	public void setResendUnAssignedWorks(boolean resendUnAssignedWorks) {
		this.resendUnAssignedWorks = resendUnAssignedWorks;
	}
	public Integer getLimitForWorkAssignMentService() {
		return limitForWorkAssignMentService;
	}
	public void setLimitForWorkAssignMentService(Integer limitForWorkAssignMentService) {
		this.limitForWorkAssignMentService = limitForWorkAssignMentService;
	}
	public Integer getDurationForUnAssigment() {
		return durationForUnAssigment;
	}
	public void setDurationForUnAssigment(Integer durationForUnAssigment) {
		this.durationForUnAssigment = durationForUnAssigment;
	}
	public Integer getMaxWorksForAcceptence() {
		return maxWorksForAcceptence;
	}
	public void setMaxWorksForAcceptence(Integer maxWorksForAcceptence) {
		this.maxWorksForAcceptence = maxWorksForAcceptence;
	}
	public boolean isDownloadWorkMedias() {
		return downloadWorkMedias;
	}
	public void setDownloadWorkMedias(boolean downloadWorkMedias) {
		this.downloadWorkMedias = downloadWorkMedias;
	}
	public Long getFormId() {
		return formId;
	}
	public void setFormId(Long formId) {
		this.formId = formId;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	

}
