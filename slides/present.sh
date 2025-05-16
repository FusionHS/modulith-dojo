#!/bin/bash

# Check if Marp CLI is installed
if ! command -v marp &> /dev/null; then
    echo "Marp CLI is not installed. Installing via npm..."
    npm install -g @marp-team/marp-cli
fi

# Present the slides
marp slides/presentation.md --theme slides/rose-pine.css --preview