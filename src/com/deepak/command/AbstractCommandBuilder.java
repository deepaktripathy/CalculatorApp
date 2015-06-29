package com.deepak.command;

import java.util.Stack;
import java.util.logging.Logger;
import com.deepak.dataholder.IDataHolder;

/**
 * Common abstract class for stack-based commands which are used to
 * build formulae. Uses and assume RPN Grammar.
 * 
 * @author deepak tripathy
 */
abstract class AbstractCommandBuilder implements ICommandBuilder
{
	private static final Logger LOGGER = Logger.getLogger(AbstractCommandBuilder.class.getName());
	
	/**
     * This method executes this command. This might put the object on the
     * stack or pop items and calculate new results to put on the stack.
     * @param stack Stack to push/pop data to/from
     */
    public final void build(Stack stack)
        throws Exception
    {
        stack.push(buildDataHolder(stack));
    }

    /**
     * Create the DataHolder using the DataHolders on the stack.
     * derived classes can override this to build their own DataHolder.
     * @param stack Stack to pop data
     */
    protected abstract IDataHolder buildDataHolder(Stack stack)
        throws Exception;

    /**
     * Trigger exception if all the parameters are not successfully 
     * popped, means the expression might be wrong.
     */
    protected static Object [] populateParams(Stack stack, int numParams)
        throws Exception
    {
        try
        {
        	Object [] params = new Object[numParams];

            for (int paramNum = 0; paramNum < numParams; paramNum++)
            {
                params[paramNum] = (Object) stack.pop();
            }

            return params;
        }
        catch (Exception e)
        {
            throw new Exception(ERROR_POP_PARAMS, e);
        }
    }

}
