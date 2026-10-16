import java.util.Scanner;

public class ATMtest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double balance = 10000;
        int pin = 1234;

        System.out.print("Enter your PIN: ");
        int enteredPin = sc.nextInt();

        if (enteredPin != pin) {
            System.out.println("Invalid PIN!");
            return;
        }

        while (true) {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Current Balance: ₹" + balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double deposit = sc.nextDouble();

                    if (deposit > 0) {
                        balance += deposit;
                        System.out.println("Amount deposited successfully.");
                        System.out.println("New Balance: ₹" + balance);
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdraw = sc.nextDouble();

                    if (withdraw <= 0) {
                        System.out.println("Invalid amount.");
                    } else if (withdraw > balance) {
                        System.out.println("Insufficient balance.");
                    } else {
                        balance -= withdraw;
                        System.out.println("Please collect your cash.");
                        System.out.println("Remaining Balance: ₹" + balance);
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}