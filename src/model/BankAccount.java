package model;

public abstract class BankAccount {
    public int id;
    protected String accountCode;
    protected String ownerName;
    protected String createdDate;

    public BankAccount(int id, String accountCode, String ownerName, String createdDate) {
        this.id = id;
        this.accountCode = accountCode;
        this.ownerName = ownerName;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public abstract String getInfoForCSV();
    @Override
    public abstract String toString();
}
