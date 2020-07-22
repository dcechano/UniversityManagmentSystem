package enumstests;

import com.example.ums.enums.AcademicStatus;
import com.example.ums.enums.converter.AcademicStatusConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAcademicStatusConverter {

    AcademicStatusConverter converter = new AcademicStatusConverter();
    AcademicStatus status;

    @Test
    public void convertToDatabaseColumnPositive() {
        status = AcademicStatus.NOT_ENROLLED;
        String code = converter.convertToDatabaseColumn(status);
        assertEquals(code, "N");
    }

    @Test
    public void convertToDatabaseColumnNegative() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    public void convertToEntityAttributePositive() {
        status = AcademicStatus.GOOD_STANDING;
        AcademicStatus newStatus = converter.convertToEntityAttribute(status.getCode());
        assertEquals(status, newStatus);

    }

    @Test
    public void convertToEntityAttributeNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convertToEntityAttribute("non-existent code");
        });
    }


}
