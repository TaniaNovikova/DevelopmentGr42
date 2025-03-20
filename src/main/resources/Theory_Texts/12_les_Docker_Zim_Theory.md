Lecture: Docker and Spring Boot
Brief Introduction to Docker
Docker is a containerization platform that enables you to package your application
(along with all necessary dependencies, libraries, and environment settings)
into a single container. This container can be run on any system where 
Docker is installed, ensuring consistent behavior across different environments.

Key Advantages of Docker: 1. Portability –
A container can be run locally, on a server, or in the cloud,
with the same results.
2. Isolation – Each container has its own dependencies, 
so different applications won’t conflict with each other. 
3. Simplified Deployment – You no longer need to install and configure dependencies
4. manually on the target server. 4. Scalability – You can easily spin up multiple instances of containers to handle higher workloads.

⸻

Spring Boot and Docker
Spring Boot simplifies the process of building and running 
Java applications by providing an embedded web server (often Tomcat),
auto-configuration, and a convenient project structure. 
By pairing Spring Boot with Docker, you can create a container 
with your application that’s ready to be deployed anywhere.

What does the overall flow look like? 1. Build your Spring Boot application into a .jar file. 2. Create a Dockerfile: • Specify a base image (for example, openjdk). • Copy the .jar file into the container. • Configure the command that will run your application. 3. Build the Docker image (using docker build). 4. Run the container (using docker run).

⸻

Sample Dockerfile for Spring Boot
Below is a simple Dockerfile for a Spring Boot application:

Use the official OpenJDK 17 image
FROM openjdk:17-jdk-slim

Set the working directory inside the container
WORKDIR /app

Copy the compiled JAR from the target folder into the container
COPY target/myapp-0.0.1-SNAPSHOT.jar app.jar

Set the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

Line-by-line explanation: 1. FROM openjdk:17-jdk-slim – the base image containing the Java Virtual Machine (JVM). 2. WORKDIR /app – sets the working directory in the container. 3. COPY target/myapp-0.0.1-SNAPSHOT.jar app.jar – copies the JAR file from the local target folder into the container. 4. ENTRYPOINT [...] – defines the command that starts your Spring Boot application.

Make sure the JAR filename in the COPY instruction matches the one produced by your build tool (Maven/Gradle).
Copy
⸻

Building the Docker Image
After creating your Dockerfile and ensuring the .jar is built (e.g., via mvn clean package), you can build the Docker image by running:

docker build -t my-spring-boot-app .

Here: • -t my-spring-boot-app specifies the image name (tag). • . indicates that the build context is the current directory (where your Dockerfile is located).

⸻

Running the Container
To run a container based on the image you just built, use:

docker run -p 8080:8080 my-spring-boot-app

Explanation: 1. -p 8080:8080 maps port 8080 inside the container to port 8080 on your host machine, so you can access the application at http://localhost:8080. 2. my-spring-boot-app is the name of the image you specified during the build.

Once started, the Spring Boot application will be available at http://localhost:8080 (assuming you haven’t changed the default port).

⸻

Verifying the Application
If everything goes well, your terminal will show the standard Spring Boot logs, including Tomcat starting on port 8080, followed by a startup success message. In your browser, navigate to http://localhost:8080 to see your application’s homepage or REST response.

⸻

Common Questions and Tips
Optimizing Image Size: • Use minimal images like openjdk:17-jdk-slim or alpine variants. • Copy only what is needed (the .jar) into the container. • Consider using multi-stage builds to keep the final image as small as possible.
Network Settings: • If you use Docker Compose, you can link multiple containers (e.g., your Spring Boot app + a database). • You can customize ports by changing the mapping (e.g., -p 8081:8080).
Managing Configuration: • You can pass environment variables (ENV) via docker-compose.yml or the -e flag in docker run.
Working with Databases: • Run a separate container for your database (e.g., Postgres). • Configure your Spring Boot application to connect to it by specifying the service name or host.
⸻

Conclusion

Using Docker with Spring Boot greatly simplifies application development, deployment, and scaling. The main tasks involve writing an effective Dockerfile, keeping an eye on image size, and configuring networking and environment variables properly.

Key Takeaways: • Containers isolate your application and make it portable. • The Dockerfile specifies how to build the environment for your Spring Boot app. • Use docker build and docker run to build and run your container. • Scaling and deployment become much easier thanks to Docker’s standard container format.

