
# cache as most as possible in this multistage dockerfile.
FROM maven:3.6-alpine as DEPS

WORKDIR /opt/app 
#COPY module1/pom.xml module1/pom.xml
#COPY module2/pom.xml module2/pom.xml
COPY MyEbuyProject/pom.xml MyEbuyProject/pom.xml
COPY MyEbuyCommon/pom.xml MyEbuyCommon/pom.xml
COPY MyEbuyWebParent/pom.xml MyEbuyWebParent/pom.xml
COPY MyEbuyWebParent/MyEbuyBackEnd/pom.xml MyEbuy/WebParent/MyEbuyBackEnd/pom.xml
COPY MyEbuyWebParent/MyEbuyFrontEnd/pom.xml MyEbuyWebParent/MyEbuyFrontEnd/pom.xml

# you get the idea:
# COPY moduleN/pom.xml moduleN/pom.xml

COPY pom.xml .
RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

# if you have modules that depends each other, you may use -DexcludeArtifactIds as follows
# RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline -DexcludeArtifactIds=module1

# Copy the dependencies from the DEPS stage with the advantage
# of using docker layer caches. If something goes wrong from this
# line on, all dependencies from DEPS were already downloaded and
# stored in docker's layers.
#FROM maven:3.6-alpine as BUILDER
#WORKDIR /opt/app
#COPY --from=deps /root/.m2 /root/.m2
#COPY --from=deps /opt/app/ /opt/app
#COPY module1/src /opt/app/module1/src
#COPY module2/src /opt/app/module2/src

# use -o (--offline) if you didn't need to exclude artifacts.
# if you have excluded artifacts, then remove -o flag
#RUN mvn -B -e -o clean install -DskipTests=true

# At this point, BUILDER stage should have your .jar or whatever in some path
#FROM openjdk:8-alpine
#WORKDIR /opt/app
#COPY --from=builder /opt/app/<path-to-target>/my-1.0.0.jar .
#EXPOSE 8080
#CMD [ "java", "-jar", "/opt/app/my-1.0.0.jar" ]