package utilitaire;

import java.util.function.Function;

/**
 * @author freva
 * @link https://github.com/freva/ascii-table
 */
public class ColumnData<T> extends Column {
    private final Function<T, String> getter;

    ColumnData(Column column, Function<T, String> getter) {
        super(column.getHeader(), column.getFooter(), column.getHeaderAlign(), column.getDataAlign(),
                column.getFooterAlign(), column.getMaxColumnWidth());
        this.getter = getter;
    }

    public String getCellValue(T object) {
        return getter.apply(object);
    }
}