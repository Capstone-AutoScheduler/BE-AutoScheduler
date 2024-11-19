#FROM amazoncorretto:17
#LABEL authors="jaehyun-park"
#ARG JAR_FILE=./build/libs/AutoScheduler-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
##ENTRYPOINT ["sh", "-c", "java -Duser.timezone=Asia/Seoul -jar /app.jar"]
#ENTRYPOINT ["java", "-jar", "app.jar"]

FROM amazoncorretto:17
LABEL authors="jaehyun-park"
#
## 1. 필수 도구 및 패키지 설치
#RUN yum update -y && \
#    yum install -y wget unzip curl xorg-x11-server-Xvfb libXcomposite libXrandr libXcursor libXinerama libXi libXtst alsa-lib libXScrnSaver
#
## 2. Google Chrome 설치
#RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm && \
#    yum localinstall -y google-chrome-stable_current_x86_64.rpm && \
#    rm -f google-chrome-stable_current_x86_64.rpm
#
## 3. ChromeDriver 설치
#RUN CHROMEDRIVER_VERSION=`curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE` && \
#    wget -N https://chromedriver.storage.googleapis.com/$CHROMEDRIVER_VERSION/chromedriver_linux64.zip && \
#    unzip chromedriver_linux64.zip && \
#    mv chromedriver /usr/local/bin/ && \
#    chmod +x /usr/local/bin/chromedriver && \
#    rm chromedriver_linux64.zip
#
## 4. JAR 파일 복사
#ARG JAR_FILE=./build/libs/AutoScheduler-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
#
## 5. Java 애플리케이션 실행
#ENTRYPOINT ["java", "-jar", "app.jar"]

# 1. 필수 도구 및 패키지 설치
RUN apt-get update && \
    apt-get install -y wget unzip curl

# 2. 안정적인 버전의 Google Chrome 설치 (예: 114 버전)
RUN wget https://dl.google.com/linux/chrome/deb/pool/main/g/google-chrome-stable/google-chrome-stable_114.0.5735.90-1_amd64.deb && \
    apt-get install -y ./google-chrome-stable_114.0.5735.90-1_amd64.deb && \
    rm -f google-chrome-stable_114.0.5735.90-1_amd64.deb

# 3. ChromeDriver 설치 (Chrome 버전에 맞는 버전)
RUN wget -N https://chromedriver.storage.googleapis.com/114.0.5735.90/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver && \
    rm chromedriver_linux64.zip

# 4. JAR 파일 복사
ARG JAR_FILE=./build/libs/AutoScheduler-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 5. Java 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
