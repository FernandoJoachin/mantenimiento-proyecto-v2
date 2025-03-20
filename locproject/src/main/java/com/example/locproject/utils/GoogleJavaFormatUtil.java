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

public class GoogleJavaFormatUtil {

  public boolean isFormatValid(File file) {
    try {
      Path path = file.toPath();
      List<String> lines = Files.readAllLines(path);
      return validateFormat(lines, file.getName());
    } catch (IOException e) {
      return false;
    }
  }

  private boolean validateFormat(List<String> lines, String fileName) {
    boolean isValid = true;
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      int lineNumber = i + 1;

      if (!validateBraceStyle(line, lineNumber, fileName) || 
          !validateClassBraceStyle(line, lineNumber, fileName) ||
          !validateMethodBraceStyle(line, lineNumber, fileName) || 
          !validateLineLength(line, lineNumber, fileName) ||
          !validateIndentation(line, lineNumber, fileName)) {
        isValid = false;
      }
    }
    return isValid;
  }

  private boolean validateBraceStyle(String line, int lineNumber, String fileName) {
    try {
      if (line.contains(SymbolsConstants.OPENING_BRACE)
          && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
        throw new FileFormatException(fileName, lineNumber, 
            FileFormatConstants.INVALID_BRACE_STYLE_MESSAGE, line);
      }
      return true;
    } catch (FileFormatException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  private boolean validateClassBraceStyle(String line, int lineNumber, String fileName) {
    try {
      Pattern classPattern = Pattern.compile(
          JavaRegexConstants.STRUCT_DECLARATION_REGEX +
          JavaRegexConstants.CLASS_NAME_REGEX
      );
      if (classPattern.matcher(line).find()
          && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
        throw new FileFormatException(fileName, lineNumber, 
            FileFormatConstants.INVALID_CLASS_BRACE_STYLE_MESSAGE, line);
      }
      return true;
    } catch (FileFormatException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  private boolean validateMethodBraceStyle(String line, int lineNumber, String fileName) {
    try {
      Pattern methodPattern = Pattern.compile(
          JavaRegexConstants.METHOD_DECLARATION_REGEX +
          JavaRegexConstants.ANY_CHARACTERS
      );
      if (methodPattern.matcher(line).find()
          && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
        throw new FileFormatException(fileName, lineNumber, 
            FileFormatConstants.INVALID_METHOD_BRACE_STYLE_MESSAGE, line);
      }
      return true;
    } catch (FileFormatException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  private boolean validateLineLength(String line, int lineNumber, String fileName) {
    try {
      if (line.length() > FileFormatConstants.MAX_LINE_LENGTH) {
        throw new FileFormatException(fileName, lineNumber, 
            FileFormatConstants.INVALID_LINE_LENGTH_MESSAGE, line);
      }
      return true;
    } catch (FileFormatException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  private boolean validateIndentation(String line, int lineNumber, String fileName) {
    try {
      String trimmedLine = line.trim();
      if (!line.isBlank() 
          && line.startsWith(SymbolsConstants.SPACE)
          && (line.indexOf(trimmedLine) % 2) != 0) {
        throw new FileFormatException(fileName, lineNumber, 
            FileFormatConstants.INVALID_INDENTATION_MESSAGE, line);
      }
      return true;
    } catch (FileFormatException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }
}
