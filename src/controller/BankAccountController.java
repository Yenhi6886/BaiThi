package controller;

import model.*;
import util.FileUtils;
import exception.NotFoundBankAccountException;

import java.util.*;

public class BankAccountController {
    private static final String FILE_PATH = "data/bank_accounts.csv";
    private List<BankAccount> accounts;

    public BankAccountController() {
        accounts = loadFromFile();
    }

    private List<BankAccount> loadFromFile() {
        List<BankAccount> result = new ArrayList<>();
        List<String> lines = FileUtils.readFile(FILE_PATH);
        for (String line : lines) {
            String[] parts = line.split(",");
            try {
                int id = Integer.parseInt(parts[0]);
                if (parts.length == 8) {
                    result.add(new SavingAccount(id, parts[1], parts[2], parts[3],
                            Double.parseDouble(parts[4]), parts[5],
                            Double.parseDouble(parts[6]), Integer.parseInt(parts[7])));
                } else if (parts.length == 6) {
                    result.add(new PaymentAccount(id, parts[1], parts[2], parts[3],
                            parts[4], Double.parseDouble(parts[5])));
                }
            } catch (Exception e) {
                System.out.println("Dòng lỗi: " + line);
            }
        }
        return result;
    }

    public void showAllAccounts() {
        if (accounts.isEmpty()) System.out.println("Không có tài khoản.");
        else for (BankAccount acc : accounts) System.out.println(acc);
    }

    public void addAccount(BankAccount account) {
        account.id = getNextId();
        accounts.add(account);
        FileUtils.writeFile(FILE_PATH, Collections.singletonList(account.getInfoForCSV()), true);
        System.out.println("Thêm mới thành công!");
    }

    private int getNextId() {
        if (accounts.isEmpty()) return 1;
        int maxId = 0;
        for (BankAccount acc : accounts) {
            if (acc.getId() > maxId) maxId = acc.getId();
        }
        return maxId + 1;
    }

    public void deleteAccount(String accountCode) throws NotFoundBankAccountException {
        BankAccount acc = accounts.stream()
                .filter(a -> a.getAccountCode().equals(accountCode))
                .findFirst()
                .orElseThrow(() -> new NotFoundBankAccountException("Tài khoản không tồn tại."));

        Scanner sc = new Scanner(System.in);
        System.out.print("Bạn có chắc chắn muốn xóa? (Yes/No): ");
        if (sc.nextLine().equalsIgnoreCase("Yes")) {
            accounts.remove(acc);
            List<String> newData = new ArrayList<>();
            for (BankAccount a : accounts) newData.add(a.getInfoForCSV());
            FileUtils.overwriteFile(FILE_PATH, newData);
            System.out.println("Đã xóa thành công!");
        }
    }

    public void search(String keyword) {
        boolean found = false;
        for (BankAccount acc : accounts) {
            if (acc.getAccountCode().contains(keyword) || acc.getOwnerName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(acc);
                found = true;
            }
        }
        if (!found) System.out.println("Không tìm thấy tài khoản nào.");
    }

    public boolean isAccountCodeExists(String code) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountCode().equals(code)) return true;
        }
        return false;
    }
}