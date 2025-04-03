package com.example.locproject.utils;

import com.example.locproject.constants.JavaRegexConstants;
import com.example.locproject.constants.SymbolsConstants;
import com.example.locproject.constants.FileFormatConstants;
import com.example.locproject.exceptions.FileFormatException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

/**
 * ========================================================================================
 *                                              CHANGELOG
 * ========================================================================================
 * 
 * FECHA         : 31-March-2025
 * VERSIÓN       : 2.0.0 (Release Estable)
 * AUTOR         : [Equipo 4]
 * 
 * =====================================[ DETAIL OF MODIFICATIONS ]=====================================
 * 
 * [FEATURE]  ┃ Implementing validation of multiple class declarations, interfaces, or enums 
 *             → Affected methods: ValidateFormat()
 *             → New methods: isClassInterfaceEnumDeclaration()
 * 
 * [FEATURE]  ┃ Implementing exception cases in the validateLineLength method
 *             → Affected methods: validateLineLength()
 *             → New methods: isLengthCheckException()
 * 
 * [REFACTOR] ┃ A long method was identified in validateFormat. Moreover, 
 *              implementation of messages when a validation fails
 *             → Affected methods: ValidateFormat()
 *             → New methods: validateClassBraceStyle(), validateMethodBraceStyle(),
 *               validateLineLength(), validateIndentation()
 * 
 * ======================================================================================================
 */

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
    boolean declarationFound = false;
    CommentAnalyzerUtil commentAnalyzer = new CommentAnalyzerUtil();
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      int lineNumber = i + 1;
      String currentLine = commentAnalyzer.removeTrailingInlineComment(line);
      if (!commentAnalyzer.isComment(currentLine)) {
        if (isClassInterfaceEnumDeclaration(currentLine)) {
          if (declarationFound) {
              throw new FileFormatException(fileName, lineNumber,
                FileFormatConstants.MULTIPLE_PUBLIC_CLASSES_MESSAGE, currentLine);
          }
          declarationFound = true;
        }
        if (!validateBraceStyle(currentLine, lineNumber, fileName) || 
            !validateClassBraceStyle(currentLine, lineNumber, fileName) ||
            !validateMethodBraceStyle(currentLine, lineNumber, fileName) || 
            !validateLineLength(currentLine, lineNumber, fileName) ||
            !validateIndentation(currentLine, lineNumber, fileName)) {
          isValid = false;
        }
      }
    }
    return isValid;
  }

  /**
   * Removes string literals from a line of code.
   *
   * @param line The line of code.
   * @return The modified line without string literals.
   */
  private static String deleteStringInsideCode(String line) {
    return line.replaceAll(
      JavaRegexConstants.QUOTED_STRING_REGEX, 
      SymbolsConstants.SPACE
    ).trim();
  }

  /**
 * Checks if the line contains a public class declaration.
 * Detects standard public classes as well as abstract and final public classes.
 * 
 * @param line the source code line to evaluate (must not be null)
 * @return true if the line contains a public class declaration,
 *         false otherwise
 */
  private boolean isClassInterfaceEnumDeclaration(String line) {
    String currentLine = deleteStringInsideCode(line);
    Pattern publicClassPattern = Pattern.compile(
      JavaRegexConstants.CLASS_INTERFACE_ENUM_DECLARATION_REGEX
    );
    return publicClassPattern.matcher(currentLine).find();
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
    String currentLine = deleteStringInsideCode(line);
    if (currentLine.contains(SymbolsConstants.OPENING_BRACE)
      && !currentLine.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
        throw new FileFormatException(fileName, lineNumber, 
            FileFormatConstants.INVALID_BRACE_STYLE_MESSAGE, currentLine);
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
    String currentLine = deleteStringInsideCode(line);
    Pattern classPattern = Pattern.compile(
      JavaRegexConstants.STRUCT_DECLARATION_REGEX + 
      JavaRegexConstants.CLASS_NAME_REGEX
    );
    if (classPattern.matcher(currentLine).find() 
      && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
        throw new FileFormatException(fileName, lineNumber, 
          FileFormatConstants.INVALID_CLASS_BRACE_STYLE_MESSAGE, currentLine);
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
    String currentLine = deleteStringInsideCode(line);
    Pattern methodPattern = Pattern.compile(
      JavaRegexConstants.METHOD_DECLARATION_REGEX
    );
    if (methodPattern.matcher(currentLine).find() 
      && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
        throw new FileFormatException(fileName, lineNumber, 
          FileFormatConstants.INVALID_METHOD_BRACE_STYLE_MESSAGE, currentLine);
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
    if(!isLengthCheckException(line)){
      if (line.length() > FileFormatConstants.MAX_LINE_LENGTH) {
        throw new FileFormatException(fileName, lineNumber, 
          FileFormatConstants.INVALID_LINE_LENGTH_MESSAGE, line);
      }
    }
    return true;
  }

  /**
   * Checks if a line should be exempt from line length validation.
   * 
   * @param line The line to check for exemption.
   * @return {@code true} if the line should be exempt from length checking.
  */
  private boolean isLengthCheckException(String line) {
    String normalizedLine = line.trim().replaceAll("\\s+", " ");
    
    Pattern pattern = Pattern.compile(
      "\\b(?:" + 
      JavaRegexConstants.METHOD_DECLARATION_REGEX + 
      "|" + 
      JavaRegexConstants.CONSTRUCTOR_DECLARATION_REGEX + 
      "|" + 
      JavaRegexConstants.IMPORT_REGEX + 
      ")"
    );
    
    return pattern.matcher(normalizedLine).find();
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
    if (!line.isBlank() 
      && line.startsWith(SymbolsConstants.SPACE) 
      && (line.indexOf(trimmedLine) % 2) != 0) {
        throw new FileFormatException(fileName, lineNumber, 
          FileFormatConstants.INVALID_INDENTATION_MESSAGE, line);
    }
    return true;
  }

}
