#!/bin/sh

destination="lib"
file1="cards"

wget -q "https://github.com/europ/stuff/raw/master/cards.zip" -P $destination

if [ $? -ne 0 ]; then
	printf "ERROR: Could not download \"cards.zip\"!\n"
	exit
fi

unzip -q $destination/$file1.zip -d $destination
rm -f $destination/$file1.zip
