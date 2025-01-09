package Workshpo_project;
import java.util.*;

class Product {
    private String productId;
    private String name;
    private int stock;
    private double price;
    public Product(String productId, String name, int stock, double price) {
        this.productId = productId;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
    public String getProductId() {
        return productId;
    }
    public String getName() {
        return name;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public double getPrice() {
        return price;
    }
    @Override
    public String toString() {
    	System.out.println(" ProductID\tProduct Name\tStock Quantity\t Price");
        return "  "+productId + " \t\t" + name + "\t\t" + stock + "\t\t " + price;
    }
}
class Supplier {
    private String supplierId;
    private String name;
    private String contact;
    public Supplier(String supplierId, String name, String contact) {
        this.supplierId = supplierId;
        this.name = name;
        this.contact = contact;
    }
    public String getSupplierId() {
        return supplierId;
    }
    public String getName() {
        return name;
    }
    public String getContact() {
        return contact;
    }
    @Override
    public String toString() {
    	System.out.println("SupplierID\tName\tContact");
        return supplierId + "\t\t" + name + "\t" + contact;
    }
}
class Sale {
    private String saleId;
    private String productId;
    private int quantity;
    private double totalPrice;
    private String date;
    public Sale(String saleId, String productId, int quantity, double totalPrice, String date) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
    }
    @Override
    public String toString() {
        return saleId + ": " + productId + " | Quantity: " + quantity + " | Total Price: $" + totalPrice + " | Date: " + date;
    }
}
public class InventoryManagementSystem {
    private static Map<String, Product> inventory = new HashMap<>();
    private static Map<String, Supplier> suppliers = new HashMap<>();
    private static List<Sale> sales = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Inventory Management System ===");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Suppliers");
            System.out.println("3. Sales Management");
            System.out.println("4. Generate Sales Report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    manageSuppliers();
                    break;
                case 3:
                    manageSales();
                    break;
                case 4:
                    generateSalesReport();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Exiting Inventory Management System...");
    }
    private static void manageProducts() {
        boolean managing = true;
        while (managing) {
            System.out.println("\n--- Manage Products ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Remove Product");
            System.out.println("4. List Products");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    listProducts();
                    break;
                case 5:
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Stock Quantity: ");
        int stock = scanner.nextInt();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        Product product = new Product(productId, name, stock, price);
        inventory.put(productId, product);
        System.out.println("Product added successfully.");
    }

    private static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        String productId = scanner.nextLine();
        if (inventory.containsKey(productId)) {
            Product product = inventory.get(productId);
            System.out.print("Enter new stock quantity: ");
            int stock = scanner.nextInt();
            product.setStock(stock);
            System.out.println("Product stock updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }
    private static void removeProduct() {
        System.out.print("Enter Product ID to remove: ");
        String productId = scanner.nextLine();
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void listProducts() {
        System.out.println("\n--- Product List ---");
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
    private static void manageSuppliers() {
        boolean managing = true;
        while (managing) {
            System.out.println("\n--- Manage Suppliers ---");
            System.out.println("1. Add Supplier");
            System.out.println("2. List Suppliers");
            System.out.println("3. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    addSupplier();
                    break;
                case 2:
                    listSuppliers();
                    break;
                case 3:
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void addSupplier() {
        System.out.print("Enter Supplier ID: ");
        String supplierId = scanner.nextLine();
        System.out.print("Enter Supplier Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Supplier Contact: ");
        String contact = scanner.nextLine();
        Supplier supplier = new Supplier(supplierId, name, contact);
        suppliers.put(supplierId, supplier);
        System.out.println("Supplier added successfully.");
    }

    private static void listSuppliers() {
        System.out.println("\n--- Supplier List ---");
        for (Supplier supplier : suppliers.values()) {
            System.out.println(supplier);
        }
    }

    private static void manageSales() {
        System.out.print("Enter Sale ID: ");
        String saleId = scanner.nextLine();
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        if (inventory.containsKey(productId)) {
            Product product = inventory.get(productId);
            System.out.print("Enter Quantity Sold: ");
            int quantity = scanner.nextInt();
            if (product.getStock() >= quantity) {
                double totalPrice = product.getPrice() * quantity;
                product.setStock(product.getStock() - quantity);
                System.out.print("Enter Sale Date (YYYY-MM-DD): ");
                String date = scanner.next();
                Sale sale = new Sale(saleId, productId, quantity, totalPrice, date);
                sales.add(sale);
                System.out.println("Sale recorded successfully.");
            } else {
                System.out.println("Not enough stock available.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void generateSalesReport() {
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        System.out.println("\n--- Sales Report ---");
        for (Sale sale : sales) {
            if (sale.toString().contains(startDate) && sale.toString().contains(endDate)) {
                System.out.println(sale);
            }
        }
    }
}

