Guide for Project Setup and Deployment

This comprehensive guide will lead you through the essential steps to clone, build, and deploy the project with Docker Compose. Please adhere to the instructions below to have the project up and operational.

**Prerequisites**
Before commencing, make sure you have the following software installed on your system:

- Git
- Maven
- Docker
- Docker Compose
- Angular

**Step 1: Clone the Project**
Begin by cloning the project repository to your local machine using Git:

In your command terminal, run the following commands:


git clone https://github.com/zaineb-bouallegui/GestionRecette.git
cd your-project


**Step 2: Build the Project**
Utilize Maven to build the project and ensure you are in the project's root directory:

In your command terminal, execute these commands:


mvn clean install
cd user && npm i


**Step 3: Build Docker Images**
For each project within the repository, create Docker images. Navigate to each project's directory and initiate the Docker image build:

In your command terminal, use the following command:

docker compose build


**Step 4: Docker Compose**
Return to the project's root directory and employ Docker Compose to launch the services. The provided docker-compose.yml file outlines the services and their configurations:

In your command terminal, run:


docker-compose up


This command will initiate all the services outlined in the docker-compose.yml file in detached mode.

**Step 5: Testing**
Once the services are operational, you can test your application. Depending on your project and its services, open a web browser or use tools such as Postman to interact with the application.

Access the services as needed using the appropriate URL, usually http://localhost:8081, as the api-gateway is typically configured on this port.

To initiate the frontend project, execute the following command: 


ng serve


**Step 6: Stopping and Cleanup**
To halt and remove the services, execute the following Docker Compose command:

In your command terminal, run:


docker-compose down


This will terminate and remove all containers as defined in the docker-compose.yml file.

That's it! You've now completed the process of cloning, building, and deploying the project using Docker Compose.
