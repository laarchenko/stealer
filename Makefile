postgres-up:
	docker run --name my-postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -e POSTGRESQL_DATABASE=stealer bitnami/postgresql:latest
postgres-stop:
	docker stop my-postgres
postgres-down:
	docker stop my-postgres
	docker rm my-postgres