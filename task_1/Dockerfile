FROM ubuntu:24.04

RUN apt-get update && apt-get install -y \
    software-properties-common \
    curl \
    unzip

RUN add-apt-repository ppa:deadsnakes/ppa && \
    apt-get update && \
    apt-get install -y python3.10 python3.10-venv

RUN add-apt-repository ppa:openjdk-r/ppa && \
    apt-get update && \
    apt-get install -y openjdk-8-jdk

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

RUN curl -L -o /tmp/kotlin.zip "https://github.com/JetBrains/kotlin/releases/download/v1.9.22/kotlin-compiler-1.9.22.zip" && \
    unzip /tmp/kotlin.zip -d /opt && \
    mv /opt/kotlinc /opt/kotlin && \
    rm /tmp/kotlin.zip

ENV PATH="/opt/kotlin/bin:${PATH}"

RUN curl -L -o /tmp/gradle.zip "https://services.gradle.org/distributions/gradle-8.7-bin.zip" && \
    unzip /tmp/gradle.zip -d /opt && \
    mv /opt/gradle-8.7 /opt/gradle && \
    rm /tmp/gradle.zip

ENV PATH="/opt/gradle/bin:${PATH}"

WORKDIR /app

COPY build.gradle.kts .
COPY src ./src
COPY hello.py .

RUN gradle build --no-daemon && \
    chmod +x hello.py

CMD ["sh", "-c", "./hello.py && java -jar build/libs/app.jar"]