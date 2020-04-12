FROM zenika/kotlin:1.3.70-eap-184-jdk11
COPY src /src

RUN kotlinc src/project/client/main.kt -include-runtime -d client.jar
RUN kotlinc src/project/server/main.kt -include-runtime -d server.jar

CMD java -jar $TARGET.jar
