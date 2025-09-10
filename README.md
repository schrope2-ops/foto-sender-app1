# FotoSender - Android Beispielprojekt

Dieses Minimalprojekt ermöglicht:
- Foto mit Kamera aufnehmen (Thumbnail via `MediaStore.ACTION_IMAGE_CAPTURE`)
- Kompressionsgrad wählen (50% oder 25% Qualitätswert)
- Auswahl zwischen zwei vordefinierten E-Mail-Adressen
- Versand über das Standard-E-Mail-App (öffnet einen Sende-Dialog)

Wichtig:
- Das Projekt benutzt **Android Gradle Plugin 8.9.0** und **Kotlin 2.2.0** (aktuell stabile Releases zum Zeitpunkt der Erstellung). Siehe `build.gradle` für Details.
- Der E-Mail-Versand erfolgt per `Intent.ACTION_SEND` und **öffnet** das E-Mail-Programm – vollständig automatisches Senden (ohne Benutzeraktion) erfordert einen SMTP-Server und Zugangsdaten (nicht enthalten).
- Öffne das Verzeichnis `foto-sender-app` in Android Studio und synchronisiere Gradle. Android Studio wird fehlende Gradle-Wrapper/Versionen automatisch herunterladen, falls nötig.

Änderungen / Anpassungen die du ggf. möchtest:
- Vollauflösung-Fotos statt Thumbnails: Implementiere FileProvider und speichere das Foto in einer Datei.
- Vollautomatischer Versand: Implementiere einen SMTP-Client (z.B. JavaMail) und sichere die Zugangsdaten serverseitig oder per Benutzereingabe.
