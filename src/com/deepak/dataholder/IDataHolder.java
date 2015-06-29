package com.deepak.dataholder;

/**
 * This would hold any type of data and its type. Even though every data has a type, 
 * say String is String type, we can group them as a new type, much useful for 
 * grouping needs.
 * 
 * The contained data should be cast back to the type provided by the getType.
 * 
 * @author deepak tripathy
 *
 */
public interface IDataHolder {
	
	/* All the common data types, better to add here than by the implementers */
	public static final String K_DATA_TYPE_STRING = "string_type";
	public static final String K_DATA_TYPE_INT = "int_type";
	public static final String K_DATA_TYPE_LONG = "long_type";
	public static final String K_DATA_TYPE_FLOAT = "float_type";
	public static final String K_DATA_TYPE_DOUBLE = "double_type";
	
	public static final String K_OPERATION_NOT_SUPPORTED = "Operation is not supported";
	/* Returns the type of this, if not provided, should become same as getClass().toString */
	public String getType();
	
	/* Returns the data contained in this holder.*/
	public Object getData() throws Exception;
	
	/* Helper methods: converts the data to an expected type, may throw "Invalid Type" exception, add more as needed*/
	
	/* returns a double value version */
	public Double getDataAsDouble() throws Exception;

	/* returns a float value */
	public Float  getDataAsFloat() throws Exception;

	/* returns a int value */
	public Integer getDataAsInteger() throws Exception;

	/* return either a toString or implementation specific */
	public String getDataAsString() throws Exception;
			
}
