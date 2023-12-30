db-up:
	docker run --name my-postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -e POSTGRESQL_DATABASE=stealer bitnami/postgresql:latest
db-stop:
	docker stop my-postgres
db-down:
	docker stop my-postgres
	docker rm my-postgres

test-db-up:
	docker run --name my-test-db -p 5433:5432 -e POSTGRES_PASSWORD=password -e POSTGRESQL_DATABASE=stealer bitnami/postgresql:latest
test-db-stop:
	docker stop my-test-db
test-db-down:
	docker stop my-test-db
	docker rm my-test-db