#!/bin/sh

#if [ ! -f dest-client/ija-client.jar ]; then
#	ant compile
#	echo
#fi

ant compile
java -jar dest-client/ija-client.jar