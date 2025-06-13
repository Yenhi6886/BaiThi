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

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public abstract String getInfoForCSV();
    @Override
    public abstract String toString();
}
