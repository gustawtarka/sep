public class Transaction {

    int amount = 0;
    String token = "DEFAULT";
    User sender = null;
    User receiver = null;

    public Transaction(int amount, User sender, String token, User receiver) {
        setAmount(amount);
        setSender(sender);
        setToken(token);
        setReceiver(receiver);
    }

    public static Transaction create( int amount, String token, User sender, User receiver) throws Exception {

        if (sender == null || receiver == null) throw new Exception("Sender and receiver not null");


        Transaction tx = new Transaction(amount, sender, token , receiver);
        System.out.println(tx.getToken());
        double senderBalance = sender.balanceLedger.get(tx.getToken());
        if (senderBalance < tx.getAmount()) {
            throw new Exception("Insufficient funds");
        }

        sender.updateLedger(tx.getToken(), senderBalance - tx.getAmount());
        receiver.updateLedger(tx.getToken(), receiver.balanceLedger.getOrDefault(tx.getToken(), 0.0) + tx.getAmount());

        return tx;
    }


    public void setAmount(int amount) {
        if (amount > 0) {
            this.amount = amount;
        }
    }

    public void setToken(String token) {
        if (token != null){
            if(token.length() ==  4 || token.length() == 3) {
                this.token = token;
            }
        }
    }
    public void setSender(User sender) {
        if (sender != null) {
            this.sender = sender;
        }
    }

    public void setReceiver(User receiver) {
        if (receiver != null) {
            this.receiver = receiver;
        }
    }

    public int getAmount() { return amount; }
    public String getToken() { return token; }
    public User getSender() { return sender; }
    public User getReceiver() { return receiver; }
}