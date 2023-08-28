# Nome da Image open openjdk
FROM eclipse-temurin:17-jre-alpine

# Diretório de trabalho pode ser qualquer nome
WORKDIR /app

ARG JAR_FILE
# poderia ser explicito em colocar o nome algafood-api-0.0.1-SNAPSHOT.jar,
# mas boto * para facilitar
COPY target/${JAR_FILE}  /app/api.jar

# dá apenas documentação de qual seria a porta
# mas a porta que vai executar e no comando run
# Ex: docker container run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=Leandro78 --name algafood-mysql mysql:8.0
# o -p  do co,

EXPOSE 8080

#aqui diz p comando que vai ser executado
# seria como java -jar api.jar, dentro do workdir
CMD ["java","-jar","api.jar"]