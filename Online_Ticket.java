package Workshpo_project;
import java.util.Scanner;

class Ticket {
    private String name;
    private int totalSeats;
    private int availableSeats;
    public Ticket(String name, int totalSeats) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }
    public String getName() {
        return name;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void bookTickets(int numberOfTickets) {
        if (numberOfTickets <= availableSeats) {
            availableSeats -= numberOfTickets;
            System.out.println(numberOfTickets + " tickets booked successfully for " + name + "!");
        } else {
            System.out.println("Not enough tickets available. Only " + availableSeats + " tickets are left.");
        }
    }
    public void cancelTickets(int numberOfTickets) {
        if (numberOfTickets <= (totalSeats - availableSeats)) {
            availableSeats += numberOfTickets;
            System.out.println(numberOfTickets + " tickets canceled successfully for " + name + "!");
        } else {
            System.out.println("You can't cancel more tickets than what was booked.");
        }
    }
    public void showAvailability() {
        System.out.println("Event: " + name + " | Available Seats: " + availableSeats + " / " + totalSeats);
    }
}
class TicketReservationSystem {
    private Ticket event;
    public TicketReservationSystem(Ticket event) {
        this.event = event;
    }
    public void displayEventDetails() {
        event.showAvailability();
    }
    public void bookTickets(int tickets) {
        event.bookTickets(tickets);
    }
    public void cancelTickets(int tickets) {
        event.cancelTickets(tickets);
    }
}
public class Online_Ticket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ticket concert = new Ticket("Rock Concert", 100);
        TicketReservationSystem reservationSystem = new TicketReservationSystem(concert);
        while (true) {
            System.out.println("\n--- Online Ticket Reservation System ---");
            System.out.println("1. View Event Details");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Tickets");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: // View Event Details
                    reservationSystem.displayEventDetails();
                    break;
                case 2: // Book Tickets
                    System.out.print("Enter the number of tickets to book: ");
                    int ticketsToBook = scanner.nextInt();
                    reservationSystem.bookTickets(ticketsToBook);
                    break;
                case 3: // Cancel Tickets
                    System.out.print("Enter the number of tickets to cancel: ");
                    int ticketsToCancel = scanner.nextInt();
                    reservationSystem.cancelTickets(ticketsToCancel);
                    break;
                case 4: // Exit
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

