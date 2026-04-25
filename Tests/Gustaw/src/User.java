import java.util.HashMap;

public class User {

    String name;
    String address;
    String email;
    String phoneNo;
    String studentNo;

    HashMap<String, Double> balanceLedger = new HashMap<>();

    public User(String name, String address, String email, String phoneNo, String studentNo) {
        setName(name);
        setAddress(address);
        setEmail(email);
        setPhoneNo(phoneNo);
        setStudentNo(studentNo);
    }

    public void setName(String name) {
        if (name != null && name.length() >= 2 && name.length() <= 50) {
            this.name = name;
        }
    }

    public void setAddress(String address) {
        if (address != null && address.length() <= 100) {
            this.address = address;
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email;
        }
    }

    public void setPhoneNo(String phoneNo) {
        if (phoneNo != null && phoneNo.length() >= 7 && phoneNo.length() <= 15) {
            this.phoneNo = phoneNo;
        }
    }

    public void setStudentNo(String studentNo) {
        if (studentNo != null && studentNo.length() == 8) {
            this.studentNo = studentNo;
        }
    }

    public void updateLedger(String token, double newAmount) {
        for (String s : balanceLedger.keySet()) {
            if (s == token) {
                balanceLedger.remove(s);
                balanceLedger.put(token, newAmount);
                return;
            }
        }
        balanceLedger.put(token, newAmount);
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

}