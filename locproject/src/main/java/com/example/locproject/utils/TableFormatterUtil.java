package com.example.locproject.utils;

import java.util.Arrays;
import java.util.List;
import com.example.locproject.constants.SymbolsConstants;

/**
 * Utility class for formatting data into a table.
 * Provides methods to calculate column widths, create header formats, and build table separators.
 */
public class TableFormatterUtil {

    /**
     * Template for formatting each column in a row.
     */
    private static final String COLUMN_FORMAT_TEMPLATE = 
    "| %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds |\n";
    
    /**
     * The horizontal padding added to each column width.
     */
    private static final int HORIZONTAL_PADDING = 2;

    /**
     * Formats a table with the specified headers and data rows.
     *
     * @param headers A list of header names for the table columns.
     * @param data    A list of rows, where each row is a list of strings representing the columns.
     * @return A formatted string representing the complete table.
     */
    public static String formatTable(List<String> headers, List<List<String>> data) {
        int[] columnWidths = calculateColumnWidths(headers, data);
        StringBuilder table = new StringBuilder();
        String headerFormat = createHeaderFormat(columnWidths);
        String separator = createSeparator(columnWidths);

        table.append(separator);
        table.append(String.format(headerFormat, headers.toArray()));

        for (List<String> row : data) {
            table.append(separator);
            table.append(String.format(headerFormat, row.toArray()));
        }
        table.append(separator);
        return table.toString();
    }

    /**
     * Calculates the width of each column based on the maximum content length among headers and rows.
     *
     * @param headers A list of header names.
     * @param data    A list of rows, where each row is a list of column values.
     * @return An array of integers representing the width for each column.
     */
    private static int[] calculateColumnWidths(List<String> headers, List<List<String>> data) {
        int columns = headers.size();
        int[] widths = new int[columns];

        for (int i = 0; i < columns; i++) {
            int maxWidth = headers.get(i).length();
            for (List<String> row : data) {
                if (i < row.size() && row.get(i) != null) {
                    maxWidth = Math.max(maxWidth, row.get(i).length());
                }
            }
            widths[i] = maxWidth;
        }
        return widths;
    }

    /**
     * Creates a format string for table rows based on the column widths.
     *
     * @param widths An array of integers representing the width for each column.
     * @return A format string for table rows.
     */
    private static String createHeaderFormat(int... widths) {
        return String.format(COLUMN_FORMAT_TEMPLATE, Arrays.stream(widths).boxed().toArray());
    }

    /**
     * Creates a separator string for the table using the column widths.
     *
     * @param widths An array of integers representing the width for each column.
     * @return A string representing the separator line of the table.
     */
    private static String createSeparator(int... widths) {
        StringBuilder separator = new StringBuilder(SymbolsConstants.PLUS_SIGN);
        for (int width : widths) {
            separator.append(SymbolsConstants.MINUS_SIGN.repeat(width + HORIZONTAL_PADDING))
                .append(SymbolsConstants.PLUS_SIGN);
        }
        return separator.append("\n").toString();
    }
}
