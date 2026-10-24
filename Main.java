import java.util.ArrayList;
import java.util.Scanner;

class Product {
    String name;
    int quantity;
    double buyingPrice;
    double sellingPrice;

    Product(String name, int quantity, double buyingPrice,
            double sellingPrice) {
        this.name = name;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }
}

public class Main {
    static ArrayList<Product> products = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static double totalRevenue = 0;
    static double totalProfit = 0;

    public static void main(String[] args) {
        products.add(new Product("Maize Flour", 100, 120, 150));
        products.add(new Product("Rice", 80, 160, 200));
        products.add(new Product("Sugar", 60, 140, 180));
        products.add(new Product("Cooking Oil", 50, 250, 300));

        int choice;

        do {
            System.out.println("\n=== SALES AND STOCK MANAGEMENT ===");
            System.out.println("1. View Products and Stock");
            System.out.println("2. Record a Sale");
            System.out.println("3. Add a New Product");
            System.out.println("4. View Sales Summary");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    recordSale();
                    break;
                case 3:
                    addProduct();
                    break;
                case 4:
                    viewSummary();
                    break;
                case 5:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 5);

        input.close();
    }

    static void viewProducts() {
        System.out.println("\n--- AVAILABLE STOCK ---");
        for (Product p : products) {
            System.out.println(p.name + " | Stock: " + p.quantity
                    + " | Selling Price: KSh " + p.sellingPrice);
        }
    }

    static void recordSale() {
        input.nextLine(); // Clear any pending input
        System.out.print("Enter product name: ");
        String name = input.nextLine();

        Product selected = null;
        for (Product p : products) {
            if (p.name.equalsIgnoreCase(name)) {
                selected = p;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter quantity sold: ");
        int quantity = input.nextInt();

        if (quantity <= 0 || quantity > selected.quantity) {
            System.out.println("Invalid quantity or insufficient stock.");
            return;
        }

        double revenue = quantity * selected.sellingPrice;
        double profit = quantity *
                (selected.sellingPrice - selected.buyingPrice);

        selected.quantity -= quantity;
        totalRevenue += revenue;
        totalProfit += profit;

        System.out.println("Sale recorded successfully!");
        System.out.println("Revenue: KSh " + revenue);
        System.out.println("Estimated profit: KSh " + profit);
    }

    static void addProduct() {
        input.nextLine();
        System.out.print("Enter product name: ");
        String name = input.nextLine();

        System.out.print("Enter opening stock: ");
        int quantity = input.nextInt();

        System.out.print("Enter buying price: ");
        double buyingPrice = input.nextDouble();

        System.out.print("Enter selling price: ");
        double sellingPrice = input.nextDouble();

        if (quantity < 0 || buyingPrice < 0 || sellingPrice < 0) {
            System.out.println("Values cannot be negative.");
            return;
        }

        for (Product p : products) {
            if (p.name.equalsIgnoreCase(name)) {
                System.out.println("Product already exists.");
                return;
            }
        }

        products.add(new Product(name, quantity, buyingPrice, sellingPrice));
        System.out.println("Product added successfully!");
    }

    static void viewSummary() {
        System.out.println("\n--- SALES SUMMARY ---");
        System.out.println("Total Revenue: KSh " + totalRevenue);
        System.out.println("Estimated Gross Profit: KSh " + totalProfit);
        System.out.println("Products Registered: " + products.size());
    }
    }
