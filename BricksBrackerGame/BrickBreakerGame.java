import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;
import java.lang.reflect.Method;

public class BrickBreakerGame extends JPanel implements ActionListener, KeyListener {

 // Constants for game dimensions and sizes
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BALL_DIAMETER = 20;
    private static final int BRICK_WIDTH = 60;
    private static final int BRICK_HEIGHT = 30;
    private static final int PADDLE_SPEED = 9;
    private static final int INITIAL_BALL_SPEED = 5;
    private static final int MAX_LEVEL = 3;
    private static final int BRICK_INCREASE_PER_LEVEL = 20; // Additional bricks per level
    private static final double SPEED_INCREASE_PERCENTAGE = 0.4; // Speed increase percentage per level


// Game state variables
    private int currentLevel = 1;
    private int score = 0;
    private int lives = 3;
    private int paddleX;
    private int ballX, ballY, ballDX, ballDY;
    private boolean playing = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private Rectangle paddle;
    private Rectangle[] bricks;
    private boolean ballReleased = false;

 // Timer for game loop
    private Timer timer;

 // Buttons and combo box for UI 
    private JButton startButton;
    private JButton restartButton;
    private JButton pauseButton;
    private JButton resumeButton;
    private JComboBox<String> levelComboBox;

// Audio clips for sound effects and background music
    private Clip brickSound;
    private Clip paddleSound;
    private Clip collisionSound;
    private Clip gameOverSound;
    private Clip gameWinSound;
    private Clip backgroundMusic;
 
// Display instructions to the user
    private static void showInstructionDialog() {
        String message = "Welcome to Brick Breaker Game!\n\n" +
                         "Instructions:\n" +
                         "- Use left and right arrow keys to move the paddle.\n" +
                         "- Press spacebar to release the ball.\n" +
                         "- Break all the bricks to complete each level.\n" +
                         "- You have three lives. Lose a life when the ball falls below the paddle.\n" +
                         "- Complete all levels to win the game!\n\n" +
                         "Press OK to start the game.";
    
        JOptionPane.showMessageDialog(null, message, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

// Constructor to set up the game panel and initialize components
    public BrickBreakerGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        // Create level selection menu
        String[] levels = new String[MAX_LEVEL];
        for (int i = 0; i < MAX_LEVEL; i++) {
            levels[i] = "Level " + (i + 1);
        }
        levelComboBox = new JComboBox<>(levels);

        // Create start button
        startButton = new JButton("Start");
        startButton.addActionListener(this);

        // Create restart button
        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        restartButton.setVisible(false); // Initially hide restart button

        // Create pause button
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(this);
        pauseButton.setEnabled(false); // Initially disable pause button
        pauseButton.setVisible(false); // Initially hide pause button

        // Create resume button
        resumeButton = new JButton("Resume");
        resumeButton.addActionListener(this);
        resumeButton.setEnabled(false); // Initially disable resume button
        resumeButton.setVisible(false); // Initially hide resume button

        // Add components to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(levelComboBox);
        buttonPanel.add(startButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resumeButton);

        // Add button panel to the main panel
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize game components
        initGame();
        loadSounds(); // Load sound effects

        playBackgroundMusic();
    }

    private void initGame() {
        paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;
        resetBall();
        paddle = new Rectangle(paddleX, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Initialize timer for game loop
        timer = new Timer(20, this);
    }

// Reset ball position to start a new level
   private void resetBall() {
    ballX = paddleX + PADDLE_WIDTH / 2 - BALL_DIAMETER / 2;
    ballY = HEIGHT - PADDLE_HEIGHT - BALL_DIAMETER - 1;
    ballDX = INITIAL_BALL_SPEED + currentLevel; // Set initial ball speed along x-axis
    ballDY = -(INITIAL_BALL_SPEED + currentLevel); // Set initial ball speed along y-axis (negative to move upward)
}

    
// Load game sound effects and background music
    private void loadSounds() {
        try {
            // Load audio streams for sound effects
            AudioInputStream brickStream = AudioSystem.getAudioInputStream(new File("brick.wav"));
            AudioInputStream paddleStream = AudioSystem.getAudioInputStream(new File("paddle.wav"));
            AudioInputStream collisionStream = AudioSystem.getAudioInputStream(new File("wall.wav"));
            AudioInputStream gameOverStream = AudioSystem.getAudioInputStream(new File("powerup.wav"));
            AudioInputStream gameWinStream = AudioSystem.getAudioInputStream(new File("win.wav"));

            // Open audio clips for sound effects
            brickSound = AudioSystem.getClip();
            brickSound.open(brickStream);

            paddleSound = AudioSystem.getClip();
            paddleSound.open(paddleStream);

            collisionSound = AudioSystem.getClip();
            collisionSound.open(collisionStream);

            gameOverSound = AudioSystem.getClip();
            gameOverSound.open(gameOverStream);

            gameWinSound = AudioSystem.getClip();
            gameWinSound.open(gameWinStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Play background music continuously during gameplay
    private void playBackgroundMusic() {
        try {
            // Load background music file
            File file = new File("bg.wav");
            if (!file.exists()) {
                System.out.println("Background music file not found: " + file.getAbsolutePath());
                return;
            }
    
            // Open and loop background music clip
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Loop the background music continuously
        } catch (Exception e) {
            System.out.println("Error loading background music:");
            e.printStackTrace();
        }
    }
    
 
    // Method to paint game components on the panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (playing) {
            // Draw paddle
            g.setColor(Color.WHITE);
            g.fillRect(paddleX, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);

            // Draw ball
            g.setColor(Color.RED);
            g.fillOval(ballX, ballY, BALL_DIAMETER, BALL_DIAMETER);

            // Draw bricks with different colors
            Color[] brickColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
                    Color.MAGENTA, Color.CYAN, Color.PINK, Color.LIGHT_GRAY, Color.WHITE};
            for (int i = 0; i < bricks.length; i++) {
                if (bricks[i] != null) {
                    g.setColor(brickColors[i % brickColors.length]); // Use a cyclic color pattern
                    g.fillRect(bricks[i].x, bricks[i].y, bricks[i].width, bricks[i].height);
                }
            }

            // Draw level, score, and lives
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Level: " + currentLevel, 20, 30);
            g.drawString("Score: " + score, 150, 30);
            g.drawString("Lives: " + lives, WIDTH - 100, 30);
        } else {
            // Display start menu or game over message
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            if (lives <= 0) {
                g.drawString("Game Over! Final Score: " + score, WIDTH / 2 - 200, HEIGHT / 2 - 50);
            } else if (currentLevel > MAX_LEVEL) {
                g.drawString("Congratulations! You completed all levels!", WIDTH / 2 - 300, HEIGHT / 2 - 50);
            } else {
                g.drawString("Brick Breaker Game - Level " + currentLevel, WIDTH / 2 - 250, HEIGHT / 2 - 50);
            }
        }
    }

     // Method to handle game logic and update game state
    public void actionPerformed(ActionEvent e) {
         // Handle button actions and game loop updates
        if (e.getSource() == startButton && !playing) {
            // Start the game only when "Start" button is clicked and game not already started
            int selectedLevelIndex = levelComboBox.getSelectedIndex();
              currentLevel = selectedLevelIndex + 1;
            if (selectedLevelIndex == 0) {
                playing = true;
                startButton.setVisible(false); // Hide start button when game starts
                levelComboBox.setVisible(false); // Hide level selection menu
                restartButton.setVisible(false); // Hide restart button when game starts
                pauseButton.setVisible(false); // Initially hide pause button
                resumeButton.setVisible(false); // Initially hide resume button
                currentLevel = 1; // Reset to Level 1
                startLevel(currentLevel);
                resetBall(); // Reset ball position for the new level
                timer.start();
                requestFocus(); // Set focus back to game panel for key events
            } else {
                JOptionPane.showMessageDialog(this, "Please start from Level 1.");
            }
        } else if (e.getSource() == restartButton) {
            // Restart the game
            playing = true;
            startButton.setVisible(false); // Hide start button
            levelComboBox.setVisible(false); // Hide level selection menu
            restartButton.setVisible(false); // Hide restart button
            pauseButton.setVisible(false); // Initially hide pause button
            resumeButton.setVisible(false); // Initially hide resume button
            currentLevel = 1; // Reset to Level 1
            startLevel(currentLevel);
            score = 0;
            lives = 3;
            resetBall(); // Reset ball position
            timer.start();
            requestFocus();
        } else if (e.getSource() == pauseButton && playing) {
            // Pause the game
            timer.stop();
            pauseButton.setVisible(false); // Hide pause button
            resumeButton.setVisible(true); // Show resume button
        } else if (e.getSource() == resumeButton && playing) {
            // Resume the game
            timer.start();
            pauseButton.setVisible(true); // Show pause button
            resumeButton.setVisible(false); // Hide resume button
        } else if (playing) {
            updateGame();
            repaint();
        }
        
        // Check for level completion
        if (playing && isLevelComplete()) {
            if (currentLevel < MAX_LEVEL) {
                currentLevel++;
                startLevel(currentLevel);
                resetBall();
                requestFocus();
            } else {
                // All levels completed
                playing = false;
                levelComboBox.setVisible(true); // Show level selection menu
                startButton.setVisible(true); // Show start button
                restartButton.setVisible(true); // Show restart button
                pauseButton.setEnabled(false); // Disable pause button
                timer.stop();
            }
        }
    }
    
    private boolean isLevelComplete() {
        // Check if all bricks are destroyed
        for (Rectangle brick : bricks) {
            if (brick != null) {
                return false; // Brick found, level not complete
            }
        }
        return true; // All bricks destroyed, level complete
    }
  


    private void updateGame() {
        if (ballReleased) {
            // Update the game only if the ball has been released
            if (leftPressed && paddleX > 0) {
                paddleX -= PADDLE_SPEED;
            }
            if (rightPressed && paddleX < WIDTH - PADDLE_WIDTH) {
                paddleX += PADDLE_SPEED;
            }
    
            ballX += ballDX;
            ballY += ballDY;
    
            Rectangle ballRect = new Rectangle(ballX, ballY, BALL_DIAMETER, BALL_DIAMETER);
            Rectangle paddleRect = new Rectangle(paddleX, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
    
            if (ballRect.intersects(paddleRect)) {
                ballDY = -ballDY;
                playPaddleSound(); // Play paddle hit sound
            }
    
            // Handle ball-wall collisions
            if (ballX <= 0 || ballX >= WIDTH - BALL_DIAMETER) {
                ballDX = -ballDX;
                playCollisionSound(); // Play wall collision sound
            }
            if (ballY <= 0) {
                ballDY = -ballDY;
                playCollisionSound(); // Play wall collision sound
            }
    
            // Handle brick collisions
            for (int i = 0; i < bricks.length; i++) {
                if (bricks[i] != null && bricks[i].intersects(ballRect)) {
                    bricks[i] = null;
                    ballDY = -ballDY;
                    score += 10;
                    playBrickSound(); // Play brick hit sound
                }
            }
    
            // Check if ball falls below the paddle
            if (ballY >= HEIGHT) {
                lives--;
                if (lives > 0) {
                    resetBall();
                    ballReleased = false; // Reset ballReleased to false when lives are lost
                } else {
                    gameOver();
                    playGameOverSound(); // Play game over sound
                }
            }
        }
    
        repaint(); // Repaint the game panel
    }
    
       // Start a new game with the selected level
       private void startLevel(int level) {
        // Reset ball position before starting the level
        resetBall();
        ballReleased = false; // Reset ballReleased to false for each level
    
        // Adjust brick count and ball speed based on the selected level
        int numBricks = 30 + (level - 1) * 20;
        int ballSpeed = INITIAL_BALL_SPEED + (level - 1); // Increase ball speed with each level
        
        // Initialize bricks for the specified level
        bricks = new Rectangle[numBricks];
        int brickX = 0;
        int brickY = 50;
        for (int i = 0; i < numBricks; i++) {
            bricks[i] = new Rectangle(brickX, brickY, 60, 30); // Adjust brick size as needed
            brickX += 60 + 5; // Adjust brick spacing as needed
            if (brickX + 60 > WIDTH) {
                brickX = 0;
                brickY += 30 + 5; // Adjust brick spacing as needed
            }
        }
        
        // Set ball speed for the level
        ballDX = ballSpeed;
        ballDY = -ballSpeed; // Move upward
        playing = true;
        timer.start(); // Start the game loop
    }
    
    // End the game and show appropriate message
    private void gameOver() {
        playing = false;
        timer.stop();
        levelComboBox.setVisible(true); // Show level selection menu
        startButton.setVisible(true); // Show start button
        restartButton.setVisible(true); // Show restart button
        pauseButton.setEnabled(false); // Disable pause button
    }
// Key press event handler
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
            ballReleased = true; // Release the ball when player moves the paddle
        } else if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }else if (key == KeyEvent.VK_SPACE) {
            // Release the ball when spacebar is pressed    
            ballReleased = true; // Release the ball when player moves the paddle
        } else if (key == KeyEvent.VK_P && playing) {
            // Pause the game when 'P' key is pressed and game is currently playing
            pauseGame();
        } else if (key == KeyEvent.VK_R && playing) {
            // Resume the game when 'R' key is pressed and game is currently playing
            resumeGame();
        }
    }
    
