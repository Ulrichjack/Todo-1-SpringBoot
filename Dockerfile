# Utilise une image Java 17
FROM openjdk:17-jdk-slim

# Crée un répertoire de travail
WORKDIR /app

# Copie le fichier JAR généré (ajustez le nom si nécessaire)
COPY target/*.jar app.jar

# Expose le port 8081
EXPOSE 8081

# Variables d'environnement pour optimiser la JVM
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Commande pour lancer l'application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]