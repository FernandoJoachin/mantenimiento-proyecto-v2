package com.example.locproject.exceptions;

/**
 * Custom exception to indicate a file format error during validation.
 * This exception builds an error message that includes the file name,
 * the line number where the error occurred, a specific error message,
 * and the content of the problematic line.
 */
public class FileFormatException extends Exception {

    /**
     * Constructs a new FileFormatException with detailed information.
     *
     * @param fileName     The name of the file in which the error was detected.
     * @param lineNumber   The line number where the error occurred.
     * @param errorMessage A specific message describing the error.
     * @param lineContent  The content of the line where the error was found.
     */
    public FileFormatException(String fileName, int lineNumber, 
                               String errorMessage, String lineContent) {
        super(createMessage(fileName, lineNumber, errorMessage, lineContent));
    }

    /**
     * Creates a formatted error message based on the provided details.
     *
     * @param fileName     The name of the file.
     * @param line         The line number where the error occurred.
     * @param errorMessage A specific message describing the error.
     * @param content      The content of the line with the error.
     * @return A formatted string that combines the provided details.
     */
    private static String createMessage(String fileName, int line, 
                                        String errorMessage, String content) {
        return String.format(
            "[%s] Line %d - %s | Content: '%s'",
            fileName,
            line,
            errorMessage,
            content
        );
    }
}
