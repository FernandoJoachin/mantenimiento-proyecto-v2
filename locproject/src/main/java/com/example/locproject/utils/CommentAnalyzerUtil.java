package com.example.locproject.utils;

import java.util.regex.Pattern;

import com.example.locproject.constants.SymbolsConstants;

/**
 * Util class to identify a comment.
 * Provides functionality to determine whether
 * a given line of code is a comment in Java source code. It recognizes both
 * line comments (starting with //) and block comments (enclosed between /* and * /)..
 */
public class CommentAnalyzerUtil {
    /**
     * Regular expression to match line comments. A line comment starts with {@code //} and
     * can be preceded by whitespace characters.
     */
    private final String LINE_COMMENT_REGEX = "^\\s*//.*";
    
    /**
     * Regular expression to remove trailing inline comments.
     * Example: "// comment" -> ""
     */
    private static final String REMOVE_TRAILING_COMMENT_REGEX = "//.*$";

    /**
     * Regular expression to match the start of a block comment. A block comment starts with
     * {@code /*} and can be preceded by whitespace characters.
     */
    private final String START_BLOCK_COMMENT_REGEX = "^\\s*/\\*.*";

    /**
     * Regular expression to match the end of a block comment. A block comment ends with
     * {@code * /} and can be followed by whitespace characters.
     */
    private final String END_BLOCK_COMMENT_REGEX = ".*\\*/\\s*$";

    /**
     * Indicates whether the validator is currently inside a block comment.
     */
    private boolean isInsideBlockComment = false;

    /**
     * Determines if the given line is a comment.
     * 
     * @param line The line of code to check
     * @return true if the line is a comment, false otherwise
     */
    public boolean isComment(String line) {
        if (isInsideBlockComment) {
            if (line.matches(END_BLOCK_COMMENT_REGEX)) {
                isInsideBlockComment = false;
            }
            return true;
        } else if (line.matches(START_BLOCK_COMMENT_REGEX)) {
            isInsideBlockComment = !line.matches(END_BLOCK_COMMENT_REGEX);
            return true;
        } else {
            return line.matches(LINE_COMMENT_REGEX);
        }
    }

    public String removeTrailingInlineComment(String line) {       
        Pattern trailingInlineCommetPattern = Pattern.compile(
            REMOVE_TRAILING_COMMENT_REGEX
        );
        if (trailingInlineCommetPattern.matcher(line).find()) {
            return line.replaceAll(
                REMOVE_TRAILING_COMMENT_REGEX,
                SymbolsConstants.SPACE
            ).trim();
        }
        return line;
    }
}