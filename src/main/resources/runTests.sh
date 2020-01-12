#!/bin/bash

echo "Starting tests with extra java parameters: "$JAVA_PROPERTIES
echo "Using feature path: features/"$FEATURE_PATH
if [[ ! -z "$TAG_EXPRESSION" ]]; then
  echo "Using tag expression: "$TAG_EXPRESSION
  TAGS="--tags "
else
  TAGS=""
fi

java $JAVA_PROPERTIES -cp 'lib/*' cucumber.api.cli.Main \
	--glue dummyAPI.stepdefinitions	\
	$TAGS "$TAG_EXPRESSION"\
	--add-plugin pretty \
	--add-plugin html:reports/html	\
	--add-plugin junit:reports/junit.xml \
	classpath:features/$FEATURE_PATH
