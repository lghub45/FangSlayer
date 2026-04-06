This README serves as a guide on to download and play FANG SLAYER. 


___________________________________________________DOWNLOAD AND SETUP GUIDE______________________________________________________________________________________

STEP ONE: INSTALL JAVA

A: Based on your device download the version of Java that you need using THIS link. https://www.oracle.com/java/technologies/downloads/#jdk26-windows 
   (NOTE: the previous link takes you to the WINDOWS version, but you can select your device on the website. 
   Also it seems the best version to get is the "x64 installer" however, be sure to google which version is recommended for your device)

STEP TWO: DOWNLOAD ECLIPSE IDE & set it up

A: From this link: https://www.eclipse.org/downloads/packages/  you need to download "Eclipse IDE For Java Developers" more specifically, the "x86_64" version. 
   However, be sure to double check that this version (x86_64) is better for your computer. 
B: Once downloaded, run eclipse.exe and when this is done, open the program (Eclipse IDE). 
C: Once Eclipse is open (if prompted) be sure to allow the exclusion check (let it get scanned) for saftey reasons. 

STEP THREE: IMPORT FANG SLAYER FROM HERE

A: IN ECLIPSE click "File" and select "import". 
B: Select "Git" then "Projects from Git", then click "Next".
C: You'll want to select "Clone URI", and click "Next".
D: From THIS WEBSITE you'll want to click the green "Code" button and copy the link "https://github.com/lghub45/FangSlayer.git" from the HTTPS text box. 
E: Paste the link into the URI text box on Eclipse IDE then click "finish". 

STEP FOUR: DOWNLOAD AND SET UP THE JAR FILE

A: Click on "sqlite-jdbc-3.50.3.0.jar" placed under the FangSlayer file on github. Next, click the button with three dots placed horizontally and click "Download".
B: In Eclispe IDE: right click the "Fang Slayer" folder and select "Build Path" then "Configure Build Path". 
C: Click on the "Libraries" tab and click on "Classpath".
D: Add an "External Jar" file, specifically the one you previously downloaded in Step 4A. 

STEP FIVE: ENJOY =] 

A: Now that everything needed is set up, just navigate to the MAIN class by clicking the "FangSlayer" folder, then the "src" folder, the "MainPackage" folder
   and finally open "FangSlayerMain.java". 
B: Once this is open click "Run" button (it looks like a green circle with a white triangle inside of it), and the game will start. 

ALTERNATIVELY:
The following link is a runable JAR file which was too big to include as a normal file in this 
repository so I added the google drive link for anybody to download it. 
It ensures a lot less hassle due to the fact that you don't need to set up anything on your end (other than copy and pasting this link, and downloading the file)
, but it may possibly be a little riskier and probably not as high quality as setting it up yourself (I'm not sure though because I'm pretty new to JAR files).
FANG SLAYER DOWNLOAD LINK
https://drive.google.com/file/d/1caMjbJ8wdjmcBJ9AAUx1Duw73xWPNKvJ/view?usp=drive_link


____________________________________________________________GAMEPLAY GUIDE_______________________________________________________________________________________

CONTROLS:
- BASICS
  W or Up_Arrow : Move up
  A or Left_Arrow : Move left
  S or Down_Arrow : Move down
  D or Right_Arrow : Move right
  F or P: fire primary weapon

- WEAPON SELECT
  1 button: equip main weapon
  2 button: equip silver daggers
  3 button: equip wooden stakes

HOW TO PLAY THE GAME:
- User interface:
  * When you play the game there will be a "Score tracker" to track how many points you've collect, a "Round Counter" to showcase what round it is, a "Dagger
  Counter" to showcase how many silver daggers you have, and a "difficulty indicator". All of these will be shown in the upper right.
  * In the lower left: you'll see which weapon is equipped (and in the case of wooden stakes: how many stakes you have left).

- Gameplay rules (General):
  * The goal of the game is to survive as many rounds / get the highest score as they possibly can. If a vampire/monster runs into / hits the player the player dies.
  * Both the monsters and the player have ONE life.
  * The difficulty selected will change how many vampries spawn in each round, how many points you get from said vampires, and (in the case of "Seasoned Hunter")
    how accurate your shots need to be (in Baby and Normal, a haircut will kill vampires, but in Seasoned Hunter the head or torso MUST be shot to kill)
  * Each vampire/monster killed rewards a specific number of points based on what difficulty the user selected. If the user dies and gets higher than any score on
  the leaderboard, the user is prompted to enter a new highscore onto the leaderboard.
  * Shooting a vampire with an arrow kills the vampire and uses up the arrow.
  * Running into a vampire with the silver daggers equipped kills the vampire instead of the user.
  * Shooting a vampire with a wooden stake kills the vampire, but DOESN'T use the wooden stake (wooden stakes can pierce thru multiple vampires).
  * Shooting a vampire with the Boomstick kills the vampire and uses up a bullet (the boomstick fires three bullets- which function the same as arrows-
    in a spread shot which can kill up to three vampires per shot - if well aimed)
  * Holding down the fire button whilst the "lazer beam" glitch/easter egg is activated will shoot a "lazer beam" of arrows.
  * Special items (the wooden stakes and silver daggers) are granted randomly every five rounds (you can tell if you got wooden stakes if the number of silver
    daggers in the upper right remains the same).
  * The Boomstick and "lazer beam" glitch are aquired via cheat codes. These are granted by playing the game. 

- Gameplay rules (Boss Blitz):
  * The goal of this gamemode is to kill Vlad the Devourer of Souls
  * Vlad is the only character in the game to have multiple lives (the amount he has depends on the difficulty selected).
  * Vlad has three phases:
    PHASE 1: Vlad will slide across the top of the screen and shoot dark magic fireballs imbued with the souls of the damned.
    PHASE 2: Vlad will teleport to center of the screen and summon a MASSIVE horde of the undead. Killing ten of these vile vamps will grant the user
             either silver daggers or wooden stakes (this will be helpful for phase 3).
    PHASE 3: Vlad transforms into his final form, A GIANT BAT MONSTER. He will slowly glide across the sides of the screen vertically to try and align with you,
             and when he does, he will zoom across the screen horizontally in a desperate attempt to snatch his prey (this being you =[ ).
  * Killing vlad rewards a spcial new primary weapon: The Boomstick! (more specifically the cheat code to get it). While you can't earn points or leadboard
    recognition in the mode, collecting the Boomstick code will earn you good-ole-fashioned bragging rights.

A FINAL MESSAGE:
  The whole point of this project is to spread fun and encourage the competitive spirit. Downloading this onto eclipse hypothetically means that you can mess 
  around with your copy of the code, but this is highly discouraged (specifically upping the amount of points you get for killing vampires) 
  on account of it defeats the entire purpose of playing the game and goes against the entire M-O of this program (I said it in the first sentence). 
  Regardless, I hope you have as much fun with this game as I did making it and if you wanna see cool concept art you can unlock the cheat code by defeating Vlad
  on the highest difficulty (Nightmare)... unless I didn't have time to code it in (which is very likely because this README was written 2 days before it's due). 
  In which case, feel free to take a peak in the images file at some of the old image files for the game including the test 
  background and any of the vampire/hunter images with "1" in it. 
  Anyhow, I hope you really enjoy sinking your teeth into FangSlayer.   >=] 
