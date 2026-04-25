import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    void setup() {
        user = new User("Jibbity", "123 St", "jibbity@jibbity.hr", "1234567", "20112020");
    }

    @Test
    void setEmail() {
        user.setEmail("new@mail.ee");
        assertEquals("new@mail.ee", user.getEmail());

        user.setEmail("not valid");
        assertEquals("new@mail.ee", user.getEmail());

        user.setEmail(null);
        assertEquals("new@mail.ee", user.getEmail());
    }

    @Test
    void setPhoneNo() {
        user.setPhoneNo("9999999");
        assertEquals("9999999", user.getPhoneNo());

        user.setPhoneNo("123");
        assertEquals("9999999", user.getPhoneNo());

        user.setPhoneNo(null);
        assertEquals("9999999", user.getPhoneNo());
    }

    @Test
    void setStudentNo() {
        user.setStudentNo("20113304");
        assertEquals("20113304", user.getStudentNo());

        user.setStudentNo("XX");
        assertEquals("20113304", user.getStudentNo());

        user.setStudentNo(null);
        assertEquals("20113304", user.getStudentNo());
    }

    @Test
    void updateLedger() {
        user.updateLedger("BTC", 150.0);
        assertEquals(150.0, user.balanceLedger.get("BTC"));

        user.updateLedger("BTC", 75.5);
        assertEquals(75.5, user.balanceLedger.get("BTC"));

        user.updateLedger("ETH", 200.0);
        assertEquals(200.0, user.balanceLedger.get("ETH"));
    }
}