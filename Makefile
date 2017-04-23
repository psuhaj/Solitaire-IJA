archiv_name=xsuhaj02
folders=build dest-client dest-server doc examples lib src
files=build.xml readme.txt rozdeleni.txt

.PHONY: doc

make:
	ant compile

run: make
	java -jar dest-client/ija-client.jar

doc:
	ant doc

dir:
	mkdir -p $(folders)

zip: dir clean
	zip -r $(archiv_name).zip $(folders) $(files)

clean:
	ant clean
	rm -f $(archiv_name).zip