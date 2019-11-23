FROM openjdk:8 AS builder
WORKDIR dropwizard-scaffolding
COPY . .
RUN ./gradlew install

FROM openjdk:8
WORKDIR application
COPY --from=builder /dropwizard-scaffolding/build/install/dropwizard-scaffolding/bin/dropwizard-scaffolding bin/application
COPY --from=builder /dropwizard-scaffolding/build/install/dropwizard-scaffolding/lib lib
COPY --from=builder /dropwizard-scaffolding/config/configuration.yml .
CMD bin/application server configuration.yml
