import LoginForm.Login;
import MainSystem.Room.CheckIn;
import MainSystem.Room.CheckOut;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        new Login().setVisible(true);
//        new MenuBar();
//        new MainForm();
//        new About();
//        new FindRoom();
//        new CheckIn().setVisible(true);
//        new CustomerInfo();
        new CheckOut().setVisible(true);
    }
}
