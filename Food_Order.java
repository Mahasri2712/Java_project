package Workshpo_project;
import java.util.ArrayList;
import java.util.Scanner;
class FoodItem{
    private String name;
    private double price;
    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return name + " - $" + price;
    }
}
class Order {
    int orderId;
    private ArrayList<FoodItem> items;
    private String status;
    private double totalPrice;
    public Order(int orderId, ArrayList<FoodItem> items) {
        this.orderId = orderId;
        this.items = new ArrayList<>(items);
        this.status = "Order Placed";
        this.totalPrice = calculateTotalPrice();
    }
    private double calculateTotalPrice() {
        double total = 0;
        for (FoodItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
    public void updateStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Items:");
        for (FoodItem item : items) {
            System.out.println("  - " + item);
        }
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Status: " + status);
    }
}
class FoodDeliverySystem {
    private ArrayList<FoodItem> menu;
    private ArrayList<Order> orderHistory;
    private int orderCounter;
    public FoodDeliverySystem() {
        menu = new ArrayList<>();
        orderHistory = new ArrayList<>();
        orderCounter = 1;
        initializeMenu();
    }
    private void initializeMenu() {
        menu.add(new FoodItem("Burger", 5.99));
        menu.add(new FoodItem("Pizza", 8.99));
        menu.add(new FoodItem("Pasta", 7.49));
        menu.add(new FoodItem("Fries", 2.99));
        menu.add(new FoodItem("Soda", 1.99));
    }
    public void displayMenu() {
        System.out.println("\n--- Menu ---");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }
    public void placeOrder(ArrayList<Integer> itemIndexes) {
        ArrayList<FoodItem> selectedItems = new ArrayList<>();
        for (int index : itemIndexes) {
            if (index > 0 && index <= menu.size()) {
                selectedItems.add(menu.get(index - 1));
            } else {
                System.out.println("Invalid menu selection: " + index);
            }
        }
        if (!selectedItems.isEmpty()) {
            Order newOrder = new Order(orderCounter++, selectedItems);
            orderHistory.add(newOrder);
            System.out.println("\nOrder placed successfully!");
            newOrder.displayOrderDetails();
        } else {
            System.out.println("\nNo valid items selected. Order not placed.");
        }
    }
    public void trackOrder(int orderId) {
        for (Order order : orderHistory) {
            if (orderId == order.orderId) {
                order.displayOrderDetails();
            }
            else if(orderId != order.orderId) {
            System.out.println("Order ID not found!");}
        }   
    }
    public void updateOrderStatus(int orderId, String status) {
        for (Order order : orderHistory) {
            if (orderId == order.orderId) {
                order.updateStatus(status);
                System.out.println("Order status updated successfully!");       
            }
            else if(orderId == order.orderId) {
            System.out.println("Order ID not found!");}
        }
        
    }
    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("\nNo orders placed yet.");
        }
        System.out.println("\n--- Order History ---");
        for (Order order : orderHistory) {
            order.displayOrderDetails();
            System.out.println("-----------------------");
        }
    }
}
public class Food_Order {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoodDeliverySystem fds = new FoodDeliverySystem();
        while (true) {
            System.out.println("\n--- Food Delivery System ---");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. Track Order");
            System.out.println("4. Update Order Status (Admin)");
            System.out.println("5. View Order History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: // View Menu
                    fds.displayMenu();
                    break;
                case 2: // Place Order
                    fds.displayMenu();
                    System.out.print("Enter item numbers to order (comma-separated): ");
                    scanner.nextLine(); // Consume newline
                    String[] input = scanner.nextLine().split(",");
                    ArrayList<Integer> itemIndexes = new ArrayList<>();
                    for (String s : input) {
                        itemIndexes.add(Integer.parseInt(s.trim()));
                    }
                    fds.placeOrder(itemIndexes);
                    break;
                case 3: // Track Order
                    System.out.print("Enter Order ID to track: ");
                    int trackOrderId = scanner.nextInt();
                    fds.trackOrder(trackOrderId);
                    break;
                case 4: // Update Order Status
                    System.out.print("Enter Order ID to update: ");
                    int updateOrderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new status (e.g., 'Out for Delivery', 'Delivered'): ");
                    String status = scanner.nextLine();
                    fds.updateOrderStatus(updateOrderId, status);
                    break;
                case 5: // View Order History
                    fds.viewOrderHistory();
                    break;
                case 6: // Exit
                    System.out.println("Exiting the system...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}