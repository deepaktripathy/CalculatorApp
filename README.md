# CalculatorApp
A simple Calculator that parses RPN grammar
---------------------------------------------

Nothing much here except the expression is parsed and converted to DataHolders & CommandBuilders using a factory. The factory loads the expression in a stack and based on data/command type converts it to a command object and pushes back to the stack. Finally the stack is empty. Then when the getValue is called on the resulting command object all the objects are calculated and result is returned.

Some code is imported from my stockTradingApp project, mainly the expression evaluation and DataHolder pieces. 

This is a eclipse project and main method contains two examples of this. Currently has no command prompt interface, but that will be added as well as In-Fix expression handling.
