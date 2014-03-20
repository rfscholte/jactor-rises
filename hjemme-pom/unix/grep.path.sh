#!/bin/sh
# Searches recursively after a filename, ignores git and target folders...

find . | grep $1 | grep /\.git -v | grep /target -v | grep --color $1
