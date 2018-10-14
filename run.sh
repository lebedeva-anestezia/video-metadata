docker build -t video .
docker run --rm -ti -p 8080:8080 video:latest