mvn package -Dmaven.test.skip=true
if [ "$?" -ne "0" ]
then
    exit 1
fi
java -jar "target/purranya_app-1.0-SNAPSHOT-jar-with-dependencies.jar"
