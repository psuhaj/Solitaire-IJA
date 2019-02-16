#!/bin/sh

package="pack"
server="merlin.fit.vutbr.cz"
serverdir="IJA_MAKEFILE"
rm -f $package.zip
read -r -p "Enter your FIT login: " login
zip -q -r $package.zip . -x *.git*
scp $package.zip $login@$server:$serverdir
ssh $login@$server "cd $serverdir && rm -rf $package && unzip -q $package.zip -d $package && rm -rf $package.zip"
rm -f $package.zip