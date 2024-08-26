import java.util.InputMismatchException;
import java.util.Scanner;

class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    public Product(int id, String name, int qty, double price) {
        this.productId = id;
        this.productName = name;
        this.quantity = qty;
        this.price = price;
    }
}

class Node {
    Product product;
    Node next;

    public Node(Product product) {
        this.product = product;
        this.next = null;
    }
}

class ProductList {
    Node head;

    public ProductList() {
        head = null;
    }

    public void addProduct(Product product) {
        Node newNode = new Node(product);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Product added successfully!");
    }

    public void modifyProduct(int productId, String newName, int newQty, double newPrice) {
        Node current = head;
        while (current != null) {
            if (current.product.productId == productId) {
                current.product.productName = newName;
                current.product.quantity = newQty;
                current.product.price = newPrice;
                System.out.println("Product modified successfully!");
                return;
            }
            current = current.next;
        }
        System.out.println("Product not found.");
    }

    public void deleteProduct(int productId) {
        if (head == null) {
            System.out.println("Product list is empty.");
            return;
        }

        if (head.product.productId == productId) {
            head = head.next;
            System.out.println("Product deleted successfully!");
            return;
        }

        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.product.productId == productId) {
                prev.next = current.next;
                System.out.println("Product deleted successfully!");
                return;
            }
            prev = current;
            current = current.next;
        }

        System.out.println("Product not found.");
    }

    public void searchProduct(int productId) {
        Node current = head;
        while (current != null) {
            if (current.product.productId == productId) {
                System.out.println("Product found:");
                System.out.println("Product ID: " + current.product.productId);
                System.out.println("Product Name: " + current.product.productName);
                System.out.println("Quantity: " + current.product.quantity);
                System.out.println("Price: " + current.product.price);
                return;
            }
            current = current.next;
        }
        System.out.println("Product not found.");
    }

    public void viewBill() {
        if (head == null) {
            System.out.println("Product list is empty.");
            return;
        }

        double totalBill = 0;
        Node current = head;
        System.out.println("Bill Details:");
        while (current != null) {
            System.out.println("Product ID: " + current.product.productId);
            System.out.println("Product Name: " + current.product.productName);
            System.out.println("Quantity: " + current.product.quantity);
            System.out.println("Price: " + current.product.price);
            totalBill += current.product.price * current.product.quantity;
            current = current.next;
        }
        System.out.println("Total Bill: " + totalBill);
    }
}

public class bill_generator {
    public static void main(String[] args) {
        ProductList productList = new ProductList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. Modify Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Search Product");
            System.out.println("5. View Bill");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); // Consume the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Product ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline
                        System.out.print("Enter Product Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Quantity: ");
                        int qty = scanner.nextInt();
                        System.out.print("Enter Price: ");
                        double price = scanner.nextDouble();
                        productList.addProduct(new Product(id, name, qty, price));
                        System.out.println("Product added successfully!");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter valid values.");
                        scanner.nextLine(); 
                    }
                    break;

                case 2:
                    
                      try {
                        System.out.print("Enter Product ID to Modify: ");
                        int modifyId = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.print("Enter New Product Name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter New Quantity: ");
                        int newQty = scanner.nextInt();
                        System.out.print("Enter New Price: ");
                        double newPrice = scanner.nextDouble();
                        productList.modifyProduct(modifyId, newName, newQty, newPrice);} 
                        catch (InputMismatchException e)
                        {
                        System.out.println("Invalid input. Please enter valid values.");
                        scanner.nextLine(); 
                        }
                    break;


                case 3:
                    try {
                        System.out.print("Enter Product ID to Delete: ");
                        int deleteId = scanner.nextInt();
                        productList.deleteProduct(deleteId);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter valid values.");
                        scanner.nextLine(); 
                    }
                    break;


                case 4:
                      try {
                        System.out.print("Enter Product ID to Search: ");
        int searchId = scanner.nextInt();
        productList.searchProduct(searchId);
    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter valid values.");
                        scanner.nextLine();
                    }
                    break;

            
                case 5:
                    
                      try {
                       productList.viewBill();
                    break;
                      }catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter valid values.");
                        scanner.nextLine();                     }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}