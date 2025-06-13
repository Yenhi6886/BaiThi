package model;

public abstract class BankAccount {
    public int id;
    protected String accountCode; //ma tai khoan
    protected String ownerName; //ten chu so huu
    protected String createdDate; //ngay tao

    public BankAccount() {
    }

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
