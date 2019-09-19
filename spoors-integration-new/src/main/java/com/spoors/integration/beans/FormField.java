package com.spoors.integration.beans;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ayansys.effort.util.Api;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormField implements Serializable {
	private static final long serialVersionUID = 1L;

	private long fieldId;
	private long formId;
	private long formSpecId;
	private long fieldSpecId;
	private String fieldValue;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String fieldValueSubstitute;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String clientFormId;
	private int canIgnoreUpdate;
	private String fieldDisplayValue;
	private String externalId;
	private String fieldLabel;
	private String uniqueId;
	private int identifier;

	// private String clientFieldId;

	private String error;

	private String displayValue;

	private Integer fieldType;
	private Integer displayOrder;

	private Long initialFormFieldSpecId;
	private Long skeletonFormFieldSpecId;
	private Integer isVisible;
	
	private String fieldName;
	private String groupExpression;
	
	private String backgroundColor;
	
	private String expression;
	private String externalLabel;
	
	// @Transient
	private transient CommonsMultipartFile file = null;

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public long getFieldId() {
		return fieldId;
	}

	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
	}
	
	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
	}

	public long getFormSpecId() {
		return formSpecId;
	}

	public void setFormSpecId(long formSpecId) {
		this.formSpecId = formSpecId;
	}

	public long getFieldSpecId() {
		return fieldSpecId;
	}

	public void setFieldSpecId(long fieldSpecId) {
		this.fieldSpecId = fieldSpecId;
	}

	public String getFieldValue() {

		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getCorrectedFieldValue() {
		if (!Api.isEmptyString(fieldValue)) {
			String result = fieldValue.replace("\n", " \\n").replace("\r",
					" \\n");
			return result;
		}
		return fieldValue;
	}

	
	public String getFieldValueSubstitute() {
		return fieldValueSubstitute;
	}

	public void setFieldValueSubstitute(String fieldValueSubstitute) {
		this.fieldValueSubstitute = fieldValueSubstitute;
	}

	
	public String getClientFormId() {
		return clientFormId;
	}

	public void setClientFormId(String clientFormId) {
		this.clientFormId = clientFormId;
	}

	// public String getClientFieldId() {
	// return clientFieldId;
	// }
	// public void setClientFieldId(String clientFieldId) {
	// this.clientFieldId = clientFieldId;
	// }

	@JsonIgnore
	public String getError() {
		return error;
	}

	public String getFieldDisplayValue() {
		return fieldDisplayValue;
	}

	public void setFieldDisplayValue(String fieldDisplayValue) {
		this.fieldDisplayValue = fieldDisplayValue;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	@JsonIgnore
	public void setError(String error) {
		this.error = error;
	}

	@JsonIgnore
	public CommonsMultipartFile getFile() {
		return file;
	}

	@JsonIgnore
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	/*public String toCSV() {
		return "[formId=" + formId + ", formSpecId=" + formSpecId
				+ ", fieldSpecId=" + fieldSpecId + ", fieldValue=" + fieldValue
				+ ", clientFormId=" + clientFormId + "]";
	}*/
	
	

	public String toCSV() {
		return "FormField [formId=" + formId + ", formSpecId=" + formSpecId
				+ ", fieldSpecId=" + fieldSpecId + ", fieldValue=" + fieldValue
				+ ", fieldValueSubstitute=" + fieldValueSubstitute
				+ ", clientFormId=" + clientFormId + ", canIgnoreUpdate="
				+ canIgnoreUpdate + ", fieldDisplayValue=" + fieldDisplayValue
				+ ", externalId=" + externalId + ", fieldLabel=" + fieldLabel
				+ ", uniqueId=" + uniqueId + ", identifier=" + identifier
				+ ", error=" + error + ", displayValue=" + displayValue
				+ ", fieldType=" + fieldType + ", displayOrder=" + displayOrder
				+ ", initialFormFieldSpecId=" + initialFormFieldSpecId
				+ ", skeletonFormFieldSpecId=" + skeletonFormFieldSpecId
				+ ", isVisible=" + isVisible + "]";
	}

	public int getCanIgnoreUpdate() {
		return canIgnoreUpdate;
	}

	public void setCanIgnoreUpdate(int canIgnoreUpdate) {
		this.canIgnoreUpdate = canIgnoreUpdate;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getFieldType() {
		return fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
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
	
	public Integer getIsVisible() {
		return isVisible;
	}
	
	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}
	
	
	

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FormField) {
			return (getFormId() == ((FormField) obj).getFormId() && getFieldSpecId() == ((FormField) obj)
					.getFieldSpecId());
		} else {
			return super.equals(obj);
		}
	}

	
	public String getGroupExpression() {
		return groupExpression;
	}

	public void setGroupExpression(String groupExpression) {
		this.groupExpression = groupExpression;
	}

	@Override
	public String toString() {
		return "FormField [formId=" + formId + ", formSpecId=" + formSpecId
				+ ", fieldSpecId=" + fieldSpecId + ", fieldValue=" + fieldValue
				+ ", fieldValueSubstitute=" + fieldValueSubstitute
				+ ", clientFormId=" + clientFormId + ", canIgnoreUpdate="
				+ canIgnoreUpdate + ", fieldDisplayValue=" + fieldDisplayValue
				+ ", externalId=" + externalId + ", fieldLabel=" + fieldLabel
				+ ", uniqueId=" + uniqueId + ", identifier=" + identifier
				+ ", error=" + error + ", displayValue=" + displayValue
				+ ", fieldType=" + fieldType + ", displayOrder=" + displayOrder
				+ ", initialFormFieldSpecId=" + initialFormFieldSpecId
				+ ", skeletonFormFieldSpecId=" + skeletonFormFieldSpecId
				+ ", isVisible=" + isVisible + ", fieldName=" + fieldName + "]";
	}
	@JsonIgnore
	public String getBackgroundColor() {
		return backgroundColor;
	}
	@JsonIgnore
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getExternalLabel() {
		return externalLabel;
	}

	public void setExternalLabel(String externalLabel) {
		this.externalLabel = externalLabel;
	}
	
}
