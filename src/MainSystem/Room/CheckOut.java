package MainSystem.Room;

import MainSystem.Bill;
import MainSystem.BillDB;
import MainSystem.Customer.CustomerDB;
import MainSystem.GenerateRandom;
import MainSystem.ResizeColumn;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CheckOut extends JFrame{
    private JPanel checkOutPanel;
    private JTextField searchCodeField;
    private JTextField bookCodeField;
    private JTextField employeeIdField;
    private JTextField customerIdField;
    private JTextField roomIdField;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JTextField priceField;
    private JTextField totalDayField;
    private JTextField totalPriceField;
    private JTextField billField;
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JButton checkOutButton;
    private JButton paymentButton;
    private JButton backButton;
    private JTable bookTable;
    private JTable billTable;
    Date date = new Date();
    NumberFormat formatPrice = new DecimalFormat("#,000");

    public CheckOut(){
        initUI();
    }

    private void initUI() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        checkOutField.setText(formatDate.format(date));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        BookTable();
        BillTable();
        add(checkOutPanel);
        setSize(1200, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
//        setResizable(false);
    }

    private void BookTable(){
        String[] column = {"Booking code", "Employee ID", "Customer ID", "Room ID", "Check in", "Check out"};
        BookRoomDB bookRoomDB = new BookRoomDB();
        CustomerDB customerDB = new CustomerDB();
        RoomDB roomDB = new RoomDB();
        BillDB billDB = new BillDB();
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (String s : column) {
            model.addColumn(s);
        }
        bookTable.setModel(model);
        bookTable.setFillsViewportHeight(true);
        new ResizeColumn().resizeColumnsWidth(bookTable);
        bookRoomDB.roomAreUsing();
        bookRoomDB.addBookRoomTable(model);
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                billTable.getSelectionModel().clearSelection();
                int selectRow = bookTable.getSelectedRow();
                bookCodeField.setText(model.getValueAt(selectRow, 0).toString());
                employeeIdField.setText(model.getValueAt(selectRow, 1).toString());
                customerIdField.setText(model.getValueAt(selectRow, 2).toString());
                roomIdField.setText(model.getValueAt(selectRow, 3).toString());
                checkInField.setText(model.getValueAt(selectRow, 4).toString());
                roomDB.getAllRoom();
                double price = roomDB.getRoomPrice(roomIdField.getText());
                priceField.setText(formatPrice.format(price));
                customerDB.customerBookedRoom();
                customerDB.getSomeData(customerIdField.getText(), nameField, phoneNumberField);
            }
        });
        searchCodeField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                bookRoomDB.searchBookingCode(model, searchCodeField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                bookRoomDB.searchBookingCode(model, searchCodeField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                bookRoomDB.searchBookingCode(model, searchCodeField);
            }
        });
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bookCodeField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Choose a transaction");
                } else {
                    // Calculate day
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date1 = LocalDate.parse(checkInField.getText(), dtf);
                    LocalDate date2 = LocalDate.parse(checkOutField.getText(), dtf);
                    long totalDay = ChronoUnit.DAYS.between(date1, date2) == 0 ? 1 : ChronoUnit.DAYS.between(date1, date2);
                    double price = roomDB.getRoomPrice(roomIdField.getText());
                    double totalPrice = price * totalDay;
                    totalDayField.setText(String.valueOf(totalDay));
                    totalPriceField.setText(formatPrice.format(totalPrice));
                    billField.setText(new GenerateRandom().randomBillId(6));
                    bookRoomDB.updateCheckOut(checkOutField.getText(), bookCodeField.getText());
                    model.setValueAt(checkOutField.getText(), bookTable.getSelectedRow(), 5);

                    Bill bill = new Bill();
                    bill.setBillId(billField.getText());
                    bill.setBookingCode(bookCodeField.getText());
                    bill.setEmployeeId(employeeIdField.getText());
                    bill.setCustomerId(customerIdField.getText());
                    bill.setCustomerName(nameField.getText());
                    bill.setCustomerPhone(phoneNumberField.getText());
                    bill.setRoomId(roomIdField.getText());
                    bill.setCheckIn(checkInField.getText());
                    bill.setCheckOut(checkOutField.getText());
                    bill.setTotalDay(totalDay);
                    bill.setTotalPrice(totalPrice);
                    billDB.setBillDB(bill);
                    BillTable();
                }
            }
        });
    }

    private void BillTable(){
        String[] column = {"Bill ID", "Booking code", "Employee ID", "Customer ID", "Name","Mobile Number",
                "Room ID", "Check in", "Check out", "Total day", "Total price", "Status"};
        BillDB billDB = new BillDB();
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (String s : column) {
            model.addColumn(s);
        }
        billTable.setModel(model);
        billTable.setFillsViewportHeight(true);
        new ResizeColumn().resizeColumnsWidth(billTable);
        billDB.getBillDB();
        billDB.addBillTable(model);
        billTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bookTable.getSelectionModel().clearSelection();
            }
        });
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectRow = billTable.getSelectedRow();
                billDB.updateStatus(model.getValueAt(selectRow, 0).toString());
                int choose = JOptionPane.showConfirmDialog(null, "Do you want to print bill", "Select", JOptionPane.YES_NO_OPTION);
                model.setValueAt("Paid", selectRow, 11);
                Bill bill = new Bill();
                bill.setBillId(model.getValueAt(selectRow, 0).toString());
                bill.setBookingCode(model.getValueAt(selectRow, 1).toString());
                bill.setEmployeeId(model.getValueAt(selectRow, 2).toString());
                bill.setCustomerId(model.getValueAt(selectRow, 3).toString());
                bill.setCustomerName(model.getValueAt(selectRow, 4).toString());
                bill.setCustomerPhone(model.getValueAt(selectRow, 5).toString());
                bill.setRoomId(model.getValueAt(selectRow, 6).toString());
                bill.setCheckIn(model.getValueAt(selectRow, 7).toString());
                bill.setCheckOut(model.getValueAt(selectRow, 8).toString());
                bill.setTotalDay(Long.parseLong(model.getValueAt(selectRow, 9).toString()));
                bill.setTotalPrice(Double.parseDouble(model.getValueAt(selectRow, 10).toString().replace(",", "")));
                if (choose == 0){
                    printBill(bill);
                }
            }
        });
    }

    private void printBill(Bill bill){
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        String path = "F:\\JavaProjectS2\\Bill\\";
        Document document = new Document(PageSize.A5, 0, 5, 20, 0);
        try {
            Font header = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
            Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
            PdfWriter.getInstance(document, new FileOutputStream(path + "Bill_" + bill.getBillId() + ".pdf"));
            document.open();
            Paragraph paragraph = new Paragraph("HOTEL BILL", header);
            PdfPTable table = new PdfPTable(2);
            insertCell(table, "Bill ID:  " + bill.getBillId(), Rectangle.ALIGN_LEFT, 5, Rectangle.NO_BORDER, font1);
            insertCell(table, dateFormat.format(date), Rectangle.ALIGN_RIGHT, 0, Rectangle.NO_BORDER, font2);
            insertCell(table, "Booking code:  " + bill.getBookingCode(), Rectangle.ALIGN_LEFT, 0, Rectangle.TOP, font2);
            insertCell(table, "Employee ID:  " + bill.getEmployeeId(), Rectangle.ALIGN_LEFT, 15,  Rectangle.TOP, font2);
            insertCell(table, "Name:  " + bill.getCustomerName(), Rectangle.ALIGN_LEFT, 0,  Rectangle.NO_BORDER, font2);
            insertCell(table, "Check In:  " + bill.getCheckIn(), Rectangle.ALIGN_LEFT, 15,  Rectangle.NO_BORDER, font2);
            insertCell(table, "Mobile Number:  " + bill.getCustomerPhone(), Rectangle.ALIGN_LEFT, 0,  Rectangle.NO_BORDER, font2);
            insertCell(table, "Check Out:  " + bill.getCheckOut(), Rectangle.ALIGN_LEFT, 15,  Rectangle.NO_BORDER, font2);
            insertCell(table, "Room Number:  " + bill.getRoomId(), Rectangle.ALIGN_LEFT, 0,  Rectangle.BOTTOM, font2);
            insertCell(table, "Total Day:  " + bill.getTotalDay(), Rectangle.ALIGN_LEFT, 15,  Rectangle.BOTTOM, font2);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(table);
            Paragraph paragraph2 = new Paragraph();
            PdfPTable table2 = new PdfPTable(2);
            insertCell(table2, "Details", Rectangle.ALIGN_LEFT, 0, Rectangle.NO_BORDER, font1);
            insertCell(table2, "Price", Rectangle.ALIGN_LEFT, 0, Rectangle.NO_BORDER, font1);
            insertCell(table2, "Service", Rectangle.ALIGN_LEFT, 0, Rectangle.NO_BORDER, font2);
            insertCell(table2, "Price", Rectangle.ALIGN_LEFT, 0, Rectangle.NO_BORDER, font2);
            insertCell(table2, "Room", Rectangle.ALIGN_LEFT, 0, Rectangle.BOTTOM, font2);
            insertCell(table2, formatPrice.format(bill.getTotalPrice()), Rectangle.ALIGN_LEFT, 0, Rectangle.BOTTOM, font2);
            insertCell(table2, "Total:", Rectangle.ALIGN_LEFT, 45, Rectangle.NO_BORDER, font1);
            insertCell(table2, formatPrice.format(bill.getTotalPrice()), Rectangle.ALIGN_LEFT, 0, Rectangle.NO_BORDER, font1);
            table2.setWidthPercentage(50);
            table2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            paragraph2.add(table2);
            paragraph2.setIndentationRight(40);
            document.add(paragraph);
            document.add(paragraph2);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        document.close();
    }

    private void insertCell(PdfPTable table, String text, int align, int padding, int border, Font font){
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setHorizontalAlignment(align);
        cell.setBorder(border);
        cell.setPadding(3.5F);
        cell.setPaddingLeft(padding);
        table.addCell(cell);
    }
}
