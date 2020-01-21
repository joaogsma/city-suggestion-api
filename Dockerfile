FROM openjdk:8 AS builder
WORKDIR city-suggestion-api
COPY . .
RUN ./gradlew install

FROM openjdk:8
WORKDIR application
COPY --from=builder /city-suggestion-api/build/install/city-suggestion-api/bin/city-suggestion-api bin/application
COPY --from=builder /city-suggestion-api/build/install/city-suggestion-api/lib lib
COPY --from=builder /city-suggestion-api/config/configuration.yml .
COPY --from=builder /city-suggestion-api/cities.json .
CMD bin/application server configuration.yml
