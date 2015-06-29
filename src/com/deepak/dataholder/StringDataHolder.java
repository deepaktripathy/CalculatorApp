package com.deepak.dataholder;

/**
 * 
 * @author deepak tripathy
 *
 */
public class StringDataHolder implements IDataHolder{
	protected Object myData;
	protected String myType = IDataHolder.K_DATA_TYPE_STRING;//keep the widest type as default.
	
	public StringDataHolder(String data) {
		myData = data;
	}
	
	@Override
	public String getType() {
		return myType;
	}

	@Override
	public Object getData() throws Exception {
		return myData;
	}

	/* Helper methods: converts the data to an expected type, may throw "Invalid Type" exception, add more as needed*/
	@Override
	public String getDataAsString() throws Exception {
		return myData.toString();
	}

	@Override
	public Integer getDataAsInteger() throws Exception {
		throw new Exception(K_OPERATION_NOT_SUPPORTED);
	}

	@Override
	public Double getDataAsDouble() throws Exception {
		throw new Exception(K_OPERATION_NOT_SUPPORTED);
	}
	
	@Override
	public Float getDataAsFloat() throws Exception {
		throw new Exception(K_OPERATION_NOT_SUPPORTED);
	}
}
