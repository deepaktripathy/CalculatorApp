package com.deepak.calculator;

import java.util.HashMap;
import java.util.Map;

import com.deepak.command.FormulaBuilderFactory;
import com.deepak.dataholder.IDataHolder;


/**
 * A calculator class to demo parsing RPN & InFix expression (TBD)
 * 
 * @author deepak tripathy
 *
 */
public class Calculator {

	/**
	 * Usees RPN Parsing... 
	 * 
	 * How about normal parsing?
	 * 	         //EXAMPLE RPN String: "5 + ((1 + 2) * 4) - 3" becomes: "5 1 2 + 4 * + 3 -" = +14.
	 * 
	 * 
	 * @param expression
	 * @return
	 */
	private Object calculateValue(String expression) {

		try {
			FormulaBuilderFactory factory =FormulaBuilderFactory.getInstance();

			Map params = new HashMap();
			System.out.println("Testing a simple expression.");
			//EXAMPLE InFix->RPN String: "5 + ((1 + 2) * 4) - 3" becomes: "5 1 2 + 4 * + 3 -" = +14.
			params.put("formula", "5 1 2 + 4 * + 3 -");
			//params.put("formula", "4 2 5 6.21 - * +");
			
			//not yet implemented
			//params.put("formulaInfix", "5 + ((1 + 2) * 4) - 3");
			
			IDataHolder resultHolder = factory.newDataHolder(params);
			String result1 = resultHolder.getData().toString();
			System.out.println("Result of operation <"+result1 +">.");
		}
		catch(Exception dse)
		{
			dse.printStackTrace();
		}

		return null;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String expr = null;
		if(args.length > 0) {
			expr = args[1];
		}
		Calculator calc = new Calculator();
		Object value = calc.calculateValue(expr);

	}

}
