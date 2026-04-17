import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class JoeyTests {
    BackEnd backEnd=new BackEnd();

    @BeforeEach
    void setUp() {
        backEnd.addBooking("Joey", new ParkingSlot(1, 31, 9, 2026));
        backEnd.addBooking("Bao", new ParkingSlot(2, 12, 2, 2027));
        backEnd.addBooking("Bao", new ParkingSlot(3, 15, 6, 2027)); //Bao has 4 credits
        backEnd.addBooking("Joey", new ParkingSlot(4, 23, 4, 2027));
        backEnd.addBooking("Joey", new ParkingSlot(5, 30, 1, 2027)); //Joey has 1 credit
        backEnd.addBooking("Gustaw", new ParkingSlot(6, 6, 2, 2027)); //Gustaw has 7 credits
    }
    @AfterEach
    void tearDown() {
        backEnd.clearBookings();
        backEnd.clearCredits();
    }

    @Test
    void makeBooking() {
        //Check valid booking
        assertTrue(backEnd.addBooking("Bao", new ParkingSlot(7, 31, 8, 2027))); //Bao has 1 credit
        assertTrue(backEnd.checkExist(new ParkingSlot(7, 31, 8, 2027)));

        //Check invalid booking - duplicate
        assertFalse(backEnd.addBooking("Gustaw", new ParkingSlot(8, 31, 8, 2027)));
        assertNull(backEnd.getBookingById(8));

        //Check invalid booking - not enough credits
        assertFalse(backEnd.addBooking("Bao", new ParkingSlot(9, 31, 8, 2028)));
        assertNull(backEnd.getBookingById(9));
    }

    @Test
    void checkModifyBooking() {
        //Valid rebook
        assertTrue(backEnd.checkExist(new ParkingSlot(5, 30, 1, 2027)));
        assertTrue(backEnd.modifyBooking("Joey", new ParkingSlot(5, 30, 1, 2027), new ParkingSlot(5, 5, 2, 2028)));
        assertFalse(backEnd.checkExist(new ParkingSlot(5, 30, 1, 2027)));
        assertTrue(backEnd.checkExist(new ParkingSlot(5, 5, 2, 2028)));

        //Invalid rebook
        assertTrue(backEnd.checkExist(new ParkingSlot(1, 31, 9, 2026)));
        assertFalse(backEnd.modifyBooking("Joey", new ParkingSlot(1, 31, 9, 2026), new ParkingSlot(1, 12, 2, 2027)));
        assertTrue(backEnd.checkExist(new ParkingSlot(1, 31, 9, 2026))); //Should still be there
        assertFalse(backEnd.checkExist(new ParkingSlot(1, 31, 9, 2027)));
    }
}
