###default build:
mvn clean javadoc:javadoc install

###build and push docker image build:
create docker image
>mvn clean javadoc:javadoc install docker:build 

and pull local:
>docker pull mypaperbox 

or push to server:
>mvn docker:push 

and after that, goto server and pull:
>docker pull localhost:5000/mypaperbox

start the new created image local with default profile and without demon mode to debug:
>docker run -e "JAVA_OPTS=-Dsecret=###MY_SECRET###" --restart=always -p 8099:8099 mypaperbox

start the new created image with profile test and in demon mode:
>docker run -d -e "SPRING_PROFILES_ACTIVE=test" -e "secret=###MY_SECRET###" --restart=always -p 8099:8099 localhost:5000/mypaperbox

start the new created image with profile prod and in demon mode:
>docker run -d -e "SPRING_PROFILES_ACTIVE=prod" -e "secret=###MY_SECRET###" --restart=always -p 8099:8099 localhost:5000/mypaperbox