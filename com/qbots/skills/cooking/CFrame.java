package com.qbots.skills.cooking;
import com.qbots.skills.cooking.areas.Catherby;
import com.qbots.skills.cooking.areas.Den;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CFrame extends JFrame {

    private JPanel contentPane;

    public CFrame(final Main main) {
        setTitle("Excobot Cooker");
        setType(Type.UTILITY);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 251, 130);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSelectOne = new JLabel("Select One:");
        lblSelectOne.setBounds(26, 11, 71, 14);
        contentPane.add(lblSelectOne);

        final JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Shrimp", "Anchovies", "Trout", "Salmon", "Tuna", "Lobster", "Swordfish", "Shark", "Monkfish", "Rays"}));
        comboBox.setBounds(96, 8, 105, 20);
        contentPane.add(comboBox);

        JLabel lblArea = new JLabel("Area:");
        lblArea.setBounds(26, 36, 46, 14);
        contentPane.add(lblArea);

        final JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Catherby", "Rogues Den"}));
        comboBox_1.setBounds(96, 33, 105, 20);
        contentPane.add(comboBox_1);

        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                main.utils.food_name = "Raw "+comboBox.getSelectedItem().toString().toLowerCase();
                switch (comboBox_1.getSelectedIndex()) {
                    case 0:
                        main.utils.fire_name = "Range";
                        main.area = new Catherby(main.context());
                        break;
                    case 1:
                        main.utils.fire_name = "Fire";
                        main.area = new Den();

                }
                dispose();
            }
        });
        btnStart.setBounds(26, 61, 89, 23);
        contentPane.add(btnStart);
    }
}
