package ui;

import exception.TooManyToDosException;
import model.ToDo;
import model.ToDoList;
import network.ReadWeather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class MainMenu implements ActionListener {
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
    private JTextArea textDeleteToDo;
    private ToDoList toDoList = new ToDoList();
    private ReadWeather readWeather;
    private CardLayout card;
    private JPanel cardPane;

    public MainMenu() throws IOException {
//        frame = new JFrame("Main Menu");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,400);
//        mainPanel = new JPanel();
//        deletePanel = new JPanel();
//        deletePanel.setBackground(Color.BLACK);
//        card = new CardLayout();
//        cardPane.setLayout(card);
//        cardPane.add(mainPanel, "Main Menu");
//        cardPane.add(deletePanel, "Delete Menu");
//        frame.add(cardPane);
//        frame.setVisible(true);

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
                for (ToDo td : toDoList.getToDoList()) {
                    JButton button = new JButton(td.getToDoName());
                    deletePanel.add(button);
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


    @Override
    public void actionPerformed(ActionEvent e) {
        card.next(cardPane);
    }
}
