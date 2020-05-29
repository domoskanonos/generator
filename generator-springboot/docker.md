###Standart Docker Befehle:

######In einen Container springen:
`docker exec -it <container name> /bin/bash`

######Alle nicht verwendeten Docker Images löschen:
docker image prune -a

######Alle Images,Networks und Volumes löschen:
docker system prune --volumes

######Alle unbenutzten Docker Container löschen
docker container prune

docker volume ls

docker volume prune -f
