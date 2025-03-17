package com.example.locproject.utils;

import com.example.locproject.constants.JavaRegexConstants;
import com.example.locproject.constants.SymbolsConstants;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class GoogleJavaFormatUtil {

  public static final int MAX_LINE_LENGTH = 100;

  public boolean isFormatValid(File file) {
    try {
      Path path = file.toPath();
      List<String> lines = Files.readAllLines(path);
      return validateFormat(lines);
    } catch (IOException e) {
      return false;
    }
  }

  private boolean validateFormat(List<String> lines) {
    boolean isValid = true;
    for (String line : lines) {
      if (!validateBraceStyle(line)) {
        isValid = false;
      }
      if (!validateClassBraceStyle(line)) {
        isValid = false;
      }
      if (!validateMethodBraceStyle(line)) {
        isValid = false;
      }
      if (!validateLineLength(line)) {
        isValid = false;
      }
      if (!validateIndentation(line)) {
        isValid = false;
      }
    }
    return isValid;
  }

  private boolean validateBraceStyle(String line) {
    if (line.contains(SymbolsConstants.OPENING_BRACE)
        && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
      return false;
    }
    return true;
  }

  private boolean validateClassBraceStyle(String line) {
    Pattern classPattern = Pattern.compile(
        JavaRegexConstants.STRUCT_DECLARATION_REGEX +
        JavaRegexConstants.CLASS_NAME_REGEX
    );
    if (classPattern.matcher(line).find()
        && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
      return false;
    }
    return true;
  }

  private boolean validateMethodBraceStyle(String line) {
    Pattern methodPattern = Pattern.compile(
        JavaRegexConstants.METHOD_DECLARATION_REGEX +
        JavaRegexConstants.ANY_CHARACTERS
    );
    if (methodPattern.matcher(line).find()
        && !line.trim().endsWith(SymbolsConstants.OPENING_BRACE)) {
      return false;
    }
    return true;
  }

  private boolean validateLineLength(String line) {
    if (line.length() > MAX_LINE_LENGTH) {
      return false;
    }
    return true;
  }

  private boolean validateIndentation(String line) {
    String trimmedLine = line.trim();
    if (!line.isBlank() 
        && line.startsWith(SymbolsConstants.SPACE)
        && (line.indexOf(trimmedLine) % 2) != 0) {
      return false;
    }
    return true;
  }
}
