#!/bin/bash

# Set installation directory
INSTALL_DIR="/usr/jq"

# Check if jq is already installed
if command -v jq >/dev/null 2>&1; then
  echo "jq is already installed."
  exit 0
fi

# Check if installation directory exists, create if necessary
if [ ! -d "$INSTALL_DIR" ]; then
  echo "Creating installation directory: $INSTALL_DIR"
  sudo mkdir -p "$INSTALL_DIR"
fi

# Extract jq archive to installation directory
sudo tar -zxvf jq-1.5.tar.gz -C "$INSTALL_DIR"

# Configure, make, and install jq
sudo  /usr/jq/jq-1.5/configure && sudo make && sudo make install

echo "jq has been installed to $INSTALL_DIR"