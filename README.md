# Twitter Dashboard
Application to access Twitter using Spring Social.

### Docker

A docker file and docker-compose.yml are provided. 

The path to your application.properties file should be included in the docker-compose.yml file.

Example:

mvn clean package docker:build

docker-compose -f /path/to/docker-compose.yml up

### Authors and Contributors
Emma Hastings (@emmahastings)

### Credits and acknowledgements
* Created with the help of [Spring guides](https://spring.io/guides)
* Built with [Bootstrap](http://getbootstrap.com/)
* Enhanced with [Font Awesome](http://fontawesome.io) by Dave Gandy
* Colour scheme with [PaintStrap](http://paintstrap.com/)
