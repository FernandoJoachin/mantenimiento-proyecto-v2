package com.example.locproject.constants;

/**
 * This class provides a collection of regular expression constants
 * commonly used for parsing and validating Java code structures.
 * These constants are designed to match various Java language constructs,
 * such as method declarations, class declarations, control flow statements,
 * and more.
 */
public class JavaRegexConstants {
    /**
     * Regular expression to match access modifiers (public, private, protected).
     * Example: "public", "private", "protected".
     */
    public final static String ACCESS_MODIFIERS_REGEX = "((public|private|protected)\\s+)";

    /**
     * Regular expression to match data type declarations, including generics.
     * Example: "String", "List<String>".
     */
    public final static String DATATYPE_DECLARATION_REGEX = "(\\s*[a-zA-Z0-9]+(<[a-zA-Z0-9]+>)?\\s+)";

    /**
     * Regular expression to match the "throws" clause in method declarations.
     * Example: "throws IOException, SQLException".
     */
    public final static String THROWS_DECLARATION_REGEX = "(\\s+(throws\\s+(\\w+\\s*,\\s*)*\\w+)\\s*)?";

    /**
     * Regular expression to match method or constructor parameters.
     * Example: "(int a, String b)".
     */
    public final static String PARAMETERS_DECLARATION_REGEX = "(\\([^)]*\\)\\s*)";

    /**
     * Regular expression to match identifiers (e.g., variable names, method names).
     * Example: "myVariable", "myMethod".
     */
    public final static String IDENTIFIER_DECLARATION_REGEX = "\\w+\\s*";

    /**
     * Regular expression to match class, enum, or interface declarations.
     * Example: "class MyClass", "interface MyInterface".
     */
    public final static String STRUCT_DECLARATION_REGEX = "((class|enum|interface)\\s+)";

    /**
     * Regular expression to match "final" and/or "static" keywords in any order.
     * Example: "static final", "final static", "static", "final".
     */
    public final static String FINAL_OR_STATIC_REGEX = "(?:(?:static\\s+)?(?:final\\s+)?|(?:final\\s+)?(?:static\\s+)?)?";

    /**
     * Regular expression to match method declarations, including access modifiers,
     * return type, method name, parameters, and throws clause.
     * Example: "public static void main(String[] args) throws Exception".
     */
    public static final String METHOD_DECLARATION_REGEX = 
        ACCESS_MODIFIERS_REGEX + 
        FINAL_OR_STATIC_REGEX +          
        DATATYPE_DECLARATION_REGEX +       
        IDENTIFIER_DECLARATION_REGEX +     
        PARAMETERS_DECLARATION_REGEX +     
        THROWS_DECLARATION_REGEX;

    /**
     * Regular expression to match class names according to naming conventions.
     * Example: "class MyClass {", "interface PaymentProcessor {".
     */
    public static final String CLASS_NAME_REGEX = "[A-Z][a-zA-Z0-9]*\\s*";
}