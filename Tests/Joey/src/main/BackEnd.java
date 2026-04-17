import java.util.ArrayList;
import java.util.HashMap;

public class BackEnd {
    private ArrayList <ParkingSlot> bookings=new ArrayList();
    private HashMap <String, ArrayList <Integer>> parkingSlots=new HashMap <>();
    private HashMap <String, Integer> userCredit=new HashMap();

    public void setCredit(String name, int num)
    {
        userCredit.put(name, num);
    }

    private boolean checkCredit(String name)
    {
        if (!userCredit.containsKey(name))
            setCredit(name, 10); //Initial Credit
        return userCredit.get(name)>=3;
    }

    public boolean addBooking(String name, ParkingSlot booking) {
        if (!parkingSlots.containsKey(name))
            parkingSlots.put(name, new ArrayList <>());
        if (!checkExist(booking) && checkCredit(name)) {
            setCredit(name, userCredit.get(name)-3);
            bookings.add(booking);
            parkingSlots.get(name).add(booking.id);
            return true;
        }
        return false;
    }

    public ParkingSlot getBookingById(int id) {
        for (ParkingSlot booking : bookings) {
            if (booking.id == id)
                return booking;
        }
        return null;
    }

    private void removeBooking(ParkingSlot booking) {
        for (ParkingSlot book : bookings) {
            if (book.id==booking.id && book.day==booking.day &&  book.month==booking.month && book.year==booking.year) {
                bookings.remove(book);
                return;
            }
        }
    }

    public boolean modifyBooking(String name, ParkingSlot oldBooking, ParkingSlot newBooking) {
        if (!parkingSlots.containsKey(name) || !parkingSlots.get(name).contains(oldBooking.id))
            return false;
        if (checkExist(oldBooking) && !checkExist(newBooking)) {
            removeBooking(oldBooking);
            bookings.add(newBooking);
            parkingSlots.get(name).add(newBooking.id);
            for (Integer i : parkingSlots.get(name)) {
                if (i == oldBooking.id) {
                    parkingSlots.get(name).remove(i);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public boolean checkExist(ParkingSlot booking) {
        for  (ParkingSlot slot : bookings) {
            if (slot.day == booking.day && slot.month == booking.month && slot.year == booking.year)
                return true;
        }
        return false;
    }

    public void clearBookings() {
        bookings.clear();
        parkingSlots.clear();
    }

    public void clearCredits() {
        userCredit.clear();
    }
}
