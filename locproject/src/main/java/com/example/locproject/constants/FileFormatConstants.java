package com.example.locproject.constants;

/**
 * This class defines constants for file format validation messages and file formatting rules.
 * These constants are used to standardize error messages when validating the formatting of source files.
 */
public class FileFormatConstants {

    /**
     * Error message indicating that the brace style used in the code is incorrect.
     */
    public static final String INVALID_BRACE_STYLE_MESSAGE = 
    "Incorrect brace style";

    /**
     * Error message indicating that the brace style in a class declaration is incorrect.
     */
    public static final String INVALID_CLASS_BRACE_STYLE_MESSAGE = 
    "Incorrect brace style in class declaration";

    /**
     * Error message indicating that the brace style in a method declaration is incorrect.
     */
    public static final String INVALID_METHOD_BRACE_STYLE_MESSAGE = 
    "Incorrect brace style in method declaration";

    /**
     * Error message indicating that a line exceeds the maximum allowed length of 100 characters.
     */
    public static final String INVALID_LINE_LENGTH_MESSAGE = "Exceeds 100 characters";

    /**
     * Error message indicating that the indentation in the code is incorrect.
     */
    public static final String INVALID_INDENTATION_MESSAGE = "Incorrect indentation";

    /**
     * Error message format for when multiple public classes are found in a single file
     */
    public static final String MULTIPLE_PUBLIC_CLASSES_MESSAGE = 
        "Only one public class is allowed per file";

    /**
     * The maximum allowed length for a single line of code.
     */
    public static final int MAX_LINE_LENGTH = 100;
}
