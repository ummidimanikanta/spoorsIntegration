package com.spoors.integration.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormSpec {
	private long formSpecId;
	private String uniqueId;
	private int companyId;
	private String formTitle;
	private long createdBy;
	@JsonProperty(access = Access.READ_ONLY)
	private boolean allAccess = true;
	@JsonProperty(access = Access.READ_ONLY)
	private boolean isPublic;
	private Long parentId;
	private boolean openToCustomer;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String printTemplate;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String printTemplatePdfSaveNameFieldUniqueId;
	private String emailTemplate;
	private String mobilePrintTemplate;
	private int version;
	private boolean deleted;
	private String empId;
	
	private int type;
	@JsonProperty(access = Access.READ_ONLY)
	private boolean customerPresent;
	@JsonProperty(access = Access.READ_ONLY)
	private String workflowId;
	private int isSystemDefined;
	private int purpose=-1;
	private Long initialFormSpecId;
	private Long skeletonFormSpecId;
	private boolean mapeed;
	private boolean select;
	
	private String modifiedTime;
	
	private String createdTime;
	
	public final static int PURPOUSE_JOB=1;
//	public final static int PURPOUSE_CUSTOMER=2;
	public final static int PURPOUSE_CUSTOMER=4;
	public final static int PURPOUSE_WORK=3;
	public static final int PURPOUSE_CUSTOMER_CREATION = 5;
	
	public static final int PURPOUSE_REJECTION_FORM = 6;
	
	public static final int PURPOUSE_CUSTOM_ENTITY = 7;
	
	public static final int PURPOUSE_EMPLOYEE_FORM = 8;
	
	public static final int PURPOUSE_SIGN_IN_SIGN_OUT_UPDATE_FORM = 10;
	
	
	private Boolean isOnlineForm=false;
	
	private Boolean hasDataSource=false;
	
	private boolean importFlag = true;
	
	private boolean importForm = true;
	
	private boolean addForm = true;
	private boolean empGroupMapped;
	
//	private boolean addOnlineFormInOffline =false;

	//Form details (ibibo report)
	private long formsCount;
	private String formIdsWithIdentifier;
	
	private int stockForm = 0;
	
	private int userEditRestriction = 0;
	private String userEditRestrictionReason;
	
	private Boolean isPublicLinkForm=false;
	
	private boolean customEntityPresent;
	
	private boolean avoidFormWebCreate;
	private String avoidFormWebCreateMessage;
	
	private int stockUpdateType = -1;
	private boolean updateOnFormApproval;
	
	private String publicLinkUniqueId;
	
	
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
	public Long getInitialFormSpecId() {
		return initialFormSpecId;
	}
	public void setInitialFormSpecId(Long initialFormSpecId) {
		this.initialFormSpecId = initialFormSpecId;
	}
	public Long getSkeletonFormSpecId() {
		return skeletonFormSpecId;
	}
	public void setSkeletonFormSpecId(Long skeletonFormSpecId) {
		this.skeletonFormSpecId = skeletonFormSpecId;
	}
	public long getFormSpecId() {
		return formSpecId;
	}
	public void setFormSpecId(long formSpecId) {
		this.formSpecId = formSpecId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public boolean isOpenToCustomer() {
		return openToCustomer;
	}
	public void setOpenToCustomer(boolean openToCustomer) {
		this.openToCustomer = openToCustomer;
	}
	public String getMobilePrintTemplate() {
		return mobilePrintTemplate;
	}
	public void setMobilePrintTemplate(String mobilePrintTemplate) {
		this.mobilePrintTemplate = mobilePrintTemplate;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public String getWorkflowId() {
		return workflowId;
	}
	
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	
	public String getPrintTemplate() {
		return printTemplate;
	}
	public void setPrintTemplate(String printTemplate) {
		this.printTemplate = printTemplate;
	}
	@JsonIgnore
	public int getCompanyId() {
		return companyId;
	}
	@JsonIgnore
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public boolean isAllAccess() {
		return allAccess;
	}
	
	public void setAllAccess(boolean allAccess) {
		this.allAccess = allAccess;
	}
	
	public boolean isPublic() {
		return isPublic;
	}
	
	public boolean getIsPublic() {
		return isPublic;
	}
	
	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	@JsonIgnore
	public Long getParentId() {
		return parentId;
	}
	@JsonIgnore
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	@JsonIgnore
	public int getType() {
		return type;
	}
	@JsonIgnore
	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isCustomerPresent() {
		return customerPresent;
	}
	
	public void setCustomerPresent(boolean customerPresent) {
		this.customerPresent = customerPresent;
	}
	
	public String getPrintTemplatePdfSaveNameFieldUniqueId() {
		return printTemplatePdfSaveNameFieldUniqueId;
	}
	public void setPrintTemplatePdfSaveNameFieldUniqueId(
			String printTemplatePdfSaveNameFieldUniqueId) {
		this.printTemplatePdfSaveNameFieldUniqueId = printTemplatePdfSaveNameFieldUniqueId;
	}
	public String getEmailTemplate() {
		return emailTemplate;
	}
	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}
	@JsonIgnore
	public boolean isMapeed() {
		return mapeed;
	}
	@JsonIgnore
	public void setMapeed(boolean mapeed) {
		this.mapeed = mapeed;
	}
	public String toCSV() {
		return "[formSpecId=" + formSpecId + ", uniqueId=" + uniqueId + ", companyId=" + companyId + ", formTitle=" + formTitle + ", createdBy=" + createdBy + ", allAccess=" + allAccess + ", isPublic=" + isPublic + ", parentId=" + parentId + ", openToCustomer=" + openToCustomer
				+ ", version=" + version + ", deleted=" + deleted + ", type=" + type + ", customerPresent=" + customerPresent + ", isOnlineForm=" + isOnlineForm + "]";
	}
	
	public Boolean getIsOnlineForm() {
		return isOnlineForm;
	}
	public void setIsOnlineForm(Boolean isOnlineForm) {
		this.isOnlineForm = isOnlineForm;
	}
	public Boolean getHasDataSource() {
		return hasDataSource;
	}
	public void setHasDataSource(Boolean hasDataSource) {
		this.hasDataSource = hasDataSource;
	}
	public boolean isImportFlag() {
		return importFlag;
	}
	public void setImportFlag(boolean importFlag) {
		this.importFlag = importFlag;
	}
	public long getFormsCount() {
		return formsCount;
	}
	public void setFormsCount(long formsCount) {
		this.formsCount = formsCount;
	}
	public String getFormIdsWithIdentifier() {
		return formIdsWithIdentifier;
	}
	public void setFormIdsWithIdentifier(String formIdsWithIdentifier) {
		this.formIdsWithIdentifier = formIdsWithIdentifier;
	}
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public boolean isImportForm() {
		return importForm;
	}
	public void setImportForm(boolean importForm) {
		this.importForm = importForm;
	}
	@Override
	public String toString() {
		return "FormSpec [formSpecId=" + formSpecId + ", uniqueId=" + uniqueId
				+ ", companyId=" + companyId + ", formTitle=" + formTitle
				+ ", createdBy=" + createdBy + ", allAccess=" + allAccess
				+ ", isPublic=" + isPublic + ", parentId=" + parentId
				+ ", openToCustomer=" + openToCustomer + ", printTemplate="
				+ printTemplate + ", printTemplatePdfSaveNameFieldUniqueId="
				+ printTemplatePdfSaveNameFieldUniqueId + ", emailTemplate="
				+ emailTemplate + ", mobilePrintTemplate="
				+ mobilePrintTemplate + ", version=" + version + ", deleted="
				+ deleted + ", empId=" + empId + ", type=" + type
				+ ", customerPresent=" + customerPresent + ", workflowId="
				+ workflowId + ", isSystemDefined=" + isSystemDefined
				+ ", purpose=" + purpose + ", initialFormSpecId="
				+ initialFormSpecId + ", skeletonFormSpecId="
				+ skeletonFormSpecId + ", mapeed=" + mapeed + ", isOnlineForm="
				+ isOnlineForm + ", hasDataSource=" + hasDataSource
				+ ", importFlag=" + importFlag + ", importForm=" + importForm
				+ ", formsCount=" + formsCount + ", formIdsWithIdentifier="
				+ formIdsWithIdentifier +"]";
	}
	public int getStockForm() {
		return stockForm;
	}
	public void setStockForm(int stockForm) {
		this.stockForm = stockForm;
	}
	public int getUserEditRestriction() {
		return userEditRestriction;
	}
	public void setUserEditRestriction(int userEditRestriction) {
		this.userEditRestriction = userEditRestriction;
	}
	public String getUserEditRestrictionReason() {
		return userEditRestrictionReason;
	}
	public void setUserEditRestrictionReason(String userEditRestrictionReason) {
		this.userEditRestrictionReason = userEditRestrictionReason;
	}
	public boolean isAddForm() {
		return addForm;
	}
	public void setAddForm(boolean addForm) {
		this.addForm = addForm;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FormSpec){
			FormSpec formSpec = (FormSpec) obj;
			return formSpec.getUniqueId().equals(this.uniqueId);
		} else {
			return super.equals(obj);
		}
	}
	
	public Boolean getIsPublicLinkForm() {
		return isPublicLinkForm;
	}
	public void setIsPublicLinkForm(Boolean isPublicLinkForm) {
		this.isPublicLinkForm = isPublicLinkForm;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/*public boolean isAddOnlineFormInOffline() {
		return addOnlineFormInOffline;
	}
	public void setAddOnlineFormInOffline(boolean addOnlineFormInOffline) {
		this.addOnlineFormInOffline = addOnlineFormInOffline;
	}*/
	public boolean isEmpGroupMapped() {
		return empGroupMapped;
	}
	public void setEmpGroupMapped(boolean empGroupMapped) {
		this.empGroupMapped = empGroupMapped;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public boolean isCustomEntityPresent() {
		return customEntityPresent;
	}
	public void setCustomEntityPresent(boolean customEntityPresent) {
		this.customEntityPresent = customEntityPresent;
	}
	@JsonIgnore
	public String getAvoidFormWebCreateMessage() {
		return avoidFormWebCreateMessage;
	}
	@JsonIgnore
	public void setAvoidFormWebCreateMessage(String avoidFormWebCreateMessage) {
		this.avoidFormWebCreateMessage = avoidFormWebCreateMessage;
	}
	@JsonIgnore
	public boolean isAvoidFormWebCreate() {
		return avoidFormWebCreate;
	}
	@JsonIgnore
	public void setAvoidFormWebCreate(boolean avoidFormWebCreate) {
		this.avoidFormWebCreate = avoidFormWebCreate;
	}
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	
	@JsonIgnore
	public int getStockUpdateType() {
		return stockUpdateType;
	}
	@JsonIgnore
	public void setStockUpdateType(int stockUpdateType) {
		this.stockUpdateType = stockUpdateType;
	}
    public boolean isUpdateOnFormApproval() {
        return updateOnFormApproval;
    }
    public void setUpdateOnFormApproval(boolean updateOnFormApproval) {
        this.updateOnFormApproval = updateOnFormApproval;
    }
	public String getPublicLinkUniqueId() {
		return publicLinkUniqueId;
	}
	public void setPublicLinkUniqueId(String publicLinkUniqueId) {
		this.publicLinkUniqueId = publicLinkUniqueId;
	}
}