    private void pauseGame() {
        if (timer.isRunning()) {
            timer.stop();
            pauseButton.setText("Resume");
        }
    }
    
    private void resumeGame() {
        if (!timer.isRunning()) {
            timer.start();
            pauseButton.setText("Pause");
        }
    }
    
// Key release event handler
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
 // Key typed event handler (not used in this game)
    public void keyTyped(KeyEvent e) {}
// Play sound effect for hitting a brick
    private void playBrickSound() {
        if (brickSound != null) {
            brickSound.setFramePosition(0);
            brickSound.start();
        }
    }
 // Play sound effect for hitting the paddle
    private void playPaddleSound() {
        if (paddleSound != null) {
            paddleSound.setFramePosition(0);
            paddleSound.start();
        }
    }
// Play sound effect for wall collisions
    private void playCollisionSound() {
        if (collisionSound != null) {
            collisionSound.setFramePosition(0);
            collisionSound.start();
        }
    }
// Play game over sound effect
    private void playGameOverSound() {
        if (gameOverSound != null) {
            gameOverSound.setFramePosition(0);
            gameOverSound.start();
        }
    }
    // Main method to create and run the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Brick Breaker Game");
            BrickBreakerGame game = new BrickBreakerGame();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
         // Display instruction dialog
         showInstructionDialog();
    }
}

