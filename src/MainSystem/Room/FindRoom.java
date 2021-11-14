package MainSystem.Room;

import MainSystem.ResizeColumn;
import MainSystem.Room.RoomDB;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindRoom extends JFrame{
    private JPanel searchPanel;
    private JLabel searchroomLabel;
    private JComboBox typeBox;
    private JButton backButton;
    private JTextField searchField;
    private JLabel searchbyLabel;
    private JLabel searchformLabel;
    private JPanel resultPanel;
    private JScrollPane scrollPane;
    private JTable resultTable;

    public FindRoom(){
        initUI();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void initUI() {
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Room ID");
        model.addColumn("Type");
        model.addColumn("Price");
        model.addColumn("Status");
        findRoom(model);
        resultTable.setModel(model);
        new ResizeColumn().resizeColumnsWidth(resultTable);
        resultTable.setFillsViewportHeight(true);
        add(searchPanel);
        setSize(800, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void findRoom(DefaultTableModel model){
        RoomDB findRoom = new RoomDB();
        findRoom.getAllRoom();
        findRoom.addRoomTable(model);

        if (typeBox.getSelectedIndex() == 0){
            searchField.setText("");
            searchField.setEditable(false);
            searchformLabel.setEnabled(false);
            findRoom.searchRoomByType(model);
        }

        typeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeBox.getSelectedIndex() == 0){
                    searchField.setText("");
                    searchField.setEditable(false);
                    searchformLabel.setEnabled(false);
                    findRoom.searchRoomByType(model);
                } else if (typeBox.getSelectedIndex() == 1){
                    searchField.setEditable(true);
                    searchformLabel.setEnabled(true);
                    findRoom.searchRoomByName(model, searchField);
                }
            }
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                findRoom.searchRoomByName(model, searchField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                findRoom.searchRoomByName(model, searchField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                findRoom.searchRoomByName(model, searchField);
            }
        });
    }
}
