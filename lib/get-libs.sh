#!/bin/sh

destination="lib"
file1="cards"

Error() {
    printf "$1"
    exit $2
}

if [ -d "$destination/$file1" ]; then
    cards_count=$(ls -l $destination/$file1 | wc -l)
    if [ $cards_count -ne 52 ]; then
        rm -rf $destination/$file1
    else
        exit 0
    fi
fi

wget -q "https://github.com/europ/stuff/raw/master/cards.zip" -P $destination > /dev/null 2>&1
if [ $? -ne 0 ]; then Error "ERROR: Could not download \"cards.zip\"!\n" 1; fi

unzip -q $destination/$file1.zip -d $destination > /dev/null 2>&1
if [ $? -ne 0 ]; then Error "ERROR: Could not unzip \"cards.zip\"!\n" 1; fi

rm -f $destination/$file1.zip

exit 0
