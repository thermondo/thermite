all: clean format lint test report assemble
.PHONY: all

appium:
	appium
.PHONY: appium

assemble:
	./gradlew assemble
.PHONY: assemble

clean:
	./gradlew clean
.PHONY: clean

format:
	./gradlew formatKotlin
.PHONY: format

lint:
	./gradlew lintKotlin detekt
.PHONY: lint

report:
	./gradlew koverHtmlReport koverXmlReport
.PHONY: report

test:
	./gradlew test
.PHONY: test
