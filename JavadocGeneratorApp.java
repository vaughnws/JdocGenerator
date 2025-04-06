import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * An enhanced graphical application for generating Javadoc documentation with HTML5 support.
 * Features an aesthetically pleasing UI, sound feedback, and simplified folder-based processing.
 * 
 * @author JavaDoc Generator
 * @version 2.0
 */
public class JavadocGeneratorApp extends JFrame {
    // UI Components
    private JTextField sourcePathField;
    private JTextField outputDirField;
    private JCheckBox html5Checkbox;
    private JTextArea consoleOutputArea;
    private JButton generateButton;
    private JPanel statusPanel;
    private JLabel statusIcon;
    private JLabel statusLabel;
    
    // Custom colors for the UI
    private final Color PRIMARY_COLOR = new Color(66, 139, 202);
    private final Color SUCCESS_COLOR = new Color(92, 184, 92);
    private final Color ERROR_COLOR = new Color(217, 83, 79);
    private final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private final Color CARD_BACKGROUND = new Color(255, 255, 255);
    private final Color TEXT_COLOR = new Color(33, 37, 41);
    
    // Dimensions
    private final int WINDOW_WIDTH = 650;
    private final int WINDOW_HEIGHT = 800;
    private final int BORDER_RADIUS = 10;
    
    /**
     * Constructs a new enhanced JavadocGeneratorApp.
     */
    public JavadocGeneratorApp() {
        // Set up the JFrame
        setTitle("Vaughn's JavaDoc Generator v2.1");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Initialize UI
        initializeUI();
    }

    /**
     * Initializes the user interface with an aesthetically pleasing design.
     */
    private void initializeUI() {
        // Use a modern layout
        setLayout(new BorderLayout(15, 15));
        
        // Title panel
        JPanel titlePanel = createTitlePanel();
        add(titlePanel, BorderLayout.NORTH);
        
        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        // Form panel (source & output fields)
        JPanel formPanel = createFormPanel();
        contentPanel.add(formPanel, BorderLayout.NORTH);
        
        // Console output area
        JPanel consolePanel = createConsolePanel();
        contentPanel.add(consolePanel, BorderLayout.CENTER);
        
        // Status panel
        statusPanel = createStatusPanel();
        contentPanel.add(statusPanel, BorderLayout.SOUTH);
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    /**
     * Creates the title panel with the application name.
     * 
     * @return the title panel
     */
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(PRIMARY_COLOR);
        titlePanel.setPreferredSize(new Dimension(WINDOW_WIDTH, 70));
        titlePanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Vaughn's JavaDoc Generator v2.1", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        
        return titlePanel;
    }
    
    /**
     * Creates the form panel with input fields.
     * 
     * @return the form panel
     */
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(BACKGROUND_COLOR);
        
        // Source directory panel
        JPanel sourcePanel = new JPanel(new BorderLayout(10, 0));
        sourcePanel.setBackground(CARD_BACKGROUND);
        sourcePanel.setBorder(createRoundedBorder());
        sourcePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        
        JLabel sourceLabel = new JLabel("Source Folder");
        sourceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sourceLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        sourceLabel.setForeground(TEXT_COLOR);
        
        sourcePathField = new JTextField();
        sourcePathField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sourcePathField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JButton sourceBrowseButton = createStyledButton("Browse");
        sourceBrowseButton.addActionListener(e -> browseForDirectory(sourcePathField));
        
        JPanel sourceLabelPanel = new JPanel(new BorderLayout());
        sourceLabelPanel.setBackground(CARD_BACKGROUND);
        sourceLabelPanel.add(sourceLabel, BorderLayout.WEST);
        
        sourcePanel.add(sourceLabelPanel, BorderLayout.NORTH);
        sourcePanel.add(sourcePathField, BorderLayout.CENTER);
        sourcePanel.add(sourceBrowseButton, BorderLayout.EAST);
        
        // Output directory panel
        JPanel outputPanel = new JPanel(new BorderLayout(10, 0));
        outputPanel.setBackground(CARD_BACKGROUND);
        outputPanel.setBorder(createRoundedBorder());
        outputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        
        JLabel outputLabel = new JLabel("Output Folder (Place in your project folder)");
        outputLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        outputLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        outputLabel.setForeground(TEXT_COLOR);
        
