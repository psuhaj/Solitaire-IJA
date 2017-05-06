#!/bin/sh

destination="lib"
file1="cards"

Error() { # function prints out message and terminates this script with appropriate exitcode
	printf "$1"
	exit $2
}

if [ -d "$destination/$file1" ]; then exit 0; fi # cards are already downloaded

wget -q "https://github.com/europ/stuff/raw/master/cards.zip" -P $destination > /dev/null 2>&1  # download cards.zip
if [ $? -ne 0 ]; then Error "ERROR: Could not download \"cards.zip\"!\n" 1 ; fi                 # check download

unzip -q $destination/$file1.zip -d $destination > /dev/null 2>&1             # unzip cards.zip
if [ $? -ne 0 ]; then Error "ERROR: Could not unzip \"cards.zip\"!\n" 1 ; fi  # check unzip

rm -f $destination/$file1.zip # remove cards.zip

exit 0