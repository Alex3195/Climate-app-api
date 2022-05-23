package uz.alex.climateappapi.datatable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterForm implements Serializable {
    private static final long serialVersionUID = -1183975305038088044L;

    private int draw;
    private int start;
    private int length;
    private List<ColumnsForm> columns;
    private List<OrderForm> order;
    private SearchFrom search;
    private Map<String, String> filter;

    private Map<String, String> customDataMap = new HashMap<>();

    public String getSearchTxt() {
        if (search != null)
            return search.getValue();
        return null;
    }

    public Map<String, OrderForm> orderMap() {
        return order
                .stream()
                .filter(o -> columns.size() > o.getColumn())
                .collect(Collectors.toMap(o -> columns.get(o.getColumn()).getData(), o -> o));
    }
}