        outputDirField = new JTextField("Choose your Path");
        outputDirField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        outputDirField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JButton outputBrowseButton = createStyledButton("Browse");
        outputBrowseButton.addActionListener(e -> browseForDirectory(outputDirField));
        
        JPanel outputLabelPanel = new JPanel(new BorderLayout());
        outputLabelPanel.setBackground(CARD_BACKGROUND);
        outputLabelPanel.add(outputLabel, BorderLayout.WEST);
        
        outputPanel.add(outputLabelPanel, BorderLayout.NORTH);
        outputPanel.add(outputDirField, BorderLayout.CENTER);
        outputPanel.add(outputBrowseButton, BorderLayout.EAST);
        
        // Options panel
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionsPanel.setBackground(BACKGROUND_COLOR);
        
        html5Checkbox = new JCheckBox("Use HTML5 format (as Brian likes)", true);
        html5Checkbox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        html5Checkbox.setBackground(BACKGROUND_COLOR);
        html5Checkbox.setForeground(TEXT_COLOR);
        optionsPanel.add(html5Checkbox);
        
        // Generate button
        generateButton = createStyledButton("Generate JDoc");
        generateButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        generateButton.setBackground(PRIMARY_COLOR);
        generateButton.setForeground(Color.WHITE);
        generateButton.setPreferredSize(new Dimension(200, 50));
        generateButton.addActionListener(this::generateJavadoc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.add(generateButton);
        
        // Add padding to form elements
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(sourcePanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(outputPanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(optionsPanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(buttonPanel);
        formPanel.add(Box.createVerticalStrut(10));
        
        return formPanel;
    }
    
    /**
     * Creates the console output panel.
     * 
     * @return the console panel
     */
    private JPanel createConsolePanel() {
        JPanel consolePanel = new JPanel(new BorderLayout());
        consolePanel.setBackground(CARD_BACKGROUND);
        consolePanel.setBorder(createRoundedBorder());
        
        JLabel consoleLabel = new JLabel("Error Output lol");
        consoleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        consoleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        consoleLabel.setForeground(TEXT_COLOR);
        
        consoleOutputArea = new JTextArea();
        consoleOutputArea.setEditable(false);
        consoleOutputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        consoleOutputArea.setLineWrap(true);
        consoleOutputArea.setWrapStyleWord(true);
        consoleOutputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        consoleOutputArea.setBackground(new Color(250, 250, 250));
        
        JScrollPane scrollPane = new JScrollPane(consoleOutputArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        consolePanel.add(consoleLabel, BorderLayout.NORTH);
        consolePanel.add(scrollPane, BorderLayout.CENTER);
        
        return consolePanel;
    }
    
    /**
     * Creates the status panel for displaying success/failure messages.
     * 
     * @return the status panel
     */
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(CARD_BACKGROUND);
        panel.setBorder(createRoundedBorder());
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        
        statusIcon = new JLabel();
        statusIcon.setHorizontalAlignment(JLabel.CENTER);
        statusIcon.setPreferredSize(new Dimension(50, 50));
        
        statusLabel = new JLabel("Ready to generate Javadoc!");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(TEXT_COLOR);
        
        panel.add(statusIcon, BorderLayout.WEST);
        panel.add(statusLabel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates a styled button with consistent appearance.
     * 
     * @param text the button text
     * @return the styled button
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 35));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
            }
        });
        
