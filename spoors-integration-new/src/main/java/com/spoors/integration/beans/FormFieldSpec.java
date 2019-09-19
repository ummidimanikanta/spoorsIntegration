package com.spoors.integration.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
/**
 * 
 */

@JsonInclude(JsonInclude.Include.NON_NULL)

public class FormFieldSpec {
	public final static int COMPUTED_FIELD_TYPE_COMPUTED = 1;
	public final static int COMPUTED_FIELD_TYPE_DEFAULT = 2;
	public final static int COMPUTED_FIELD_TYPE_NONE = 0;
	public final static int COMPUTED_FIELD_TYPE_FUNCTION = 3;

	private long fieldSpecId;
	private String uniqueId;
	private long formSpecId;
	private String fieldLabel;
	private int fieldType;
	private String fieldTypeExtra;
	private boolean computedField;
	private boolean defaultField;
	private boolean barcodeField;
	private String formula;
	private boolean isRequired;
	private boolean isVisible= true;
	private int displayOrder;
	private String expression;
	private boolean identifier;
	private Integer pageId;
	private int computedFieldType;
	private boolean mandatory;
	private int type;
	private String value;
	private String validEncodedValue;
	private boolean visibleForCutomerForm;

	private int index;
	private String fieldLabelError;
	private String fieldTypeError;
	private String computedFieldError;
	private String barcodeFieldError;
	private String isRequiredError;
	private String isVisibleError;
	private String functionFieldError;
	// extra properties
	@JsonProperty(access = Access.WRITE_ONLY)
	private String fieldVisbleRestrictedEmpGrps;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String fieldEditableRestrictedEmpGrps;
	private String min;
	private String max;
	private String minErrorValue;
	private String maxErrorValue;
	private int visible = 1;
	private int editable = 1;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String listFilteringCritiria;// this json for ListFilteringCritiria
	@JsonProperty(access = Access.WRITE_ONLY)
	private String visibilityDependencyCriteria;// this is json for
												// ListFilteringCritiria
	@JsonProperty(access = Access.WRITE_ONLY)
	private int visbleOnVisibilityCondition = 1;
	private int formFieldIdentifier = 0;
	
	private Long initialFormFieldSpecId;
	private Long skeletonFormFieldSpecId;
	private boolean isUnique;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long actionSpecId;
	
	private int isRemoteField = 0;
	private String isRemoteFieldError;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String customerFilteringCritiria;// this json for CustomerFilteringCritiria
	
	private String backgroundColorDependencyCritiria;
	
	private String fieldTypeExtraForm;

	private String externalLabel;

	private boolean isWorkInviationField;
	
	private Integer mediaPickCondition;
	
	private boolean conditionalMandatory = false;
	
	private Integer locationPickCondition = 0;
	
	private Integer visibilityOnCheckin = 0;
	
	private Integer workFieldVisibility = 0;
	
	private Integer radioButtonCondition = 0;
	private String radioButtonDefaultValue;
	
	private String formSpecUniqueId;
	private String validationExpr;
	private String validationErrorMsg;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String fieldValidationCritiria;// this json for FieldValidationCritiria
	private Boolean imageSizeEnabled = false;//we made this Boolean(wrapper) to handle null also
	private Integer imageSize;
	private Integer customerFieldVisibility = 0;

	private Integer prefix;
	private Integer seperator;
	private Integer include;
	private Integer sequenceLength;
	
	private boolean visibleForCreation = true;
	private Long formFieldGroupSpecId;
	private Integer isGroupFieldSpec=0;
	private String backgroundColor;
	
	private boolean searchableField = true;
	
	private Integer empGroupVisibleType = 1; //default invisible
	private Integer empGroupEditableType = 2; //default un-editable
	private Integer groupIndex;
	private String groupExpression;
	private Integer enableSpinnerCondition;
	
	private Integer decimalValueLimit;
	
	private String empRoleFilterValues;
	
	private String modifiedTime;
	
	private String aliasExpression;
	
	private Integer textFieldMultiLineValue;
	
	private String formFilteringCritiria;//
	
	private boolean uniqueCheck;
	private Integer otpExpiryTimeInSeconds;
	private Integer noOfOtpDigits;
	
	private Integer reminderConfigEnabled;
	private String remainderRemarksFields;
	
