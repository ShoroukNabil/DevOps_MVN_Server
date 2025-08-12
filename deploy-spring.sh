#!/bin/bash
set -euo pipefail

install_maven() {
    if ! command -v mvn &>/dev/null; then
        echo "Maven not found. Installing..."
        if [ -f /etc/debian_version ]; then
            sudo apt-get update && sudo apt-get install -y maven
        elif [ -f /etc/redhat-release ]; then
            sudo yum install -y maven
        elif command -v brew &>/dev/null; then
            brew install maven
        else
            echo "Unsupported OS. Install Maven manually."
            exit 1
        fi
        echo "Maven installation successful."
    else
        echo "Maven is already installed."
    fi
}

build_and_run() {
    # Move to the script's directory (where pom.xml is)
    cd "$(dirname "$0")"
    
    echo "Building the Spring Boot application..."
    if mvn clean install -DskipTests; then
        echo "Build successful. Starting the Spring Boot application..."
        mvn spring-boot:run
    else
        echo "Build failed."
        exit 1
    fi
}

install_maven
build_and_run
