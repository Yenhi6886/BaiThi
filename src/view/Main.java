package view;

import controller.BankAccountController;
import exception.NotFoundBankAccountException;
import model.*;

import java.util.Scanner;

public class Main {
    private static BankAccountController controller = new BankAccountController();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Thêm mới");
            System.out.println("2. Xóa");
            System.out.println("3. Xem danh sách");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    handleAdd(sc);
                    break;
                case "2":
                    handleDelete(sc);
                    break;
                case "3":
                    controller.showAllAccounts();
                    break;
                case "4":
                    System.out.print("Nhập mã hoặc tên để tìm kiếm: ");
                    controller.search(sc.nextLine());
                    break;
                case "5":
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void handleAdd(Scanner sc) {
        System.out.print("1. Tiết kiệm | 2. Thanh toán: ");
        String type = sc.nextLine();
        try {
            System.out.print("Mã tài khoản: ");
            String code = sc.nextLine().trim();
            if (code.isEmpty()) {
                System.out.println("Mã tài khoản không được để trống.");
                return;
            }
            // Kiểm tra mã tài khoản trùng
            if (controller.isAccountCodeExists(code)) {
                System.out.println("Mã tài khoản đã tồn tại.");
                return;
            }
            System.out.print("Tên chủ tài khoản: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Tên chủ tài khoản không được để trống.");
                return;
            }
            System.out.print("Ngày tạo: ");
            String created = sc.nextLine().trim();
            if (created.isEmpty()) {
                System.out.println("Ngày tạo không được để trống.");
                return;
            }

            if (type.equals("1")) {
                System.out.print("Số tiền gửi: ");
                double amount = Double.parseDouble(sc.nextLine().trim());
                if (amount < 0) {
                    System.out.println("Số tiền gửi phải >= 0.");
                    return;
                }
                System.out.print("Ngày gửi: ");
                String date = sc.nextLine().trim();
                if (date.isEmpty()) {
                    System.out.println("Ngày gửi không được để trống.");
                    return;
                }
                System.out.print("Lãi suất (%): ");
                double rate = Double.parseDouble(sc.nextLine().trim());
                if (rate < 0) {
                    System.out.println("Lãi suất phải >= 0.");
                    return;
                }
                System.out.print("Kỳ hạn (tháng): ");
                int term = Integer.parseInt(sc.nextLine().trim());
                if (term <= 0) {
                    System.out.println("Kỳ hạn phải > 0.");
                    return;
                }
                controller.addAccount(new SavingAccount(0, code, name, created, amount, date, rate, term));
            } else {
                System.out.print("Số thẻ: ");
                String card = sc.nextLine().trim();
                if (card.isEmpty()) {
                    System.out.println("Số thẻ không được để trống.");
                    return;
                }
                System.out.print("Số dư: ");
                double balance = Double.parseDouble(sc.nextLine().trim());
                if (balance < 0) {
                    System.out.println("Số dư phải >= 0.");
                    return;
                }
                controller.addAccount(new PaymentAccount(0, code, name, created, card, balance));
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi nhập số: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi nhập liệu: " + e.getMessage());
        }
    }

    private static void handleDelete(Scanner sc) {
        System.out.print("Nhập mã tài khoản cần xóa: ");
        try {
            controller.deleteAccount(sc.nextLine());
        } catch (NotFoundBankAccountException e) {
            System.out.println(e.getMessage());
        }
    }
}