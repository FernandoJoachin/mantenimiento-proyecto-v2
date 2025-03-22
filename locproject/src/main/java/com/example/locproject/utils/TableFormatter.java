package com.example.locproject.utils;

import java.util.Arrays;
import java.util.List;

import com.example.locproject.constants.SymbolsConstants;

public class TableFormatter {

    private static final String COLUMN_FORMAT_TEMPLATE = "| %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds |\n";
    private static final int HORIZONTAL_PADDING = 2;

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

    private static String createHeaderFormat(int... widths) {
        return String.format(COLUMN_FORMAT_TEMPLATE, Arrays.stream(widths).boxed().toArray());
    }

    private static String createSeparator(int... widths) {
        StringBuilder separator = new StringBuilder(SymbolsConstants.PLUS_SIGN);
        for (int width : widths) {
            separator.append(SymbolsConstants.MINUS_SIGN.repeat(width + HORIZONTAL_PADDING)).append(SymbolsConstants.PLUS_SIGN);
        }
        return separator.append("\n").toString();
    }
}
