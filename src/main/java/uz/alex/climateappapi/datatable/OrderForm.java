package uz.alex.climateappapi.datatable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm implements Serializable {
    private static final long serialVersionUID = -2069131671088646133L;

    private int column;
    private String dir;
}
