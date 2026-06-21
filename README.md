#  Lost Kitten

A 2D tile-based adventure game developed in Java, where the player controls **Mitzi**, a curious kitten trying to find her way back home after getting lost in the city.

The game combines exploration, obstacle avoidance, enemy AI, database persistence, and multiple software engineering concepts such as Object-Oriented Programming, Design Patterns, and SQLite integration.

---

##  Story

Mitzi is a playful kitten living in the busy city of Riverstone. One day, she accidentally escapes from home and gets lost while exploring unfamiliar places.

To return safely, she must overcome several challenges, avoid dangers, collect resources, and navigate through different environments until she finally reaches home.

---

##  Features

### Multi-Level Adventure

The game contains three unique levels:

#### Level 1 – Maze Challenge

* Explore a maze filled with obstacles.
* Collect fish to earn points.
* Reach 100 points before time runs out.
* Fog of War system limits visibility and increases difficulty.

#### Level 2 – Dog Park

* Avoid patrolling dogs.
* Hide inside bushes.
* Distract enemies using a ball.
* Enemy detection system with visibility radius.

#### Level 3 – City Crossing

* Cross busy roads filled with moving cars.
* Avoid trains on railway tracks.
* Checkpoint system saves progress automatically.
* Reach home to complete the game.

---

## Artificial Intelligence

Enemy dogs use simple AI behaviors:

* Patrol predefined routes.
* Detect the player inside a visibility range.
* Trigger events when the player remains visible for too long.
* React to distractions created by the player.

---

## Software Architecture

The project follows Object-Oriented Programming principles and uses multiple design patterns.

### Design Patterns

#### Singleton

Used by the AudioPlayer class to ensure a single audio manager instance throughout the game.

#### Factory Method

Used for dynamic creation of enemies and NPC entities.

#### Observer

Used for game events such as:

* Damage
* Score updates
* Life management
* Collision notifications

#### Flyweight

Used for efficient tile and sprite reuse, reducing memory consumption.

---

##  Database System

The game uses SQLite through JDBC.

Features:

* Save / Load functionality
* Persistent player progress
* Checkpoint storage
* Leaderboard system
* Saved settings (volume, preferences)

Stored information:

* Player name
* Current level
* Score
* Remaining lives
* Collected fish
* Checkpoint position

---

## Graphics & Assets

Custom pixel-art assets created using:

* GraphicsGale
* Piskel

Includes:

* Character animations
* Enemy sprites
* Tile maps
* UI components
* Game menus

---

## ⚙ Technologies Used

* Java
* Object-Oriented Programming (OOP)
* SQLite
* JDBC
* Git & GitHub
* IntelliJ IDEA

---

##  Gameplay Controls

| Key   | Action           |
| ----- | ---------------- |
| W / ↑ | Move Up          |
| S / ↓ | Move Down        |
| A / ← | Move Left        |
| D / → | Move Right       |
| ENTER | Hide / Exit Bush |
| E     | Throw Ball       |
| ESC   | Exit Leaderboard |

---

## 📸 Screenshots

### Main Menu

![Main Menu](screenshots/main-menu.png)

### Maze Level

![Level 1](screenshots/level1.png)

### Dog Park

![Level 2](screenshots/level2.png)

### City Crossing

![Level 3](screenshots/level3.png)

### Leaderboard

![Leaderboard](screenshots/leaderboard.png)

---


## Academic Context

This project was developed as part of the Object-Oriented Programming course and demonstrates the application of software engineering principles, game architecture, design patterns, database persistence, and event-driven programming.

---

## Future Improvements

* Additional enemy types
* More levels and quests
* Advanced AI behaviors
* Achievement system
* Multiple save slots
* Improved graphics and animations

