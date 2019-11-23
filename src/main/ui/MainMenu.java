package ui;

import exception.TooManyToDosException;
import model.ToDo;
import model.ToDoList;
import network.ReadWeather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainMenu extends JFrame {
    private JFrame frame;
    private JFrame deleteFrame;
    private JButton addToDoButton;
    private JButton addUrgentToDoButton;
    private JButton showToDosAndUrgentToDosButton;
    private JButton saveAndQuitButton;
    private JButton searchToDosByLocationButton;
    private JButton deleteToDoButton;
    private JPanel mainPanel;
    private JTextArea weatherText;
    private JPanel deletePanel;
    private JTextArea textDeleteToDo;
    private ToDoList toDoList = new ToDoList();
    private ReadWeather readWeather;
    private CardLayout card;
    private JPanel cardPane;
    private ReadWeather weather;

    public MainMenu(String title) throws IOException {
        cardPane = new JPanel(new CardLayout());
        JFrame frame = new JFrame(title);
        frame.setVisible(true);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,400);
        cardPane.add(frame, "ToDoList");
        weather = new ReadWeather();
        weatherText.setRows(5);
        weatherText.insert("Weather right now: " + '\n', 0);
        weatherText.append("Location: " + weather.getLocation(ReadWeather.jsonObject) + "\n");
        weatherText.append("Weather: " + weather.getWeather(ReadWeather.jsonObject) + "\n");
        weatherText.append("Description: " + weather.getDescription(ReadWeather.jsonObject) + "\n");
        weatherText.append("Temperature: " + weather.getMinTemp(ReadWeather.jsonObject)
               + " to " + weather.getMaxTemp(ReadWeather.jsonObject) + "\n");

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
                        toDoList.save(ToDoList.TODOSAVELOCATION, ToDoList.URGENTTODOSAVELOCATION,
                                ToDoList.REMOVETODOLOCATION, ToDoList.LOCATIONSAVELOCATION);
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
                deletePanel = new JPanel(new GridLayout(11, 1));
                JLabel heading = new JLabel("Delete To Do");
                deletePanel.add(heading);
                for (ToDo td : toDoList.getToDoList()) {
                    JButton button = new JButton(td.getToDoName());
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            toDoList.getToDoList().remove(td);
                        }
                    });
                    deletePanel.add(button);
                }
                JButton backButton = new JButton("Back");
                deletePanel.add(backButton);

                frame.getContentPane().removeAll();
                frame.repaint();
                frame.revalidate();
                frame.setContentPane(deletePanel);
                frame.repaint();
                frame.revalidate();

                cardPane.add(deletePanel, "Delete ToDo");

                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CardLayout card = (CardLayout) (cardPane.getLayout());    //https://stackoverflow.com/questions/24167805/cardlayout-with-buttons-that-change-the-cards
                        card.show(mainPanel, "ToDoList");
                    }
                });
            }
        });
        weatherText.addContainerListener(new ContainerAdapter() {
        });
        searchToDosByLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {};
            }
        });
    }

    private void switchPane(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.setContentPane(panel);
        frame.repaint();
        frame.revalidate();
    }
}