	private Integer readOnlyCondition = 0;
	private Integer updateFormAsProcessed =0;
	private Integer restrictToCaps = 0;
	private Integer pickDiffCustomer = 0;
	
	private Integer guidField = 0;
	
	private String fieldTypeExtraCustomEntity;
	
	private String pickEmployeesFromGroupIds;
	
	private boolean simpleSearch;
	
	private boolean canViewSubTaskProcess;
	private boolean canAddSubTaskProcess;
	private boolean performMandateSubTask;
	
	private boolean dependentComputedField;
	private boolean dependentDefaultField;
	
	private String employeeFilteringCritiria;// this json for EmployeeFilteringCritiria
	
	private Long fieldLabelFontId;
	private Long fieldValueFontId;
	private boolean created = false;
	private String displayLable;
	
    private String entityFilterFunctionFieldsMapping;// this json for EmployeeFilteringCritiria
	
	private boolean functionField;
	private String functionName;
	
	private String customisedPrefix;
	private Integer readDataFrom;
	private boolean restrictDataFromMobile;
	private boolean groupIdentifier;
	public long getFieldSpecId() {
		return fieldSpecId;
	}
	public void setFieldSpecId(long fieldSpecId) {
		this.fieldSpecId = fieldSpecId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public long getFormSpecId() {
		return formSpecId;
	}
	public void setFormSpecId(long formSpecId) {
		this.formSpecId = formSpecId;
	}
	public String getFieldLabel() {
		return fieldLabel;
	}
	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
	public int getFieldType() {
		return fieldType;
	}
	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldTypeExtra() {
		return fieldTypeExtra;
	}
	public void setFieldTypeExtra(String fieldTypeExtra) {
		this.fieldTypeExtra = fieldTypeExtra;
	}
	public boolean isComputedField() {
		return computedField;
	}
	public void setComputedField(boolean computedField) {
		this.computedField = computedField;
	}
	public boolean isDefaultField() {
		return defaultField;
	}
	public void setDefaultField(boolean defaultField) {
		this.defaultField = defaultField;
	}
	public boolean isBarcodeField() {
		return barcodeField;
	}
	public void setBarcodeField(boolean barcodeField) {
		this.barcodeField = barcodeField;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public boolean isRequired() {
		return isRequired;
	}
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public boolean isIdentifier() {
		return identifier;
	}
	public void setIdentifier(boolean identifier) {
		this.identifier = identifier;
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	public int getComputedFieldType() {
		return computedFieldType;
	}
	public void setComputedFieldType(int computedFieldType) {
		this.computedFieldType = computedFieldType;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValidEncodedValue() {
		return validEncodedValue;
	}
	public void setValidEncodedValue(String validEncodedValue) {
		this.validEncodedValue = validEncodedValue;
	}
	public boolean isVisibleForCutomerForm() {
		return visibleForCutomerForm;
	}
	public void setVisibleForCutomerForm(boolean visibleForCutomerForm) {
		this.visibleForCutomerForm = visibleForCutomerForm;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getFieldLabelError() {
		return fieldLabelError;
	}
	public void setFieldLabelError(String fieldLabelError) {
		this.fieldLabelError = fieldLabelError;
	}
	public String getFieldTypeError() {
		return fieldTypeError;
	}
	public void setFieldTypeError(String fieldTypeError) {
		this.fieldTypeError = fieldTypeError;
	}
	public String getComputedFieldError() {
		return computedFieldError;
	}
	public void setComputedFieldError(String computedFieldError) {
		this.computedFieldError = computedFieldError;
	}
	public String getBarcodeFieldError() {
		return barcodeFieldError;
	}
	public void setBarcodeFieldError(String barcodeFieldError) {
		this.barcodeFieldError = barcodeFieldError;
	}
	public String getIsRequiredError() {
		return isRequiredError;
	}
	public void setIsRequiredError(String isRequiredError) {
		this.isRequiredError = isRequiredError;
	}
	public String getIsVisibleError() {
		return isVisibleError;
	}
	public void setIsVisibleError(String isVisibleError) {
		this.isVisibleError = isVisibleError;
	}
	public String getFunctionFieldError() {
		return functionFieldError;
	}
	public void setFunctionFieldError(String functionFieldError) {
		this.functionFieldError = functionFieldError;
	}
	public String getFieldVisbleRestrictedEmpGrps() {
		return fieldVisbleRestrictedEmpGrps;
	}
	public void setFieldVisbleRestrictedEmpGrps(String fieldVisbleRestrictedEmpGrps) {
		this.fieldVisbleRestrictedEmpGrps = fieldVisbleRestrictedEmpGrps;
	}
	public String getFieldEditableRestrictedEmpGrps() {
		return fieldEditableRestrictedEmpGrps;
	}
	public void setFieldEditableRestrictedEmpGrps(String fieldEditableRestrictedEmpGrps) {
		this.fieldEditableRestrictedEmpGrps = fieldEditableRestrictedEmpGrps;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getMinErrorValue() {
		return minErrorValue;
	}
	public void setMinErrorValue(String minErrorValue) {
		this.minErrorValue = minErrorValue;
	}
	public String getMaxErrorValue() {
		return maxErrorValue;
	}
	public void setMaxErrorValue(String maxErrorValue) {
		this.maxErrorValue = maxErrorValue;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public int getEditable() {
		return editable;
	}
	public void setEditable(int editable) {
		this.editable = editable;
	}
	public String getListFilteringCritiria() {
		return listFilteringCritiria;
	}
	public void setListFilteringCritiria(String listFilteringCritiria) {
		this.listFilteringCritiria = listFilteringCritiria;
	}
	public String getVisibilityDependencyCriteria() {
		return visibilityDependencyCriteria;
	}
	public void setVisibilityDependencyCriteria(String visibilityDependencyCriteria) {
		this.visibilityDependencyCriteria = visibilityDependencyCriteria;
	}
	public int getVisbleOnVisibilityCondition() {
		return visbleOnVisibilityCondition;
	}
	public void setVisbleOnVisibilityCondition(int visbleOnVisibilityCondition) {
		this.visbleOnVisibilityCondition = visbleOnVisibilityCondition;
	}
	public int getFormFieldIdentifier() {
		return formFieldIdentifier;
	}
	public void setFormFieldIdentifier(int formFieldIdentifier) {
		this.formFieldIdentifier = formFieldIdentifier;
	}
	public Long getInitialFormFieldSpecId() {
		return initialFormFieldSpecId;
	}
	public void setInitialFormFieldSpecId(Long initialFormFieldSpecId) {
		this.initialFormFieldSpecId = initialFormFieldSpecId;
	}
	public Long getSkeletonFormFieldSpecId() {
		return skeletonFormFieldSpecId;
	}
	public void setSkeletonFormFieldSpecId(Long skeletonFormFieldSpecId) {
		this.skeletonFormFieldSpecId = skeletonFormFieldSpecId;
	}
	public boolean isUnique() {
		return isUnique;
	}
	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}
	public Long getActionSpecId() {
		return actionSpecId;
	}
	public void setActionSpecId(Long actionSpecId) {
		this.actionSpecId = actionSpecId;
	}
	public int getIsRemoteField() {
		return isRemoteField;
	}
	public void setIsRemoteField(int isRemoteField) {
		this.isRemoteField = isRemoteField;
	}
	public String getIsRemoteFieldError() {
		return isRemoteFieldError;
	}
	public void setIsRemoteFieldError(String isRemoteFieldError) {
		this.isRemoteFieldError = isRemoteFieldError;
	}
	public String getCustomerFilteringCritiria() {
		return customerFilteringCritiria;
	}
	public void setCustomerFilteringCritiria(String customerFilteringCritiria) {
		this.customerFilteringCritiria = customerFilteringCritiria;
	}
	public String getBackgroundColorDependencyCritiria() {
		return backgroundColorDependencyCritiria;
	}
	public void setBackgroundColorDependencyCritiria(String backgroundColorDependencyCritiria) {
		this.backgroundColorDependencyCritiria = backgroundColorDependencyCritiria;
	}
	public String getFieldTypeExtraForm() {
		return fieldTypeExtraForm;
	}
	public void setFieldTypeExtraForm(String fieldTypeExtraForm) {
		this.fieldTypeExtraForm = fieldTypeExtraForm;
	}
	public String getExternalLabel() {
		return externalLabel;
	}
	public void setExternalLabel(String externalLabel) {
		this.externalLabel = externalLabel;
	}
	public boolean isWorkInviationField() {
		return isWorkInviationField;
	}
	public void setWorkInviationField(boolean isWorkInviationField) {
		this.isWorkInviationField = isWorkInviationField;
	}
	public Integer getMediaPickCondition() {
		return mediaPickCondition;
	}
	public void setMediaPickCondition(Integer mediaPickCondition) {
		this.mediaPickCondition = mediaPickCondition;
	}
	public boolean isConditionalMandatory() {
		return conditionalMandatory;
	}
	public void setConditionalMandatory(boolean conditionalMandatory) {
		this.conditionalMandatory = conditionalMandatory;
	}
	public Integer getLocationPickCondition() {
		return locationPickCondition;
	}
	public void setLocationPickCondition(Integer locationPickCondition) {
		this.locationPickCondition = locationPickCondition;
	}
	public Integer getVisibilityOnCheckin() {
		return visibilityOnCheckin;
	}
	public void setVisibilityOnCheckin(Integer visibilityOnCheckin) {
		this.visibilityOnCheckin = visibilityOnCheckin;
	}
	public Integer getWorkFieldVisibility() {
		return workFieldVisibility;
	}
	public void setWorkFieldVisibility(Integer workFieldVisibility) {
		this.workFieldVisibility = workFieldVisibility;
	}
	public Integer getRadioButtonCondition() {
		return radioButtonCondition;
	}
	public void setRadioButtonCondition(Integer radioButtonCondition) {
		this.radioButtonCondition = radioButtonCondition;
	}
	public String getRadioButtonDefaultValue() {
		return radioButtonDefaultValue;
	}
	public void setRadioButtonDefaultValue(String radioButtonDefaultValue) {
		this.radioButtonDefaultValue = radioButtonDefaultValue;
	}
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public String getValidationExpr() {
		return validationExpr;
	}
	public void setValidationExpr(String validationExpr) {
		this.validationExpr = validationExpr;
	}
	public String getValidationErrorMsg() {
		return validationErrorMsg;
	}
	public void setValidationErrorMsg(String validationErrorMsg) {
		this.validationErrorMsg = validationErrorMsg;
	}
	public String getFieldValidationCritiria() {
		return fieldValidationCritiria;
	}
	public void setFieldValidationCritiria(String fieldValidationCritiria) {
		this.fieldValidationCritiria = fieldValidationCritiria;
	}
	public Boolean getImageSizeEnabled() {
		return imageSizeEnabled;
	}
	public void setImageSizeEnabled(Boolean imageSizeEnabled) {
		this.imageSizeEnabled = imageSizeEnabled;
	}
	public Integer getImageSize() {
		return imageSize;
	}
	public void setImageSize(Integer imageSize) {
		this.imageSize = imageSize;
	}
	public Integer getCustomerFieldVisibility() {
		return customerFieldVisibility;
	}
	public void setCustomerFieldVisibility(Integer customerFieldVisibility) {
		this.customerFieldVisibility = customerFieldVisibility;
	}
	public Integer getPrefix() {
		return prefix;
	}
	public void setPrefix(Integer prefix) {
		this.prefix = prefix;
	}
	public Integer getSeperator() {
		return seperator;
	}
	public void setSeperator(Integer seperator) {
		this.seperator = seperator;
	}
	public Integer getInclude() {
		return include;
	}
	public void setInclude(Integer include) {
		this.include = include;
	}
	public Integer getSequenceLength() {
		return sequenceLength;
	}
	public void setSequenceLength(Integer sequenceLength) {
		this.sequenceLength = sequenceLength;
	}
	public boolean isVisibleForCreation() {
		return visibleForCreation;
	}
	public void setVisibleForCreation(boolean visibleForCreation) {
		this.visibleForCreation = visibleForCreation;
	}
	public Long getFormFieldGroupSpecId() {
		return formFieldGroupSpecId;
	}
	public void setFormFieldGroupSpecId(Long formFieldGroupSpecId) {
		this.formFieldGroupSpecId = formFieldGroupSpecId;
	}
	public Integer getIsGroupFieldSpec() {
		return isGroupFieldSpec;
	}
	public void setIsGroupFieldSpec(Integer isGroupFieldSpec) {
		this.isGroupFieldSpec = isGroupFieldSpec;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public boolean isSearchableField() {
		return searchableField;
	}
	public void setSearchableField(boolean searchableField) {
		this.searchableField = searchableField;
	}
	public Integer getEmpGroupVisibleType() {
		return empGroupVisibleType;
	}
	public void setEmpGroupVisibleType(Integer empGroupVisibleType) {
		this.empGroupVisibleType = empGroupVisibleType;
	}
	public Integer getEmpGroupEditableType() {
		return empGroupEditableType;
	}
	public void setEmpGroupEditableType(Integer empGroupEditableType) {
		this.empGroupEditableType = empGroupEditableType;
	}
	public Integer getGroupIndex() {
		return groupIndex;
	}
	public void setGroupIndex(Integer groupIndex) {
		this.groupIndex = groupIndex;
	}
	public String getGroupExpression() {
		return groupExpression;
	}
	public void setGroupExpression(String groupExpression) {
		this.groupExpression = groupExpression;
	}
	public Integer getEnableSpinnerCondition() {
		return enableSpinnerCondition;
	}
	public void setEnableSpinnerCondition(Integer enableSpinnerCondition) {
		this.enableSpinnerCondition = enableSpinnerCondition;
	}
	public Integer getDecimalValueLimit() {
		return decimalValueLimit;
	}
	public void setDecimalValueLimit(Integer decimalValueLimit) {
		this.decimalValueLimit = decimalValueLimit;
	}
	public String getEmpRoleFilterValues() {
		return empRoleFilterValues;
	}
	public void setEmpRoleFilterValues(String empRoleFilterValues) {
		this.empRoleFilterValues = empRoleFilterValues;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getAliasExpression() {
		return aliasExpression;
	}
	public void setAliasExpression(String aliasExpression) {
		this.aliasExpression = aliasExpression;
	}
	public Integer getTextFieldMultiLineValue() {
		return textFieldMultiLineValue;
	}
	public void setTextFieldMultiLineValue(Integer textFieldMultiLineValue) {
		this.textFieldMultiLineValue = textFieldMultiLineValue;
	}
	public String getFormFilteringCritiria() {
		return formFilteringCritiria;
	}
	public void setFormFilteringCritiria(String formFilteringCritiria) {
		this.formFilteringCritiria = formFilteringCritiria;
	}
	public boolean isUniqueCheck() {
		return uniqueCheck;
	}
	public void setUniqueCheck(boolean uniqueCheck) {
		this.uniqueCheck = uniqueCheck;
	}
	public Integer getOtpExpiryTimeInSeconds() {
		return otpExpiryTimeInSeconds;
	}
	public void setOtpExpiryTimeInSeconds(Integer otpExpiryTimeInSeconds) {
		this.otpExpiryTimeInSeconds = otpExpiryTimeInSeconds;
	}
	public Integer getNoOfOtpDigits() {
		return noOfOtpDigits;
	}
	public void setNoOfOtpDigits(Integer noOfOtpDigits) {
		this.noOfOtpDigits = noOfOtpDigits;
	}
	public Integer getReminderConfigEnabled() {
		return reminderConfigEnabled;
	}
	public void setReminderConfigEnabled(Integer reminderConfigEnabled) {
		this.reminderConfigEnabled = reminderConfigEnabled;
	}
	public String getRemainderRemarksFields() {
		return remainderRemarksFields;
	}
	public void setRemainderRemarksFields(String remainderRemarksFields) {
		this.remainderRemarksFields = remainderRemarksFields;
	}
	public Integer getReadOnlyCondition() {
		return readOnlyCondition;
	}
	public void setReadOnlyCondition(Integer readOnlyCondition) {
		this.readOnlyCondition = readOnlyCondition;
	}
	public Integer getUpdateFormAsProcessed() {
		return updateFormAsProcessed;
	}
	public void setUpdateFormAsProcessed(Integer updateFormAsProcessed) {
		this.updateFormAsProcessed = updateFormAsProcessed;
	}
	public Integer getRestrictToCaps() {
		return restrictToCaps;
	}
	public void setRestrictToCaps(Integer restrictToCaps) {
		this.restrictToCaps = restrictToCaps;
	}
	public Integer getPickDiffCustomer() {
		return pickDiffCustomer;
	}
	public void setPickDiffCustomer(Integer pickDiffCustomer) {
		this.pickDiffCustomer = pickDiffCustomer;
	}
	public Integer getGuidField() {
		return guidField;
	}
	public void setGuidField(Integer guidField) {
		this.guidField = guidField;
	}
	public String getFieldTypeExtraCustomEntity() {
		return fieldTypeExtraCustomEntity;
	}
	public void setFieldTypeExtraCustomEntity(String fieldTypeExtraCustomEntity) {
		this.fieldTypeExtraCustomEntity = fieldTypeExtraCustomEntity;
	}
	public String getPickEmployeesFromGroupIds() {
		return pickEmployeesFromGroupIds;
	}
	public void setPickEmployeesFromGroupIds(String pickEmployeesFromGroupIds) {
		this.pickEmployeesFromGroupIds = pickEmployeesFromGroupIds;
	}
	public boolean isSimpleSearch() {
		return simpleSearch;
	}
	public void setSimpleSearch(boolean simpleSearch) {
		this.simpleSearch = simpleSearch;
	}
	public boolean isCanViewSubTaskProcess() {
		return canViewSubTaskProcess;
	}
	public void setCanViewSubTaskProcess(boolean canViewSubTaskProcess) {
		this.canViewSubTaskProcess = canViewSubTaskProcess;
	}
	public boolean isCanAddSubTaskProcess() {
		return canAddSubTaskProcess;
	}
	public void setCanAddSubTaskProcess(boolean canAddSubTaskProcess) {
		this.canAddSubTaskProcess = canAddSubTaskProcess;
	}
	public boolean isPerformMandateSubTask() {
		return performMandateSubTask;
	}
	public void setPerformMandateSubTask(boolean performMandateSubTask) {
		this.performMandateSubTask = performMandateSubTask;
	}
	public boolean isDependentComputedField() {
		return dependentComputedField;
	}
	public void setDependentComputedField(boolean dependentComputedField) {
		this.dependentComputedField = dependentComputedField;
	}
	public boolean isDependentDefaultField() {
		return dependentDefaultField;
	}
	public void setDependentDefaultField(boolean dependentDefaultField) {
		this.dependentDefaultField = dependentDefaultField;
	}
	public String getEmployeeFilteringCritiria() {
		return employeeFilteringCritiria;
	}
	public void setEmployeeFilteringCritiria(String employeeFilteringCritiria) {
		this.employeeFilteringCritiria = employeeFilteringCritiria;
	}
	public Long getFieldLabelFontId() {
		return fieldLabelFontId;
	}
	public void setFieldLabelFontId(Long fieldLabelFontId) {
		this.fieldLabelFontId = fieldLabelFontId;
	}
	public Long getFieldValueFontId() {
		return fieldValueFontId;
	}
	public void setFieldValueFontId(Long fieldValueFontId) {
		this.fieldValueFontId = fieldValueFontId;
	}
	public boolean isCreated() {
		return created;
	}
	public void setCreated(boolean created) {
		this.created = created;
	}
	public String getDisplayLable() {
		return displayLable;
	}
	public void setDisplayLable(String displayLable) {
		this.displayLable = displayLable;
	}
	public String getEntityFilterFunctionFieldsMapping() {
		return entityFilterFunctionFieldsMapping;
	}
	public void setEntityFilterFunctionFieldsMapping(String entityFilterFunctionFieldsMapping) {
		this.entityFilterFunctionFieldsMapping = entityFilterFunctionFieldsMapping;
	}
	public boolean isFunctionField() {
		return functionField;
	}
	public void setFunctionField(boolean functionField) {
		this.functionField = functionField;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getCustomisedPrefix() {
		return customisedPrefix;
	}
	public void setCustomisedPrefix(String customisedPrefix) {
		this.customisedPrefix = customisedPrefix;
	}
	public Integer getReadDataFrom() {
		return readDataFrom;
	}
	public void setReadDataFrom(Integer readDataFrom) {
		this.readDataFrom = readDataFrom;
	}
	public boolean isRestrictDataFromMobile() {
		return restrictDataFromMobile;
	}
	public void setRestrictDataFromMobile(boolean restrictDataFromMobile) {
		this.restrictDataFromMobile = restrictDataFromMobile;
	}
	public boolean isGroupIdentifier() {
		return groupIdentifier;
	}
	public void setGroupIdentifier(boolean groupIdentifier) {
		this.groupIdentifier = groupIdentifier;
	}
	
}
