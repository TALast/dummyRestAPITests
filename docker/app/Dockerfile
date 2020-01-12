FROM java:openjdk-8-jre

ADD lib /home/lib
ADD resources/runTests.sh /home/
ADD dummyAPITestSuite-1.0-SNAPSHOT.jar /home/lib/dummyAPITestSuite.jar

RUN mkdir -p /home/test_output

WORKDIR /home

RUN chmod 755 runTests.sh

ENTRYPOINT [ "./runTests.sh" ]
