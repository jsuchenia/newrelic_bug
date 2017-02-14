#!/bin/sh
./gradlew build copyNewRelicJar
java -javaagent:build/newrelic-agent-3.35.2.jar -Dnewrelic.config.file=newrelic.yml -jar build/libs/newrelic_bug-0.0.1-SNAPSHOT.jar
