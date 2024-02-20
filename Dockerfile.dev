FROM amazoncorretto:17.0.7-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el JAR de tu aplicación al contenedor en la ubicación /app/app.jar
COPY target/leonlibj-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto 8180 para que pueda ser accedido desde fuera del contenedor
EXPOSE 8180

# Comando para ejecutar la aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]