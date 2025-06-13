package model;

public class PaymentAccount extends BankAccount {
    private String cardNumber;
    private double balance;

    public PaymentAccount(int id, String accountCode, String ownerName, String createdDate,
                          String cardNumber, double balance) {
        super(id, accountCode, ownerName, createdDate);
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    @Override
    public String getInfoForCSV() {
        return String.format("%d,%s,%s,%s,%s,%.0f", id, accountCode, ownerName, createdDate,
                cardNumber, balance);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Mã: %s | Chủ: %s | Ngày tạo: %s | Số thẻ: %s | Số dư: %.0f",
                id, accountCode, ownerName, createdDate, cardNumber, balance);
    }
}
