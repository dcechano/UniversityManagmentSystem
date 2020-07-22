package enumstests;

import com.example.ums.enums.EmploymentStatus;
import com.example.ums.enums.converter.EmploymentStatusConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEmploymentStatusConverter {

    EmploymentStatusConverter converter = new EmploymentStatusConverter();
    EmploymentStatus status;

    @Test
    public void convertToDatabaseColumnPositive() {
        status = EmploymentStatus.FULL_TIME;
        String code = converter.convertToDatabaseColumn(status);
        assertEquals(code, "F");
    }

    @Test
    public void convertToDatabaseColumnNegative() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    public void convertToEntityAttributePositive() {
        status = EmploymentStatus.PART_TIME;
        EmploymentStatus newStatus = converter.convertToEntityAttribute(status.getCode());
        assertEquals(status, newStatus);

    }

    @Test
    public void convertToEntityAttributeNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convertToEntityAttribute("non-existent code");
        });
    }


}
