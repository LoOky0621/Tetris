---------------------------------------------Ordnerstrucktur-----------------------------------------

Tetris/
├── classes/
│   ├── src/
│   │	 ├── main/
│   │   │	  ├── java/
│   │   │    │		├── TetrisGame.class       
│   │   │    │		├── Block.class            
│   │   │    │		├── Tetromino.class          
│   │   │    │		├── Game.class              
│   │   │    │		└── TetrominoFactory.class
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── TetrisGame.java         // Hier wird das Fenster definiert & Hauptklasse zum Starten------<= Start
│   │   │   ├── Block.java              // Klasse für Tetrominos (Formen)
│   │   │   ├── Tetromino.java          // Klasse für Tetris-Block-Verhalten
│   │   │   ├── Game.java               // Klasse für die Spiellogik
│   │   │   └── TetrominoFactory.java   // Klasse zum Erstellen zufälliger Tetrominos
│   ├── resources/
│   │   ├── images/                     // Bilder für das Spiel-------------------------------------------<= Nicht verwendet
│   ├── tests/
│   │   ├── Old                         // Alter Ansatz (Funktioniert nur bedingt)
│   │   ├── Sicherheitskopie            // Sicherheitskopie, falls Original nicht mehr zu retten ist
├── lib/                                // Externe Bibliotheken, wenn benötigt-----------------------------<= Nicht verwendet
├── README.md                           // Projektbeschreibung und Anweisungen-----------------------------<= Ordnerstruktur
├── .vscode/                            // VS Code-Konfigurationsdateien-----------------------------------<= Nicht verwendet
├── build/                              // Kompilierte Klassen und Ressourcen------------------------------<= Nicht verwendet
├── Dokumentaion/                       // Kompilierte Klassen und Ressourcen------------------------------<= Nicht verwendet
     ├── Doku.pdf						 // Fertige Dokumentaion
     ├── Dokumentaion.docx				 // Rohfassung Dokumentaion



---------------------------------------------Spielanleitung-----------------------------------------


Für Java Version 17 Major Version 61

Willkommen bei Tetris! Um das Spiel zu starten, 
führen Sie die Datei "Tetris.exe" aus. 
Ein grafisches Fenster wird geöffnet, und Sie können direkt mit dem Spielen beginnen. 
Das Spiel startet standardmäßig im Level 1.

In Level 1 bewegen sich die Tetrominos langsam, was bedeutet, dass sie mehr Zeit haben, 
Entscheidungen zu treffen. Allerdings erhalten Sie für das erfolgreiche Löschen von 
Linien in diesem Level weniger Punkte. Je höher Ihr Level, desto mehr Punkte erhalten Sie pro gelöschter Linie, 
aber die Tetrominos bewegen sich auch schneller.

Hier ist eine Übersicht über die Geschwindigkeit in den verschiedenen Levels:

Level 1: 0,42 Sekunden pro Bewegung
Level 2: 0,34 Sekunden pro Bewegung
Level 3: 0,26 Sekunden pro Bewegung
Level 4: 0,18 Sekunden pro Bewegung
Level 5: 0,1 Sekunden pro Bewegung


Punktevergabe:

Löschen Sie eine Linie: Sie erhalten 10 Punkte multipliziert mit Ihrem aktuellen Level. 
Zum Beispiel erhalten Sie im Level 1 10 Punkte pro gelöschter Linie.
Löschen Sie gleichzeitig zwei Linien: 25 Punkte multipliziert mit Ihrem Level.
Löschen Sie gleichzeitig drei Linien: 40 Punkte multipliziert mit Ihrem Level.
Löschen Sie gleichzeitig vier Linien: 50 Punkte multipliziert mit Ihrem Level.

Ihre aktuellen Punkte und Ihr Level werden in der Konsole angezeigt
und aktualisieren sich nach jeder erfolgreich gelöschten Zeile.

Sobald Sie sich selbst blockieren und keine möglichen Züge mehr möglich sind, 
öffnet sich ein Fenster, das Ihre erreichten Punkte anzeigt. 
Durch Klicken auf "OK" wird das Spiel geschlossen, und Sie haben die Möglichkeit, 
von vorne zu beginnen.


Steuerung:

Pfeil nach links: Bewegen des Tetrominos nach links.
Pfeil nach rechts: Bewegen des Tetrominos nach rechts.
Pfeil nach unten: Schnelles Bewegen des Tetrominos nach unten.
Pfeil nach oben: Drehen des Tetrominos.
Viel Spaß beim Spielen!