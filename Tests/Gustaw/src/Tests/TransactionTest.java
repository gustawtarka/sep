import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void create() throws Exception {
        User sender = new User("Doctor", "", "", "", "");
        User receiver = new User("Gromp", "", "", "", "");

        sender.updateLedger("BTC", 100.0);

        for(String s : sender.balanceLedger.keySet()){
            System.out.println(s + "  " + sender.balanceLedger.get(s));
        }

        Transaction tx = Transaction.create( 30, "BTC", sender, receiver);

        assertEquals(30, tx.getAmount());
        assertEquals("BTC", tx.getToken());
        assertEquals(sender, tx.getSender());
        assertEquals(receiver, tx.getReceiver());
    }

    @Test
    void insufficientFundsException() {
        User sender = new User("Doctor", "", "", "", "");
        User receiver = new User("Gromp", "", "", "", "");

        assertThrows(Exception.class, () ->
                Transaction.create( 50, "BTC", sender, receiver)
        );
    }

    @Test
    void setAmount() {
        Transaction tx = new Transaction(
                10, null, "XXXX", null);

        tx.setAmount(999);
        assertEquals(999, tx.getAmount());

        tx.setAmount(-50);
        assertEquals(999, tx.getAmount());

        tx.setAmount(0);
        assertEquals(999, tx.getAmount());
    }

    @Test
    void setToken() {
        Transaction tx = new Transaction(
                10, null, "XXXX", null);

        tx.setToken("USDT");
        assertEquals("USDT", tx.getToken());

        tx.setToken("SEK");
        assertEquals("SEK", tx.getToken());

        tx.setToken(null);
        assertEquals("SEK", tx.getToken());
    }

    @Test
    void setSender() {
        User user = new User("Test", "", "", "", "");
        Transaction tx = new Transaction(
                10, null, "XXXX", null);

        tx.setSender(user);
        assertEquals(user, tx.getSender());

        tx.setSender(null);
        assertEquals(user, tx.getSender());
    }

    @Test
    void setReceiver() {
        User user = new User("Test", "", "", "", "");
        Transaction tx = new Transaction(
                10, null, "XXXX", null);

        tx.setReceiver(user);
        assertEquals(user, tx.getReceiver());

        tx.setReceiver(null);
        assertEquals(user, tx.getReceiver());
    }
}