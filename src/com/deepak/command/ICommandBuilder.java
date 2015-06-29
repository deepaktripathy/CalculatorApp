package com.deepak.command;

/**
 * This Either can work as a marker interface or hold some default parameters.
 * 
 * Add any methods common to a generic command here.
 * 
 * @author deepak tripathy
 *
 */
public interface ICommandBuilder {
   public static final String ERROR_POP_PARAMS = "populateParams() failed";
   public static final String ERROR_BUILD_COMMAND = "Error in building Command.";
}
