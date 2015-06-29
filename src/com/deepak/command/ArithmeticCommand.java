package com.deepak.command;

import java.util.logging.Logger;
import com.deepak.dataholder.IDataHolder;

/**
 * Command performs an arithmetic operation on top two stack items.
 * @author deepak tripathy
 */
public class ArithmeticCommand
    implements IDataHolder
{
	private static final Logger LOGGER = Logger.getLogger(ArithmeticCommand.class.getName());
	
    // Possible operations
    public static final int kAddOp  	= 1; //op1+op2
    public static final int kSubtractOp = 2; //op1-op2
    public static final int kMultiplyOp = 3; //op1*op2
    public static final int kDivideOp 	= 4; //op1/op2
    public static final int kModulusOp 	= 5; //op1%op2
    public static final int kPercentOp 	= 6; //op2 percent of op1
    
    /** mathematical Operation */
    private int fOperator;

    /** operand1 */
    private IDataHolder fOperand1;

    /** operand2 */
    private IDataHolder fOperand2;

    /**
     * Create an arithmetic command with the given operation and
     * DataHolders
     * @param operator The operation to perform
     * @param operand1 First operand
     * @param operand2 Second operand
     */
    ArithmeticCommand(int operator, IDataHolder operand1,
                                    IDataHolder operand2)
    {
        fOperator = operator;
        fOperand1 = operand1;
        fOperand2 = operand2;
    }
    
    public String getType() {
    	return K_DATA_TYPE_DOUBLE;
    }

    
	/**
	 * Return the result of the operation.
	 * @param data The data record containing the raw data
	 * @return The result of operation
	 */
    public Object getData() throws Exception {
    	
	    switch (fOperator)
	    {
	        case kAddOp:
	        	LOGGER.info("Calling Operation < ADD >, <" + fOperand1.getDataAsDouble() + ">, & <"+fOperand2.getDataAsDouble()+">");
	            return fOperand1.getDataAsDouble() +
	                   fOperand2.getDataAsDouble();

	        case kSubtractOp:
	        	LOGGER.info("Calling Operation < SUBTRACT >, <" + fOperand1.getDataAsDouble() + ">, & <"+fOperand2.getDataAsDouble()+">");
	            return fOperand1.getDataAsDouble() -
	                   fOperand2.getDataAsDouble();

	        case kMultiplyOp:
	        	LOGGER.info("Calling Operation < MULTIPLY >, <" + fOperand1.getDataAsDouble() + ">, & <"+fOperand2.getDataAsDouble()+">");
	            return fOperand1.getDataAsDouble() *
	                   fOperand2.getDataAsDouble();

	        case kDivideOp:
	        	LOGGER.info("Calling Operation < DIVIDE >, <" + fOperand1.getDataAsDouble() + ">, & <"+fOperand2.getDataAsDouble()+">");
	            return fOperand1.getDataAsDouble() /
	                   fOperand2.getDataAsDouble();

	        case kModulusOp:
	        	LOGGER.info("Calling Operation < MODULUS >, <" + fOperand1.getDataAsDouble() + ">, & <"+fOperand2.getDataAsDouble()+">");
	            return fOperand1.getDataAsDouble() %
	                   fOperand2.getDataAsDouble();
	        
	        case kPercentOp:
	        	LOGGER.info("Calling Operation < PERCENT >, <" + fOperand1.getDataAsDouble() + ">, & <"+fOperand2.getDataAsDouble()+">");
	            double percent = fOperand1.getDataAsDouble() * 
	                   (fOperand2.getDataAsDouble()/100);
	            LOGGER.info("Returning percent result <" + percent +">");
	            return percent;
        }

        throw new Exception("Invalid operation");
	}
    
	@Override
	public Integer getDataAsInteger() throws Exception {
		//can return a value, but for simplicity throw error
		throw new Exception(K_OPERATION_NOT_SUPPORTED);
	}

	@Override
	public Float getDataAsFloat() throws Exception {
		//can return a value, but for simplicity throw error
		throw new Exception(K_OPERATION_NOT_SUPPORTED);
	}
	
	@Override
	public Double getDataAsDouble() throws Exception {
		return (Double)getData();
	}

	@Override
	public String getDataAsString() throws Exception {
		return getData().toString();
	}

}
