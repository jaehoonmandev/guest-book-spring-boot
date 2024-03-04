
# 현재 애플리케이션이 구동되는 기반 이미지 버전 pull
FROM openjdk:17

# 작성자, 관리자 등의 정보를 나타낼 수 있다.
LABEL maintainer='wido1593@gmail.com'

# JAR 파일 경로 변수로 지정.
ARG JAR_FILE=build/libs/*.jar

# JAR 파일을 app.jar 라는 이름으로 사용하기 위해 복사.
COPY ${JAR_FILE} app.jar

# JAR를 실행하기 위한 java -jar app.jar 명령어를 ENTRYPOINT로 설정
ENTRYPOINT ["java","-jar", "app.jar"]