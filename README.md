# Welcome to our game **mill** ! :)

Mill is one of the oldest board games and known in Europe by different names: Nine Men Morris, Mühle, Molenspel and Merrelles. It is is a strategy board game for two players dating at least to the Roman Empire.
 
# Rules

You win the board game if your opponent has only two stones left, or if he cannot move. There are three phases in the game. In every phase, you can capture if you make three in a row (horizontal or vertical), this is called a *mill*. If you form a mill, you must capture an opposing stone of your choice (except when this stone is part of a mill and your opponent has other stones which are not part of a mill).

## Phase 0: Set name and choose color
In Phase 0 the players must configure their name and color before starting to play the game.
 > to enter the name of player 1, click in the box and type in a name \
 > to choose a color click the combox, then cklick on the *arrow* to continue \
 ![start](https://user-images.githubusercontent.com/81410821/123564075-3a7a9b00-d7b8-11eb-81aa-2258d5ea11f6.gif)
 > to enter the name of player 2, then click on the *arrow* to start the game\
 


## Phase 1: Placing stones 
In the first part of the game, the players each place a stone in turns on an intersection of a horizontal and a vertical line (denoted by a dot).
This phase ends when every player has placed his 9 stones.
  > to set a stone click on a free dot on the board \
  ![set1](https://user-images.githubusercontent.com/81410821/123563827-2aae8700-d7b7-11eb-83b1-18174e807db0.gif)
 


## Phase 2: Moving stones 
When both players placed all their stones on the bord, the second phase starts. When it is your turn, you must move one of your stones to an *adjacent free place* (along a line). If you form a mill, you must remove an opposing stone from the board.
  > to move a stone click on your stone and release on desired position\
  > to take a stone from your opponent after building a mill, click on the desired stone
  ![move](https://user-images.githubusercontent.com/81410821/123563730-9512f780-d7b6-11eb-93d4-f252c2c02413.gif)


## Phase 3: Jumping stones (when one player has only 3 stones left)
The third phase starts when a player has only 3 stones left. That player is allowed to move his stones to *any free* place on the board instead of just an adjacent place. When both players have 3 stones left, both of them may do so. 
  > to jump with a stone click on your stone and release on desired position
![jump](https://user-images.githubusercontent.com/81410821/123561459-b240c980-d7a8-11eb-874d-32ed79a8a306.gif)
\


## Store and Load
The computer version of mill allows the players to save the game and resume it later.
> to save the current game click on the *floppy disc*, then exit the game
\
![save](https://user-images.githubusercontent.com/81410821/123561661-13b56800-d7aa-11eb-8431-249047d26293.gif)

> to resume the saved game click on the *resume from file* image
\
![load](https://user-images.githubusercontent.com/81410821/123563523-7fe99900-d7b5-11eb-8f3a-7898dec83ece.gif)



## Reset
The computerversion of mill can be restarted.
\
> to restart the game and start with a fresh board click the *restart arrow*
\
![reset](https://user-images.githubusercontent.com/81410821/123560895-72c4ae00-d7a5-11eb-96fe-ff005b356cbd.gif)


# Strategy
At the beginning of the game, it is more important to place pieces in versatile locations rather than to try to form mills immediately and make the mistake of concentrating one's pieces in one area of the board.[11] An ideal position, which typically results in a win, allows a player to shuttle one piece back and forth between two mills, removing a piece every turn. 

 
# Development Process:
0.  Scala & SBT & IntelliJ
1.  VCS Git (Version Control System)
2.  ScalaTests
3.  TUI (Terminal User Interface)
4.  MVC architecture (Model View Controller)
5.  TravisCI
6.  Design Patterns
7.  GUI (Graphical User Interface)
8.  Components
9.  Dependency Injection
10. FileIO (XML & JSON)
11. Docker
12. Logging Documentation


# Coverage & Build Status [Masterbranch]
[![Build Status](https://www.travis-ci.com/ginakokoska/MILL100001.svg?branch=Pattern)](https://www.travis-ci.com/ginakokoska/MILL100001)

[![Coverage Status](https://coveralls.io/repos/github/ginakokoska/MILL100001/badge.svg?branch=Pattern)](https://coveralls.io/github/ginakokoska/MILL100001?branch=Master)

# Dockerhub

## TUI version
 > docker pull ginakokoska/mill:tui

## GUI version
To start a gui mill in a container you might need to "xhost +"
 > docker pull ginakokoska/mill:gui


![giphy](https://user-images.githubusercontent.com/81410821/123548021-661f6600-d763-11eb-8683-1a11b35ff9cb.gif)





