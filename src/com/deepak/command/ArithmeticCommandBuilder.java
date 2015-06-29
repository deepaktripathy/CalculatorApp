package com.deepak.command;

import java.util.Stack;
import com.deepak.dataholder.IDataHolder;

/**
 * Builds Arithmatic command. Like wise other commands can be built.
 * Has some limitation that complex commands cannot be built, but this is a fun demo.
 * @author deepak tripathy
 *
 */
class ArithmeticCommandBuilder
    extends AbstractCommandBuilder
{
    /** Operation to perform over DataHolders */
    protected int fOperation;

    /**
     * Tell us how which operation to perform in the command we build.
     * @param operator Operation to perform--use int values in
     * ArithmeticCommand.
     */
    public ArithmeticCommandBuilder(int operation)
    {
        fOperation = operation;
    }

    /**
     * Create the DataHolder using the DataHolders on the stack.
     * @param stack Stack to pop data from
     */
    protected final IDataHolder buildDataHolder(Stack stack)
    {
    	//TODO: Use populateParams as it validates the number of arguments
    	
    	IDataHolder operand2 = (IDataHolder) stack.pop();
        IDataHolder operand1 = (IDataHolder) stack.pop();

	    //log.debug("Calling operation, operand1 <"+operand1+">, & operand2 <"+operand2+">");
        return new ArithmeticCommand(fOperation, operand1, operand2);
    }
}
