[README.md](https://github.com/user-attachments/files/22256996/README.md)
# FotoSender Android App

Dies ist ein Beispielprojekt für Android (Kotlin), das:

- Ein Foto mit der Kamera aufnimmt
- Die Bildqualität auf 50 % oder 25 % reduziert
- Eine auswählbare E-Mail-Adresse nutzt und das Foto per Mail versendet

## GitHub Actions Build

Das Repository enthält einen Workflow `.github/workflows/android.yml`.
Sobald du den Code nach GitHub pushst, wird automatisch eine **APK gebaut**.

### So funktioniert es:
1. Repository auf GitHub erstellen und den Inhalt hochladen.
2. Actions-Tab öffnen → dort startet der Workflow.
3. Nach erfolgreichem Build findest du die `app-debug.apk` im Bereich **Artifacts**.

Diese APK kannst du direkt auf deinem Smartphone installieren.
