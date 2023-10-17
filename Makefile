SHELL := /bin/bash
.PHONY: start

start-app:
	-@docker rm -f blackjack21-api blackjack21-database
	-@docker image rm -f blackjack21-api
	@mvn clean install -DskipTests
	@docker compose up -d

stop-app:
	@docker compose down

start-db:
	sh start-db.sh