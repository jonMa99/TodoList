package ui;

import exception.TooManyToDosException;
import jdk.nashorn.internal.scripts.JO;
import model.ToDoList;
import network.ReadWeather;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu {
    private JButton addToDoButton;
    private JButton addUrgentToDoButton;
    private JButton deleteToDoButton;
    private JButton showToDosAndUrgentToDosButton;
    private JButton searchToDosByLocationButton;
    private JButton saveAndQuitButton;
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextArea textWeather;
    private ToDoList toDoList;
    private ReadWeather readWeather;

    public MainMenu() throws IOException {
        toDoList = new ToDoList();
        addToDoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String todo = JOptionPane.showInputDialog("Name of todo");
                try {
                    toDoList.addToDo(todo);
                    String location = JOptionPane.showInputDialog("Type in location (default is Nowhere)");
                    if (location == null) {
                        JOptionPane.showMessageDialog(null, "You have added: " + todo);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "You have added: " + todo + " at location: " + location);
                    }
                } catch (TooManyToDosException error) {
                    JOptionPane.showMessageDialog(null, "Sorry you have too many todos. "
                            + "Can you remove some before adding a new one?");
                }
            }
        });
        addUrgentToDoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String todo = JOptionPane.showInputDialog("Name of urgent todo");
                try {
                    String location = JOptionPane.showInputDialog("Type in location (default is Nowhere)");
                    if (location == null) {
                        toDoList.addUrgentToDo(todo);
                        JOptionPane.showMessageDialog(null, "You have added: " + todo);
                    } else {
                        toDoList.addUrgentToDo(todo, location);
                        JOptionPane.showMessageDialog(null,
                                "You have added: " + todo + " at location: " + location);
                    }
                } catch (TooManyToDosException error) {
                    JOptionPane.showMessageDialog(null, "Sorry you have too many todos. "
                            + "Can you remove some before adding a new one?");
                }
            }
        });
        showToDosAndUrgentToDosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "ToDo");
                JOptionPane.showMessageDialog(null, "ToDo1");
            }
        });
        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    toDoList.save("./data/toDoListoutput.txt", "./data/urgenttoDoListoutput.txt",
                            "./data/removeToDoListoutput.txt");
                    System.exit(0);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Sorry try again!");
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("ToDoList");
        frame.setContentPane(new MainMenu().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
