#!/bin/sh

destination="lib"
file1="cards"

Error() {
	printf "$1"
	exit $2
}

wget -q "https://github.com/europ/stuff/raw/master/cards.zip" -P $destination > /dev/null 2>&1
if [ $? -ne 0 ]; then Error "ERROR: Could not download \"cards.zip\"!\n" 1 ; fi

unzip -q $destination/$file1.zip -d $destination > /dev/null 2>&1
if [ $? -ne 0 ]; then Error "ERROR: Could not unzip \"cards.zip\"!\n" 1 ; fi

rm -f $destination/$file1.zip

exit 0