package ui;

import exception.TooManyToDosException;
import model.ToDo;
import model.ToDoList;
import network.ReadWeather;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu {
    private JFrame frame;
    private JFrame deleteFrame;
    private JButton addToDoButton;
    private JButton addUrgentToDoButton;
    private JButton showToDosAndUrgentToDosButton;
    private JButton saveAndQuitButton;
    private JButton searchToDosByLocationButton;
    private JButton deleteToDoButton;
    private JPanel mainPanel;
    private JPanel deletePanel;
    private JLabel title;
    private ToDoList toDoList;
    private ReadWeather readWeather;

    public MainMenu() throws IOException {
        toDoList = new ToDoList();
        addToDoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String todo = JOptionPane.showInputDialog("Name of todo");
                try {
                    String location = JOptionPane.showInputDialog("Type in location (default is Nowhere)");
                    if (location == null) {
                        toDoList.addToDo(todo);
                        JOptionPane.showMessageDialog(null, "You have added: " + todo);
                    } else {
                        toDoList.addToDo(todo, location);
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
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showConfirmDialog(null, "Really Quit?", "Warning", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    try {
                        toDoList.save("./data/toDoListoutput.txt", "./data/urgenttoDoListoutput.txt",
                                "./data/removeToDoListoutput.txt");
                        System.exit(0);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Sorry try again!");
                    }
                }
            }
        });
        deleteToDoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFrame = new JFrame("Delete ToDo");
                try {
                    deleteFrame.setContentPane(new MainMenu().deletePanel);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                deleteFrame.pack();
                deleteFrame.setVisible(true);
                for (ToDo td : toDoList.getToDoList()) {
                    JButton button = new JButton(td.getToDoName());
                    deleteFrame.add(button);
                }
            }
        });
    }

    public class DeletePanel {

    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("ToDoList");
        frame.setContentPane(new MainMenu().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
