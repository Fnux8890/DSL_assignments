# Start with an official OpenJDK image to get the latest Java.
# Adjust the tag as needed to get the Java version you want (e.g., 11, 17, etc.).
FROM openjdk:latest

# Install Node.js
RUN apt-get update && apt-get install -y ca-certificates curl gnupg \
    && curl -fsSL https://deb.nodesource.com/gpgkey/nodesource-repo.gpg.key | gpg --dearmor -o /usr/share/keyrings/nodesource.gpg \
    && NODE_MAJOR=20 \
    && echo "deb [signed-by=/usr/share/keyrings/nodesource.gpg] https://deb.nodesource.com/node_$NODE_MAJOR.x $(bash -c '. /etc/os-release && echo $VERSION_CODENAME') main" | tee /etc/apt/sources.list.d/nodesource.list \
    && apt-get update && apt-get install -y nodejs

# Install Maven, Gradle, and any other tools needed for Xtext development.
# You can specify versions for Maven and Gradle or use the latest available in the package repository.
RUN apt-get update && \
    apt-get install -y maven gradle && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Additional setup for your Xtext development environment can go here.
# For example, installing specific versions of tools, setting up environment variables, etc.

