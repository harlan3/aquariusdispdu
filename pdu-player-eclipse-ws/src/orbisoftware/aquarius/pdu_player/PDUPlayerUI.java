/*
 *  Aquarius DIS PDU Suite
 *
 *  Copyright (C) 2011 Harlan Murphy
 *  Orbis Software - orbisoftware@gmail.com
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package orbisoftware.aquarius.pdu_player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.UIManager;

public class PDUPlayerUI implements ActionListener, ChangeListener {

   private static PDUPlayerUI instance = null;

   private JTextField ipAddress = null;
   private JTextField port = null;
   private JButton fileSelectButton = null;
   private JButton startButton = null;
   private JButton stopButton = null;
   private String playbackDir = "playback_db";
   private String fileNameDefault = "default_db.man";
   private JTextField fileName = null;
   private JFileChooser fileChooser = null;
   private FileExtensionFilter filter = null;
   private JLabel currentPDULabel = null;
   private JLabel elapsedTimeLabel = null;
   private JSlider pduSlider = null;
   private boolean ignoreChangeEvent = false;

   protected PDUPlayerUI() {

   };

   public static PDUPlayerUI getInstance() {
      if (instance == null) {
         instance = new PDUPlayerUI();
      }
      return instance;
   }

   private void addComponentsToPane(Container pane) {

      PDUPlayerData pduPlayerData = PDUPlayerData.getInstance();

      JLabel label;
      pane.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.weightx = 1.0;
      c.fill = GridBagConstraints.HORIZONTAL;

      label = new JLabel("IP Address:");
      c.gridx = 0;
      c.gridy = 0;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(label, c);

      ipAddress = new JTextField();
      ipAddress.setText(pduPlayerData.getIPAddress());
      c.gridx = 1;
      c.gridy = 0;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(ipAddress, c);

      label = new JLabel("Port:");
      c.gridx = 0;
      c.gridy = 1;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(label, c);

      port = new JTextField();
      port.setText(Integer.toString(pduPlayerData.getPort()));
      c.gridx = 1;
      c.gridy = 1;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(port, c);

      label = new JLabel("File Name:");
      c.gridx = 0;
      c.gridy = 2;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(label, c);

      fileSelectButton = new JButton("File Select");
      c.gridx = 1;
      c.gridy = 2;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(fileSelectButton, c);
      fileSelectButton.setEnabled(true);
      fileSelectButton.addActionListener(this);

      fileName = new JTextField();
      fileName.setText(fileNameDefault);

      fileName.setText(System.getProperty("user.dir")
            + System.getProperty("file.separator") + playbackDir
            + System.getProperty("file.separator") + fileNameDefault);

      c.gridx = 0;
      c.gridy = 3;
      c.gridwidth = 2;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(fileName, c);

      startButton = new JButton("Start");
      c.gridx = 0;
      c.gridy = 4;
      c.gridwidth = 1;
      c.insets = new Insets(20, 10, 10, 10);
      pane.add(startButton, c);
      startButton.setEnabled(true);
      startButton.addActionListener(this);

      stopButton = new JButton("Stop");
      c.gridx = 1;
      c.gridy = 4;
      c.insets = new Insets(20, 10, 10, 10);
      pane.add(stopButton, c);
      stopButton.setEnabled(false);
      stopButton.addActionListener(this);

      label = new JLabel("Current PDU:");
      c.gridx = 0;
      c.gridy = 5;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(label, c);

      currentPDULabel = new JLabel("0");
      c.gridx = 1;
      c.gridy = 5;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(currentPDULabel, c);

      label = new JLabel("Elapsed Time:");
      c.gridx = 0;
      c.gridy = 6;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(label, c);

      elapsedTimeLabel = new JLabel("0");
      c.gridx = 1;
      c.gridy = 6;
      c.insets = new Insets(20, 10, 0, 10);
      pane.add(elapsedTimeLabel, c);

      pduSlider = new JSlider();
      pduSlider.setPaintTicks(true);
      pduSlider.setPaintLabels(true);
      pduSlider.setValue(0);
      c.gridx = 0;
      c.gridy = 7;
      c.gridwidth = 2;
      c.insets = new Insets(20, 10, 10, 10);
      pane.add(pduSlider, c);
      pduSlider.addChangeListener(this);
   }

   public void createAndShowGUI() {
      // Create and set up the window.
      JFrame frame = new JFrame("PDU Player");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      filter = new FileExtensionFilter();

      // Make the file chooser readonly to disallow file renaming
      UIManager.put("FileChooser.readOnly", true);

      // Instantiate file chooser
      fileChooser = new JFileChooser(System.getProperty("user.dir")
            + System.getProperty("file.separator") + playbackDir);

      filter.addExtension("man");
      filter.setDescription("PDU Player Manifest Files");

      fileChooser.setFileFilter(filter);

      // Set up the content pane.
      addComponentsToPane(frame.getContentPane());

      // Load the playback database
      loadPlaybackDB();

      // Display the window.
      frame.setSize(300, 380);
      frame.setVisible(true);
   }

   public void actionPerformed(ActionEvent e) {

      PDUPlayerData pduPlayerData = PDUPlayerData.getInstance();

      if (e.getSource() == startButton) {

         try {
            pduPlayerData.setIPAddress(ipAddress.getText());
            pduPlayerData.setPort(Integer.parseInt(port.getText()));

            pushStartButton();
         } catch (Exception exception) {
            System.out.println("\nCould not open file");
         }
      } else if (e.getSource() == stopButton) {

         pushStopButton();

      } else if (e.getSource() == fileSelectButton) {

         int returnVal = fileChooser.showOpenDialog(null);

         if (returnVal == JFileChooser.APPROVE_OPTION) {

            fileName.setText(fileChooser.getSelectedFile().getPath());
            loadPlaybackDB();
         }
      }
   }

   private void loadPlaybackDB() {

      int numberPackets;

      Manifest manifest = Manifest.getInstance();

      manifest.loadFile(fileName.getText());

      numberPackets = manifest.getNumberPDUs();

      if (numberPackets > 0) {

         // Increase numberPackets until a noneven boundary is encountered.
         // This keeps the right slider label from being omitted.
         while (numberPackets % 4 == 0)
            numberPackets++;

         pduSlider.setMinimum(1);
         pduSlider.setMaximum(numberPackets);
         pduSlider.setLabelTable(null);
         pduSlider.setMajorTickSpacing(numberPackets / 4);
      }
   }

   public void stateChanged(ChangeEvent ce) {

      Manifest manifest = Manifest.getInstance();
      ManifestEntry manifestEntry;
      PDUPlayerData pduPlayerData = PDUPlayerData.getInstance();

      if (ce.getSource() == pduSlider) {

         int currentPDUnumber = pduSlider.getValue() - 1;

         // The slider can exceed the number of PDUs, so must check
         if ((currentPDUnumber < manifest.getNumberPDUs())
               && (!ignoreChangeEvent)) {

            pushStopButton();

            manifestEntry = manifest.getPDU(currentPDUnumber);

            currentPDULabel.setText(Integer.toString(currentPDUnumber + 1));
            elapsedTimeLabel.setText(Long
                  .toString(manifestEntry.packetTimeStamp));

            pduPlayerData.setCurrentPDUnumber(currentPDUnumber);
         }
      } else if (ce.getSource() == SendDatagramThread.class) {

         int currentPDUnumber = pduPlayerData.getCurrentPDUnumber();

         manifestEntry = manifest.getPDU(currentPDUnumber);

         currentPDULabel.setText(Integer.toString(currentPDUnumber + 1));
         elapsedTimeLabel.setText(Long.toString(manifestEntry.packetTimeStamp));

         // The setValue call will generate another change event, so set
         // the ignoreChangeEvent flag to true, since the SendDatagramThread
         // is the controller in this case (not the slider control).
         ignoreChangeEvent = true;
         pduSlider.setValue(currentPDUnumber);
      }

      ignoreChangeEvent = false;
   }

   private void pushStartButton() {

      PDUPlayerData pduPlayerData = PDUPlayerData.getInstance();

      pduPlayerData.setPlayerActive(true);

      startButton.setEnabled(false);
      stopButton.setEnabled(true);
   }

   public void pushStopButton() {

      PDUPlayerData pduPlayerData = PDUPlayerData.getInstance();

      pduPlayerData.setPlayerActive(false);

      startButton.setEnabled(true);
      stopButton.setEnabled(false);

      // Interrupt SendDatagramThread so that it will stop sleeping
      SendDatagramThread.getInstance().interrupt();
   }
}
