package uz.alex.climateappapi.datatable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFrom implements Serializable {
    private static final long serialVersionUID = -3029620531741939122L;

    private String value;
    private boolean regex;
}
