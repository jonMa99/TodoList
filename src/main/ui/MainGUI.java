package ui;

import exception.TooManyToDosException;
import model.Location;
import model.Search;
import model.ToDo;
import model.ToDoList;
import network.ReadWeather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MainGUI {
    private JPanel cardPanel;
    private JButton addUrgentToDoButton;
    private JButton deleteToDoButton;
    private JButton showToDosAndUrgentToDosButton;
    private JButton searchToDosByLocationButton;
    private JButton saveAndQuitButton;
    private JButton addToDoButton;
    private JPanel mainPanel;
    private JPanel deletePanel;
    private JTextArea weatherText;
    private JLabel weatherIcon;
    private JPanel searchPanel;
    private ToDoList toDoList = new ToDoList();
    private ReadWeather weather;
    private Search search = new Search();

    public MainGUI() throws IOException {
        JFrame frame = new JFrame("ToDoList");
        frame.setVisible(true);
        frame.setContentPane(cardPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        cardPanel.add(mainPanel, "mainPanel");
        CardLayout card = (CardLayout) (cardPanel.getLayout());
        card.show(cardPanel, "mainPanel");
        displayWeather();
        getWeatherPicture();

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
                String listToDo = "";
                int count = 0;
                for (ToDo td : toDoList.getToDoList()) {
                    listToDo = listToDo + (count + 1) + ": " + td.getToDoName()
                            + " --- at location: " + td.getLocation().getLocationName() + "\n";
                    count++;
                }
                JOptionPane.showMessageDialog(null, listToDo, "List of ToDos", JOptionPane.PLAIN_MESSAGE);
            }
        });

        deleteToDoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePanel = new JPanel(new GridLayout(12,0, 0, 5));
                setUpDeletePanel();
                cardPanel.add(deletePanel, "deletePanel");
                CardLayout card = (CardLayout) (cardPanel.getLayout());    //https://stackoverflow.com/questions/24167805/cardlayout-with-buttons-that-change-the-cards
                card.show(cardPanel, "deletePanel");
            }
        });
        weatherText.addContainerListener(new ContainerAdapter() {
        });
        searchToDosByLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPanel = new JPanel(new GridLayout(10,0,0,5));
                Set<Location> locationList = search.getKeysAndMakeList(toDoList);
                HashMap<Location, ArrayList<ToDo>> locations = search.fillHashMap(toDoList);
                for (Location l : locationList) {
                    JButton button = new JButton(l.getLocationName());
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<ToDo> listOfToDo = locations.get(l);
                            String todoString = "";
                            int count = 0;
                            for (ToDo td : listOfToDo) {
                                todoString = todoString + (count + 1) + ": " + td.getToDoName() + "\n";
                            }
                            JOptionPane.showMessageDialog(null, todoString, "ToDos at "
                                    + l.getLocationName(), JOptionPane.PLAIN_MESSAGE);
                        }
                    });
                    searchPanel.add(button);
                }
                JButton backButton = new JButton("Back");
                searchPanel.add(backButton);

                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CardLayout card = (CardLayout) (cardPanel.getLayout());    //https://stackoverflow.com/questions/24167805/cardlayout-with-buttons-that-change-the-cards
                        card.show(cardPanel, "mainPanel");
                    }
                });
                cardPanel.add(searchPanel, "searchPanel");
                CardLayout card = (CardLayout) (cardPanel.getLayout());    //https://stackoverflow.com/questions/24167805/cardlayout-with-buttons-that-change-the-cards
                card.show(cardPanel, "searchPanel");

            }
        });

        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "Really Quit?",
                        "Warning", JOptionPane.YES_NO_OPTION);
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
    }

    private void setUpDeletePanel() {
        JLabel heading = new JLabel("Delete To Do");
        deletePanel.add(heading);
        makeButtonWithActionListener();
        JButton backButton = new JButton("Back");
        deletePanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout card = (CardLayout) (cardPanel.getLayout());    //https://stackoverflow.com/questions/24167805/cardlayout-with-buttons-that-change-the-cards
                card.show(cardPanel, "mainPanel");
            }
        });
    }

    private void makeButtonWithActionListener() {
        for (ToDo td : toDoList.getToDoList()) {
            JButton button = new JButton(td.getToDoName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int n = JOptionPane.showConfirmDialog(null, "Really Delete?",
                            "Warning", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        toDoList.getToDoList().remove(td);
                        refreshDeletePanel();
                    }
                }
            });
            deletePanel.add(button);
        }
    }


    private void refreshDeletePanel() {
        deletePanel.removeAll();
        setUpDeletePanel();
        deletePanel.repaint();
        deletePanel.revalidate();
    }

    private void displayWeather() {
        weather = new ReadWeather();
        weatherText.setRows(5);
        weatherText.insert("Weather right now: " + '\n', 0);
        weatherText.append("Location: " + weather.getLocation(ReadWeather.jsonObject) + "\n");
        weatherText.append("Weather: " + weather.getWeather(ReadWeather.jsonObject) + "\n");
        weatherText.append("Description: " + weather.getDescription(ReadWeather.jsonObject) + "\n");
        weatherText.append("Temperature: " + weather.getMinTemp(ReadWeather.jsonObject)
                + " to " + weather.getMaxTemp(ReadWeather.jsonObject) + "\n");
        weatherText.append("Dress appropriately for the weather!");
    }

    private void getWeatherPicture() {
        ImageIcon clothing;
        String sep = System.getProperty("file.separator");   //CPSC 210 C3 LectureLabStarter
        if (weather.getMaxTemp(ReadWeather.jsonObject) < 10) {
            clothing = new ImageIcon(System.getProperty("user.dir") + sep
                    + "data" + sep + "winterjacket.jpg");
            weatherIcon = new JLabel(clothing);     //CPSC 210 C3 LectureLabStarter
        } else if (weather.getMaxTemp(ReadWeather.jsonObject) > 30) {
            clothing = new ImageIcon(System.getProperty("user.dir") + sep
                    + "data" + sep + "hawaiianshirt.jpg");
            weatherIcon = new JLabel(clothing);     //CPSC 210 C3 LectureLabStarter
        } else {
            clothing = new ImageIcon(System.getProperty("user.dir") + sep
                    + "data" + sep + "sweatshirt.jpg");
            weatherIcon = new JLabel(clothing);     //CPSC 210 C3 LectureLabStarter
        }
    }
}
