package com.deepak.dataholder;

/**
 * 
 * @author deepak tripathy
 *
 */
public class NumericDataHolder implements IDataHolder{
	
	protected Object myData;
	protected String myType = IDataHolder.K_DATA_TYPE_DOUBLE;//keep the widest type as default.
	//add other types.
	
	public NumericDataHolder(Double data) {
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

	@Override
	public String getDataAsString() throws Exception {
		return myData.toString();
	}

	@Override
	public Integer getDataAsInteger() throws Exception {
		throw new Exception(K_OPERATION_NOT_SUPPORTED);
	}

	@Override
	public Float getDataAsFloat() throws Exception {
		throw new Exception(K_OPERATION_NOT_SUPPORTED);
	}
	
	@Override
	public Double getDataAsDouble() throws Exception {
		return (Double)myData;
	}

}
