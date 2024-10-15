FROM openjdk:21-jdk

WORKDIR /app

EXPOSE 4040

COPY /target/agendamento-0.0.1-SNAPSHOT.jar agendamento.jar

CMD ["java", "-jar", "agendamento.jar"]