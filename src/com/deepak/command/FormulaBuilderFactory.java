package com.deepak.command;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

import javax.naming.OperationNotSupportedException;

import com.deepak.dataholder.IDataHolder;
import com.deepak.dataholder.NumericDataHolder;
import com.deepak.dataholder.StringDataHolder;


/**
 * Extracts values using formulas in Reverse Polish Notation.
 * 
 * @author deepak tripathy
 */
public class FormulaBuilderFactory
{
	private static final Logger LOGGER = Logger.getLogger(FormulaBuilderFactory.class.getName());

	/** Parameter containing InFix formula */
	public final static String kFormulaParamInfix = "formulaInfix";

	/** Parameter containing RPN formula */
	public final static String kFormulaParam = "formula";

	/** List of operators for all fields */
	private final static Map gOperators;

	static
	{
		gOperators = new HashMap();

		gOperators.put("+",                      new ArithmeticCommandBuilder(ArithmeticCommand.kAddOp));
		gOperators.put("-",                      new ArithmeticCommandBuilder(ArithmeticCommand.kSubtractOp));
		gOperators.put("*",                      new ArithmeticCommandBuilder(ArithmeticCommand.kMultiplyOp));
		gOperators.put("/",                      new ArithmeticCommandBuilder(ArithmeticCommand.kDivideOp));
		gOperators.put("%",                      new ArithmeticCommandBuilder(ArithmeticCommand.kModulusOp));
		//can add more operations
	}

	/** THE singleton instance */
	private static FormulaBuilderFactory gInstance = new FormulaBuilderFactory();

	/**
	 * Get the single instance of this factory.
	 * @return Our one and only FormulaBuilderFactory
	 */
	public static FormulaBuilderFactory getInstance()
	{
		return gInstance;
	}

	/**
	 * Private constructor so we can control single instance.
	 * Call getInstance() to get the factory.
	 */
	private FormulaBuilderFactory() { }

	/**
	 * Method to build and return a DataHolder using the DataHolders in the input Map.
	 * 
	 * @param params Parameters 
	 * @param defaultValue Default value for the DataHolder (or null for none)
	 * @throws Exception if the type is not available or undefined
	 */
	public IDataHolder newDataHolder(Map params)
	throws Exception
	{
		//first assume infix, else RPN
		String formulaParamStr = (String) params.get(kFormulaParamInfix);
		if(formulaParamStr != null && !formulaParamStr.isEmpty()) {
			return buildExpressionInfix(formulaParamStr);
		}
		else {
			formulaParamStr = (String) params.get(kFormulaParam);
			return buildExpressionRPN(formulaParamStr);
		}

	}

	/**
	 * Parse an RPN string to build the formula that this field will
	 * execute when extracting its data. Tokens in the String are
	 * either operators, fields, or constants. The convention is that
	 * fields begin with letters, constants with numbers, and
	 * operators with anything else. Operators are any length and may
	 * operate on any number of opearands on the stack.
	 * 
	 * EXAMPLE RPN String: "(1+2) x 3" becomes:. "3 2 1 + x".
	 * 
	 * @param formula The RPN string to parse as the field's formula
	 */
	public static IDataHolder buildExpressionRPN(String formula)
	throws Exception
	{
		Stack stack = new Stack();

		try
		{
			StreamTokenizer formulaTok = new StreamTokenizer(new StringReader(formula));
			formulaTok.wordChars('\u0021', '\u007e');
			formulaTok.quoteChar('\'');
			formulaTok.quoteChar('\"');

			int tokenType;
			while ((tokenType = formulaTok.nextToken()) != StreamTokenizer.TT_EOF)
			{
				switch (tokenType)
				{
				// Operator (from a String)
				case StreamTokenizer.TT_WORD:
					pushOrBuildCommand(stack, formulaTok.sval);
					break;

					// Operator (single character)
				default:
					pushOrBuildCommand(stack,
							String.valueOf((char) tokenType));
				break;
				// Number
				case StreamTokenizer.TT_NUMBER:
					stack.push(new NumericDataHolder(formulaTok.nval)); //double value
					LOGGER.info("Pushed to Stack(N): <"+formulaTok.nval+">");
					break;

					// String constant
				case '\'':
					pushOrBuildCommand(stack, formulaTok.sval);
					break;
				case '\"':
					stack.push(new StringDataHolder(formulaTok.sval)); //String value
					LOGGER.info("Pushed to Stack(S/D/QT): <"+formulaTok.sval+">");
					break;
				}
			}
		}
		catch (Exception e)
		{
			throw new Exception("Error! Failed to parse formula", e);
		}

		IDataHolder result = (IDataHolder) stack.pop();

		if (! stack.empty())
		{
			throw new Exception("Error! Stack has " + stack.size()
					+ " elements remaining for formula ["+formula+"]");
		}

		return result;
	}

	/**
	 * Builds InFix expression.
	 * 
	 * NOTE: INCOMPLETE!!! Not yet started
	 * 
	 * How To: to evaluate ((2+3) * (4+1)) /2, the basic operation is (ope1) operation (ope2), this can be loaded to two stacks. 
	 * But it  must handle grouping/brackets as well.
	 * Bracket case: for each left bracket push it to an array and when hit the same numbered right bracket pop it to a command
	 * No-Bracket: an operand is incomplete without the right side so when any operand is found, keep popping till next operand, 
	 * we have a complete expression now on the left side, build a command and push it to queue. Once the queue is complete, 
	 * we can get the expression from the left side (if can still use a stack but we have to copy the result back to the original 
	 * one to reverse it.)  
	 * 
	 * TODO: Work on it.
	 * 
	 * @param formula
	 * @return
	 * @throws Exception
	 */
	public static IDataHolder buildExpressionInfix(String formula) throws Exception
	{
		throw new OperationNotSupportedException();
	}

	/**
	 * Take a String and interpret it as either an operator or an
	 * command. If we find it's an operator/data, we let the command
	 * builder use the current stack and build a DataHolder. If it's not
	 * an operator, we assume it's the name of an Command Builder and push it.
	 * 
	 * @param stack The stack to use for operands or push
	 * @param name The name of the operator or command to create
	 */
	protected static void pushOrBuildCommand(Stack stack, String name)
	throws Exception
	{
		// First try to get an operator for this name
		AbstractCommandBuilder builder =
			(AbstractCommandBuilder) gOperators.get(name);

		// If we got an operator, build the builder.
		if (builder != null)
		{
			builder.build(stack);
			LOGGER.info("Called builder for : <"+name+">");
		}
		else {
			// Builder is not available, means expression integrity is broken. should we not error out the whole application?
			LOGGER.severe("Command Builder not registered for command : <"+name+">");
		}
	}
}
