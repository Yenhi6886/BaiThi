package model;

public class SavingAccount extends BankAccount {
    private double savingAmount;
    private String savingDate;
    private double interestRate;
    private int termMonths;

    public SavingAccount(int id, String accountCode, String ownerName, String createdDate,
                         double savingAmount, String savingDate, double interestRate, int termMonths) {
        super(id, accountCode, ownerName, createdDate);
        this.savingAmount = savingAmount;
        this.savingDate = savingDate;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
    }

    @Override
    public String getInfoForCSV() {
        return String.format("%d,%s,%s,%s,%.0f,%s,%.1f,%d", id, accountCode, ownerName, createdDate,
                savingAmount, savingDate, interestRate, termMonths);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Mã: %s | Chủ: %s | Ngày tạo: %s | Số tiền: %.0f | Ngày gửi: %s | Lãi suất: %.1f%% | Kỳ hạn: %d tháng",
                id, accountCode, ownerName, createdDate, savingAmount, savingDate, interestRate, termMonths);
    }
}
