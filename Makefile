.DEFAULT_GOAL := build

BIN_FILE=leonlib
JAR_FILE=leonlibj-0.0.1-SNAPSHOT.jar

build:
	@time mvn -q clean install -DskipTests -T4 -DforkCount=2 -Dmaven.site.skip=true \
    -Dmaven.javadoc.skip=true -Dmaven.test.skip=true -Dmaven.compiler.useIncrementalCompilation=true
	@echo Done...

clean:
	rm -rf ./target/

test:
	go test

check:
	go test

cover:
	go test -coverprofile cp.out
	go tool cover -html=cp.out

run:
	java -jar "./target/${JAR_FILE}"

lint:
	golangci-lint run --enable-all
