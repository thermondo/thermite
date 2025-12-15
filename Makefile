all: clean format lint test report assemble docs
.PHONY: all

appium-start:
	@echo "Starting Appium"
	@mkdir -p appium
	@nohup appium --log-level info > appium/appium.log 2>&1 &
	@echo $$! > appium/appium.pid
	@echo "Appium started with PID `cat appium/appium.pid`"
.PHONY: appium-start

appium-stop:
	@echo "Stopping Appium"
	@if [ -f appium/appium.pid ]; then \
		kill -9 $$(cat appium/appium.pid) || true; \
		rm -f appium/appium.pid; \
		echo "Appium stopped."; \
	else \
		echo "No Appium process found."; \
	fi
.PHONY: appium-stop

assemble:
	./gradlew assemble
.PHONY: assemble

clean:
	./gradlew clean
.PHONY: clean

docs:
	./gradlew dokkaGenerateHtml
.PHONY: docs

format:
	./gradlew formatKotlin
.PHONY: format

lint:
	./gradlew lintKotlin detekt
.PHONY: lint

local:
	./gradlew assemble publishToMavenLocal
.PHONY: local

publish:
	./scripts/publish.sh ${GITHUB_RUN_NUMBER}
.PHONY: publish

report:
	./gradlew koverHtmlReport koverXmlReport
.PHONY: report

test:
	./gradlew test
.PHONY: test
