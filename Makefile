archiv_name=xsuhaj02
folders=build dest-client dest-server doc examples lib src
files=build.xml readme.txt rozdeleni.txt
download_name=cards

.PHONY: doc

make:
	ant compile

run: make
	java -jar dest-client/ija-client.jar

doc:
	ant doc

dir:
	mkdir -p $(folders)

cards:
	cd lib && ./get-libs.sh

zip: dir clean
	mkdir $(archiv_name)
	ant doc
	cp -r -t $(archiv_name) $(folders) $(files)
	zip -q -r $(archiv_name).zip $(archiv_name)
	rm -rf $(archiv_name)

clean:
	ant clean
	rm -f $(archiv_name).zip
	rm -f lib/$(download_name).zip
	rm -rf lib/$(download_name)