        return button;
    }
    
    /**
     * Creates a rounded border for UI elements.
     * 
     * @return the rounded border
     */
    private Border createRoundedBorder() {
        return BorderFactory.createCompoundBorder(
            new EmptyBorder(5, 5, 5, 5),
            BorderFactory.createCompoundBorder(
                new LineBorder(new Color(222, 226, 230), 1, true),
                new EmptyBorder(10, 10, 10, 10)
            )
        );
    }

    /**
     * Opens a file browser to select a directory.
     *
     * @param textField the text field to update with the selected path
     */
    private void browseForDirectory(JTextField textField) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Select Da Folder");
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    /**
     * Generates the Javadoc documentation based on the input parameters.
     *
     * @param e the action event
     */
    private void generateJavadoc(ActionEvent e) {
        String sourcePath = sourcePathField.getText().trim();
        if (sourcePath.isEmpty()) {
            showMessage("Source folder is required", false);
            return;
        }

        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists() || !sourceFile.isDirectory()) {
            showMessage("Source folder does not exist, are you stupid?", false);
            return;
        }

        // Make sure outputDir is effectively final for lambda expressions
        final String outputDir;
        String tempOutputDir = outputDirField.getText().trim();
        if (tempOutputDir.isEmpty()) {
            outputDir = "./javadoc";
            outputDirField.setText(outputDir);
        } else {
            outputDir = tempOutputDir;
        }

        // Create output directory if it doesn't exist
        File outputDirFile = new File(outputDir);
        if (!outputDirFile.exists()) {
            outputDirFile.mkdirs();
        }
        
        // Disable the generate button while processing
        generateButton.setEnabled(false);
        generateButton.setText("Generating...");
        
        // Clear console and update status
        consoleOutputArea.setText("");
        updateStatus("Processing...", null);
        
        // Find all Java files in the source directory
        java.util.List<String> javaFiles = findJavaFiles(sourceFile);
        
        if (javaFiles.isEmpty()) {
            showMessage("No Java files found in the source directory, look again please", false);
            generateButton.setEnabled(true);
            generateButton.setText("Generate JavaDoc");
            return;
        }
        
        // Build the javadoc command
        java.util.List<String> command = new ArrayList<>();
        command.add("javadoc");
        
        // Add HTML5 option if selected
        if (html5Checkbox.isSelected()) {
            command.add("-html5");
        }
        
        // Add output directory
        command.add("-d");
        command.add(outputDir);
        
        // Add some default options for better looking javadoc
        command.add("-windowtitle");
        command.add("Java Documentation");
        
        command.add("-doctitle");
        command.add("API Documentation");
        
        // Add all Java files
        command.addAll(javaFiles);
        
        // Display command in console
        consoleOutputArea.append("Executing command: " + String.join(" ", command) + "\n\n");
        
        // Execute the command
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            
            // Read the output
            new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = process.getInputStream().read(buffer)) != -1) {
                        String output = new String(buffer, 0, length);
                        SwingUtilities.invokeLater(() -> consoleOutputArea.append(output));
                    }
                    
                    int exitCode = process.waitFor();
                    SwingUtilities.invokeLater(() -> {
                        generateButton.setEnabled(true);
                        generateButton.setText("Generate JavaDoc");
                        
                        if (exitCode == 0) {
                            playSuccessSound();
                            showMessage("JavaDoc generated successfully at: " + 
                                    new File(outputDir).getAbsolutePath() + File.separator + "index.html", true);
                        } else {
                            showMessage("JavaDoc generation failed with exit code: " + exitCode, false);
                        }
                    });
                } catch (IOException | InterruptedException ex) {
                    SwingUtilities.invokeLater(() -> {
                        consoleOutputArea.append("\nError: " + ex.getMessage());
                        generateButton.setEnabled(true);
                        generateButton.setText("Generate JavaDoc");
                        showMessage("Error: " + ex.getMessage(), false);
                    });
                }
            }).start();
            
        } catch (IOException ex) {
            consoleOutputArea.append("Error executing command: " + ex.getMessage());
            generateButton.setEnabled(true);
            generateButton.setText("Generate JavaDoc");
            showMessage("Error: " + ex.getMessage(), false);
        }
    }
    
    /**
     * Recursively finds all Java files in a directory.
     *
     * @param directory the directory to search
     * @return a list of Java file paths
     */
    private java.util.List<String> findJavaFiles(File directory) {
        java.util.List<String> files = new ArrayList<>();
        findJavaFilesRecursive(directory, files);
        return files;
    }
    
    /**
     * Helper method to recursively find Java files.
     *
     * @param directory the directory to search
     * @param files the list to add found files to
     */
    private void findJavaFilesRecursive(File directory, java.util.List<String> files) {
        File[] fileList = directory.listFiles();
        if (fileList == null) return;
        
        for (File file : fileList) {
            if (file.isDirectory()) {
                findJavaFilesRecursive(file, files);
            } else if (file.getName().endsWith(".java")) {
                files.add(file.getAbsolutePath());
            }
        }
    }
    
    /**
     * Shows a status message and updates the UI accordingly.
     *
     * @param message the message to display
     * @param success true for success, false for error
     */
    private void showMessage(String message, boolean success) {
        updateStatus(message, success ? createSuccessIcon() : createErrorIcon());
    }
    
    /**
     * Updates the status panel with a message and icon.
     *
     * @param message the message to display
     * @param icon the icon to display (can be null)
     */
    private void updateStatus(String message, Icon icon) {
        statusLabel.setText(message);
        statusIcon.setIcon(icon);
        
        // Change panel background based on status
        if (icon == null) {
            statusPanel.setBackground(CARD_BACKGROUND);
            statusLabel.setForeground(TEXT_COLOR);
        } else if (icon == createSuccessIcon()) {
            statusPanel.setBackground(new Color(240, 255, 240));
            statusLabel.setForeground(SUCCESS_COLOR);
        } else {
            statusPanel.setBackground(new Color(255, 240, 240));
            statusLabel.setForeground(ERROR_COLOR);
        }
    }
    
    /**
     * Creates a success icon (thumbs up).
     *
     * @return the success icon
     */
    private Icon createSuccessIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(SUCCESS_COLOR);
                
                // Draw a thumbs up
                int[] thumbsUpX = {20, 15, 15, 20, 25, 30, 30, 25};
                int[] thumbsUpY = {15, 20, 30, 35, 35, 30, 20, 15};
                g2d.fillPolygon(thumbsUpX, thumbsUpY, thumbsUpX.length);
                
                g2d.fillRect(10, 22, 10, 15);
                
                g2d.dispose();
            }
            
            @Override
            public int getIconWidth() {
                return 40;
            }
            
            @Override
            public int getIconHeight() {
                return 40;
            }
        };
    }
    
    /**
     * Creates an error icon (sad face).
     *
     * @return the error icon
     */
    private Icon createErrorIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(ERROR_COLOR);
                
                // Draw circle
                g2d.drawOval(5, 5, 30, 30);
                
                // Draw sad mouth
                g2d.drawArc(10, 25, 20, 10, 0, 180);
                
                // Draw eyes
                g2d.fillOval(12, 15, 5, 5);
                g2d.fillOval(23, 15, 5, 5);
                
                g2d.dispose();
            }
            
            @Override
            public int getIconWidth() {
                return 40;
            }
            
            @Override
            public int getIconHeight() {
                return 40;
            }
        };
    }
    
    /**
     * Plays a success sound when JavaDoc generation is complete.
     */
    private void playSuccessSound() {
        try {
            // Create a simple beep sound using the Java Sound API
            AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            
            try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
                line.open(format);
                line.start();
                
                // Create a success sound (two ascending tones)
                byte[] buffer = new byte[44100];
                for (int i = 0; i < 5000; i++) {
                    double angle = i / (44100.0 / 440.0) * 2.0 * Math.PI;
                    buffer[i] = (byte) (Math.sin(angle) * 80);
                }
                for (int i = 0; i < 7500; i++) {
                    double angle = i / (44100.0 / 660.0) * 2.0 * Math.PI;
                    buffer[i + 5000] = (byte) (Math.sin(angle) * 80);
                }
                line.write(buffer, 0, 12500);
                line.drain();
            }
        } catch (Exception e) {
            // Silently ignore sound errors
            System.err.println("Could not play success sound: " + e.getMessage());
        }
    }

    /**
     * Main method to start the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Set system look and feel or Nimbus if available
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            
            // Adjust some Nimbus/System defaults for better appearance
            UIManager.put("control", new Color(240, 240, 240));
            UIManager.put("info", new Color(242, 242, 189));
            UIManager.put("nimbusBase", new Color(51, 98, 140));
            UIManager.put("nimbusBlueGrey", new Color(169, 176, 190));
            UIManager.put("nimbusSelectionBackground", new Color(57, 105, 138));
            UIManager.put("text", new Color(0, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            JavadocGeneratorApp app = new JavadocGeneratorApp();
            app.setVisible(true);
        });
    }
}