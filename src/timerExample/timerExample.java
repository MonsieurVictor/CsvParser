package timerExample;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    public class timerExample {

        private String[] questions = {
                "How are you?",
                "How is your day?",
                "Will you work tonight?"
        };

        private JLabel questionLabel;
        private ButtonGroup radioGroup;
        private JRadioButton yesRadioButton;
        private JRadioButton noRadioButton;

        private int counter;

        private static final int GAP = 5;

        private Timer timer;

        private ActionListener timerActions = new ActionListener () {
            @Override
            public void actionPerformed ( ActionEvent ae ) {
                ++counter;
                counter %= questions.length;
                questionLabel.setText ( questions [ counter ] );
                if ( counter == questions.length - 1 ) {
                    ( ( Timer ) ae.getSource () ).stop ();
                }
            }
        };

        public timerExample () {
            counter = 0;
        }

        private void displayGUI () {
            JFrame frame = new JFrame ( "Swing Timer Example" );
            frame.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );

            JPanel contentPane = new JPanel ();
            contentPane.setBorder ( BorderFactory.createEmptyBorder (
                    GAP, GAP, GAP, GAP ) );
            contentPane.setLayout ( new BorderLayout ( GAP, GAP ) );

            JPanel labelPanel = new JPanel ();
            questionLabel = new JLabel (
                    questions [ counter ], JLabel.CENTER);
            labelPanel.add ( questionLabel );

            JPanel radioPanel = new JPanel ();
            yesRadioButton = new JRadioButton ( "YES" );
            noRadioButton = new JRadioButton ( "NO" );
            radioGroup = new ButtonGroup ();
            radioGroup.add ( yesRadioButton );
            radioGroup.add ( noRadioButton );
            radioPanel.add ( yesRadioButton );
            radioPanel.add ( noRadioButton );

            contentPane.add ( labelPanel, BorderLayout.CENTER );
            contentPane.add ( radioPanel, BorderLayout.PAGE_END );

            frame.setContentPane ( contentPane );
            frame.pack ();
            frame.setLocationByPlatform ( true );
            frame.setVisible ( true );

            timer = new Timer ( 1000, timerActions );
            timer.start ();
        }

        public static void main ( String[] args ) {
            Runnable runnable = new Runnable () {
                @Override
                public void run () {
                    new timerExample ().displayGUI ();
                }
            };
            EventQueue.invokeLater ( runnable );
        }
    }

