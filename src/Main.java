import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main  {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<Book>();
        Scanner sc = new Scanner(System.in);
        String msg = """
                Chương trình quản lý sách
                    1. Thêm 1 cuốn sách
                    2. Xoá 1 cuốn sách
                    3. Thay đổi sách
                    4. Xuất thông tin
                    5. Tìm sách lập trình
                    6. Lấy sách tối đa theo giá
                    7. Tìm kiếm theo tác giả
                    0. Thoát
                    Chọn chức năng:""";
        int chon = 0;
        do {
            System.out.printf(msg);
            chon = sc.nextInt();
            switch (chon) {
                case 1 -> {
                    Book b = new Book();
                    b.input();
                    listBook.add(b);
                }
                case 2 -> {
                    System.out.println("Nhập mã sách cần xoá: ");
                    int bookid = sc.nextInt();
                    Book bookToRemove = listBook.stream()
                            .filter(b -> b.getId() == bookid)
                            .findFirst()
                            .orElseThrow();
                    listBook.remove(bookToRemove);
                    System.out.print("Đã xoá sách với mã: " + bookid);
                }
                case 3 -> {
                    System.out.print("Nhập mã sách cần thay đổi: ");
                    int bookid = sc.nextInt();
                    Book bookToUpdate = listBook.stream()
                            .filter(b -> b.getId() == bookid)
                            .findFirst()
                            .orElseThrow();
                    System.out.println("Nhập thông tin mới cho sách:");
                    bookToUpdate.input();
                }
                case 4 -> {
                    System.out.print("\n Xuất thông tin danh sách:");
                    listBook.forEach(b -> b.output());
                }
                case 5 -> {
                    System.out.print("\nDanh sách sách lập trình:");
                    var result = listBook.stream()
                            .filter(b -> b.getTitle().toLowerCase().contains("lập trình"))
                            .toList();
                    if (result.isEmpty()) {
                        System.out.println("\nKhông có sách lập trình.");
                    } else {
                        result.forEach(Book::output);
                    }
                }
                case 6 -> {
                    System.out.print("\nSách có giá cao nhất:");
                    long maxPrice = listBook.stream()
                            .mapToLong(Book::getPrice)
                            .max()
                            .orElseThrow();
                    listBook.stream()
                            .filter(b -> b.getPrice() == maxPrice)
                            .limit(1)
                            .forEach(b -> b.output());
                }
                case 7 -> {
                    System.out.print("Nhập tên tác giả cần tìm: ");
                    sc.nextLine(); // consume newline
                    String authorName = sc.nextLine();

                    Set<Book> result = listBook.stream()
                            .filter(b -> b.getAuthor().equalsIgnoreCase(authorName))
                            .collect(Collectors.toSet());

                    if (result.isEmpty()) {
                        System.out.println("Không tìm thấy sách của tác giả: " + authorName);
                    } else {
                        System.out.println("Danh sách sách của tác giả " + authorName + ":");
                        result.forEach(Book::output);
                    }
                }
            }
        } while (chon != 0);
    }
}