package com.example.locproject.utils;

import com.example.locproject.constants.JavaRegexConstants;
import com.example.locproject.constants.SymbolsConstants;
import com.example.locproject.constants.FileFormatConstants;
import com.example.locproject.exceptions.FileFormatException;
import com.example.locproject.validators.CommentValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Utility class to validate the formatting of Java files according to Google's Java style.
 * It checks for correct brace placement, class and method brace styles, line lengths, and indentation.
 */
public class GoogleJavaFormatUtil {

  /**
   * Checks if the format of the given file is valid.
   *
   * @param file The Java file to be validated.
   * @return {@code true} if the file format is valid; {@code false} otherwise.
   */
  public boolean isFormatValid(File file) {
    try {
      Path path = file.toPath();
      List<String> lines = Files.readAllLines(path);
      return validateFormat(lines, file.getName());
    } catch (IOException | FileFormatException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  /**
   * Validates the format of the given lines from a Java file.
   *
   * @param lines    A list of strings representing each line in the file.
   * @param fileName The name of the file being validated.
   * @return {@code true} if the format is valid; {@code false} otherwise.
   * @throws FileFormatException if a formatting rule is violated.
   */
  private boolean validateFormat(List<String> lines, String fileName) throws FileFormatException {
    boolean isValid = true;
    CommentValidator commentValidator = new CommentValidator();
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      int lineNumber = i + 1;
      if (!commentValidator.isComment(line)) {
        if (!validateBraceStyle(line, lineNumber, fileName) || 
            !validateClassBraceStyle(line, lineNumber, fileName) ||
            !validateMethodBraceStyle(line, lineNumber, fileName) || 
            !validateLineLength(line, lineNumber, fileName) ||
            !validateIndentation(line, lineNumber, fileName)) {
          isValid = false;
        }
      }
    }
    return isValid;
  }

  /**
   * Validates the brace style for a given line.
   * The line must end with the opening brace if it contains one.
   *
   * @param line      The line to validate.
   * @param lineNumber The line number in the file.
   * @param fileName  The name of the file being validated.
   * @return {@code true} if the brace style is valid.
   * @throws FileFormatException if the brace style is invalid.
   */
  private boolean validateBraceStyle(String line, int lineNumber, String fileName) throws FileFormatException {
    if (line.contains(SymbolsConstants.OPENING_BRACE) && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
        throw new FileFormatException(fileName, lineNumber, 
            FileFormatConstants.INVALID_BRACE_STYLE_MESSAGE, line);
    }
    return true;
  }

  /**
   * Validates the brace style for class declarations.
   * A class declaration must end with the opening brace.
   *
   * @param line      The line to validate.
   * @param lineNumber The line number in the file.
   * @param fileName  The name of the file being validated.
   * @return {@code true} if the class brace style is valid.
   * @throws FileFormatException if the class brace style is invalid.
   */
  private boolean validateClassBraceStyle(String line, int lineNumber, String fileName) throws FileFormatException {
      Pattern classPattern = Pattern.compile(
          JavaRegexConstants.STRUCT_DECLARATION_REGEX + 
          JavaRegexConstants.CLASS_NAME_REGEX
      );
      if (classPattern.matcher(line).find() && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
          throw new FileFormatException(fileName, lineNumber, 
              FileFormatConstants.INVALID_CLASS_BRACE_STYLE_MESSAGE, line);
      }
      return true;
  }

  /**
   * Validates the brace style for method declarations.
   * A method declaration must end with the opening brace.
   *
   * @param line      The line to validate.
   * @param lineNumber The line number in the file.
   * @param fileName  The name of the file being validated.
   * @return {@code true} if the method brace style is valid.
   * @throws FileFormatException if the method brace style is invalid.
   */
  private boolean validateMethodBraceStyle(String line, int lineNumber, String fileName) throws FileFormatException {
      Pattern methodPattern = Pattern.compile(
          JavaRegexConstants.METHOD_DECLARATION_REGEX
      );
      if (methodPattern.matcher(line).find() && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
          throw new FileFormatException(fileName, lineNumber, 
              FileFormatConstants.INVALID_METHOD_BRACE_STYLE_MESSAGE, line);
      }
      return true;
  }

  /**
   * Validates the length of a given line.
   *
   * @param line      The line to validate.
   * @param lineNumber The line number in the file.
   * @param fileName  The name of the file being validated.
   * @return {@code true} if the line length is within the allowed limit.
   * @throws FileFormatException if the line exceeds the maximum allowed length.
   */
  private boolean validateLineLength(String line, int lineNumber, String fileName) throws FileFormatException {
      if (line.length() > FileFormatConstants.MAX_LINE_LENGTH) {
          throw new FileFormatException(fileName, lineNumber, 
              FileFormatConstants.INVALID_LINE_LENGTH_MESSAGE, line);
      }
      return true;
  }

  /**
   * Validates the indentation of a given line.
   * The line's indentation must be even if it starts with spaces.
   *
   * @param line      The line to validate.
   * @param lineNumber The line number in the file.
   * @param fileName  The name of the file being validated.
   * @return {@code true} if the indentation is valid.
   * @throws FileFormatException if the indentation is invalid.
   */
  private boolean validateIndentation(String line, int lineNumber, String fileName) throws FileFormatException {
      String trimmedLine = line.trim();
      if (!line.isBlank() && line.startsWith(SymbolsConstants.SPACE) && (line.indexOf(trimmedLine) % 2) != 0) {
          throw new FileFormatException(fileName, lineNumber, 
              FileFormatConstants.INVALID_INDENTATION_MESSAGE, line);
      }
      return true;
  }

}
