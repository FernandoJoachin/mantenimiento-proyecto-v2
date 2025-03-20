package com.example.locproject.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.locproject.constants.JavaRegexConstants;
import com.example.locproject.interfaces.Counter;

/**
 * The {@code LogicalLineCounter} class implements the {@code LineCounter} interface
 * to count logical lines of code in a given Java source file.
 * A logical line is determined based on specific Java constructs such as class declarations,
 * method declarations, flow control structures, and try blocks.
 */
public class LogicalLineCounter extends Counter {

    @Override
    public void count(String line) {
        if (!this.commentValidator.isComment(line) & isLogicalLine(line)) {
            this.counter++;
        } 
    }

    /**
     * Determines whether a given line of code qualifies as a logical line.
     * A line is considered logical if it matches any of the predefined patterns
     * for Java structures such as class declarations, method declarations, flow control
     * statements, or try blocks.
     *
     * @param line The line of code to analyze.
     * @return {@code true} if the line is a logical line, otherwise {@code false}.
     */
    private boolean isLogicalLine(String line) {
        line = line.trim();

        Pattern pattern = Pattern.compile(
            JavaRegexConstants.CLASS_INSTANTIATION_REGEX);
            
        if (pattern.matcher(line).find()) {
            return false;
        }

        pattern = Pattern.compile(JavaRegexConstants.ELSE_IF_REGEX);
        if (pattern.matcher(line).find()) {
            return false;
        }

        pattern = Pattern.compile(
            JavaRegexConstants.STRUCT_DECLARATION_REGEX + 
            "|" +
            JavaRegexConstants.METHOD_DECLARATION_REGEX +
            "|" +
            JavaRegexConstants.FLOW_CONTROL_REGEX +
            "|" +
            JavaRegexConstants.TRY_DECLARATION_REGEX
        );

        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            return true;
        }
        
        return false;
    }
}