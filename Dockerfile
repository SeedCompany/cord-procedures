FROM maven:3-openjdk-11 as builder

COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/

RUN mvn package

FROM neo4j:4.1-enterprise

COPY --from=builder /tmp/target/cord-1.jar /var/lib/neo4j/plugins

LABEL org.opencontainers.image.source https://github.com/SeedCompany/cord-procedures
