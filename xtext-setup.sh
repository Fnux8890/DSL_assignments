#!/bin/bash

# Stop execution if any command fails
set -e

apt-get update && apt-get install -y maven gradle nodejs npm

# Define the base workspace path
WORKSPACE_PATH="/workspaces/DSL_assignments"

# Assuming your actual Xtext project is in a subdirectory of DSL_assignments,
# adjust the path accordingly
XTEXT_PROJECT_PATH="$WORKSPACE_PATH/path/to/xtext-project"
VS_CODE_EXTENSION_PATH="$WORKSPACE_PATH/my-vscode-extension"

# Check and build the Xtext project
if [ -d "$XTEXT_PROJECT_PATH" ]; then
  echo "Building Xtext project in $XTEXT_PROJECT_PATH..."
  cd "$XTEXT_PROJECT_PATH"
  if [ -f "pom.xml" ]; then
    mvn clean install
  else
    echo "pom.xml not found in $XTEXT_PROJECT_PATH"
    exit 1
  fi
else
  echo "$XTEXT_PROJECT_PATH does not exist."
  exit 1
fi

# Make the language server executable if it exists
if [ -f "$XTEXT_PROJECT_PATH/server/bin/my-xtext-ls" ]; then
  echo "Making the language server executable..."
  chmod +x "$XTEXT_PROJECT_PATH/server/bin/my-xtext-ls"
else
  echo "Language server binary not found in $XTEXT_PROJECT_PATH/server/bin. Skipping..."
fi

# Build the VS Code extension if the directory exists
if [ -d "$VS_CODE_EXTENSION_PATH" ]; then
  echo "Building VS Code extension..."
  cd "$VS_CODE_EXTENSION_PATH"
  npm install
  vsce package
else
  echo "$VS_CODE_EXTENSION_PATH does not exist. Skipping extension build."
fi

echo "Xtext environment setup complete!"
