Description
=====
This is a collection of older projects of mine that I don't plan to revisit, either because they're essentially done, because it would require rewriting tons of old code and would be better off to be remade, or just because the project isn't worth persuing. Just because a project is here doesn't mean it's broken, small, or stupid, although any of them may be one or more of those. Some of them are actually pretty cool. Below I have a brief description of each one.

AP Comp Game
====
Finished June 17, 2014

To run it, either use the runnable jar file, or if that doesn't work run the command "java -jar Dungin2.jar" in the folder

One of my larger projects, this was made for my AP Computer Science final project in high school with two friends. The art is done by Tal Bonen and I, the coding is done by Shiva Murali and I. We had three weeks for the project, but with finals cutting into our time we only really had about 2.

For our project we made a roguelike dungeon craller-ish game. Includes randomly generated dungeons, randomly spawning enemies, equippable equipment, fog of war, and multiple classes (although very indistinguishable with the time we had to develop it). Enemies unique to each of four dungeon types. Levels and enemies get progressively more difficult as you advance through dungeons. More info in the text file included (*Note* text file missing at the moment, need to dig up)

Fighter
====
June 2013

The only part running properly is CPU_Battle.py, just run that. Requires pygame

A summer project to create a fighter game like Street Fighter. Made it pretty far but the code was starting to get convoluted and I grew tired of the project. I have most of what I wanted for one player to be able to do implemented. I have movement - jumping, crouching, the player stayed facing the opponent. Fighting is done with "a" and "b" buttons normally found on a controller or arcade machine represented by the "u" and "h" keys, and the attacks behaved differently based on if the attack was from a standing, jumping, or crouching position, with different cooldown times, knockback effects, hitboxes, or even tripping effects. There is also added combo detection, that would detect if a player did a certain combination of moves in a short period of time a combo attack would be done.

To play, use wasd to move jump and crouch, the "u" key to kick, and "h" key to punch.

Kingdoms
====
August 2013

Run Kingdoms_Remake.py requires pygame

A remake of an older project, which was a remake of a text-based older project of the same type. Kingdoms was being built into a civilization building game, with randomly generated maps that contained mountains forests and grasslands. Not entirely random, as the ratio of grasslands, forests, and mountains is not 1 to 1 to 1. Also the forests separate the mountains from the grasslands. There are a few buildings that can be constructed, however I only ever made it as far as the map generation so they don't do anything. Not much direction to the game.

You can move around the map with arrow keys, moving the mouse to the edge of the screen, or clicking on the minimap. Click to place buildings. Press 1 for roads, 2 for farms, 3 for lumber yards (cuts surrounding trees), 4 for mine (can only be placed on mountain) and 5 for town hall.

Random Game Generator
====
September 2013

Run "random game generator.py"

Pretty simple and stupid. Generates random game ideas using a few text files of keywords. Fun for about a minute

Battle World
====
March 2013

Run "Battle World.py" requires pygame

Just something to watch, no interaction. The town in the upper left spawns adventurers that wander around until they see a boar (represented as a brown dot) or another adventurer, which they then attempt to kill. Killing increases xp, with enough they level up and get stronger.

You can click on any of their adventurers to see thier level, xp, etc.

BM Tron
====
February 2013

requires pygame

A simple recreation of the game BM Tron. Only has 2 player mode. Move around trying not to hit the walls or yourself

Arrow keys and wasd to control

Space Invaders
====
May 2013

A simple version of the classic game Space Invaders. Nothing that polished, was more just to see if I could do it. Plenty of glitches and bugs. Array of enemies can be an arbitrary height and width

arrow keys to move, space to shoot

carrier finder
====
May 2014

Pretty small, takes a phone number and looks up the carrier and location using fonefinder.com

Conways Game of Life
====
June 3, 2013

requires pygame

An inneficcient [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life). Not an efficient algorithm, and Python is not the fastest language. Tons of lag

Click anywhere to change the state of that tile, press space to pause or unpause. Cannot change the state of tiles while it is running. If you click and nothing happens, its probably lag, give it a second or two

Headline Grabber
====
May 2014

This one is pretty cool I think. It grabs news headlines off NPR and short descriptions of the articles, as well as the links to the full article. Also gets stock ticker info for any stock included in the relevant list in the code (line 41)

Unit Movement
====
December 2012

requires pygame

I was going to develop this into a mini RTS game, but never got around to it. Was the first time I messed around with moving units around.

Click on a unit or click and drag to select multiple units. Right click to give them a place to move to. They attack each other if they get close enough

