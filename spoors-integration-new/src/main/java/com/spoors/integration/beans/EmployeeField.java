package com.spoors.integration.beans;

import lombok.Data;

@Data
public class EmployeeField {

	private long id;
	private long employeeStaticFieldId;
	private String fieldLabel;
	private int mandatory;
	private int companyId;
	private String objectFieldName;
	private String columnName;
	private int fieldType;
	private long modifiedBy;
	
	public final static int EMPLOYEE_ID = 1;
	public final static int EMPLOYEE_GROUP = 2;
	public final static int EMPLOYEE_TRRITORY = 3;
	public final static int EMPLOYEE_DESIGNATION = 4;
	public final static int EMPLOYEE_ROLE = 5;
	public final static int EMPLOYEE_BRANCH = 6;
	public final static int EMPLOYEE_STREET = 7;
	public final static int EMPLOYEE_AREA = 8;
	public final static int EMPLOYEE_CITY = 9;
	public final static int EMPLOYEE_DISTRICT = 10;
	public final static int EMPLOYEE_STATE = 11;
	public final static int EMPLOYEE_COUNTRY = 12;
	
	public final static int MANDATORY = 1;
	public final static int OPTIONAL = 0;
	
	@Override
	public String toString() {
		return "EmployeeField [id=" + id + ", employeeStaticFieldId=" + employeeStaticFieldId + ", fieldLabel="
				+ fieldLabel + ", mandatory=" + mandatory + ", companyId=" + companyId + ", objectFieldName="
				+ objectFieldName + ", columnName=" + columnName + ", fieldType=" + fieldType + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmployeeStaticFieldId() {
		return employeeStaticFieldId;
	}

	public void setEmployeeStaticFieldId(long employeeStaticFieldId) {
		this.employeeStaticFieldId = employeeStaticFieldId;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public int getMandatory() {
		return mandatory;
	}

	public void setMandatory(int mandatory) {
		this.mandatory = mandatory;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getObjectFieldName() {
		return objectFieldName;
	}

	public void setObjectFieldName(String objectFieldName) {
		this.objectFieldName = objectFieldName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getFieldType() {
		return fieldType;
	}

	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}

	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	
	
	
}
