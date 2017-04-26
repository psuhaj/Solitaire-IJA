#!/bin/sh

destination="lib"
file1="cards"

wget -q "http://www.stud.fit.vutbr.cz/~xtotha01/$file1.zip" -P $destination

if [ $? -ne 0 ]; then
	printf "ERROR: Could not download \"cards.zip\"!\n"
	exit
fi

unzip -q $destination/$file1.zip -d $destination
rm -f $destination/$file1.zip
