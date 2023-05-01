FROM eclipse-temurin:17-jdk
WORKDIR /app
RUN wget https://dlcdn.apache.org/maven/maven-3/3.9.1/binaries/apache-maven-3.9.1-bin.tar.gz -P /tmp/
RUN tar xf /tmp/apache-maven-*.tar.gz -C /opt/
ENV TZ Asia/Tokyo

RUN apt update && apt install -y git