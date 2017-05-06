archiv_name=xsuhaj02
folders=build dest-client dest-server doc examples lib src
files=build.xml readme.txt rozdeleni.txt

.PHONY: make run doc dir lib zip merlin cleandoc cleanlib clean

make:
	@ant compile

run: make
	@java -jar dest-client/ija-client.jar

doc:
	@ant doc

dir:
	@ant dir

lib:
	@ant lib

zip: dir clean
	@mkdir $(archiv_name)
	@ant doc
	@cp -r -t $(archiv_name) $(folders) $(files)
	@zip -q -r $(archiv_name).zip $(archiv_name)
	@rm -rf $(archiv_name)

merlin: clean
	@./merlin.sh

cleandoc:
	@ant clean-doc

cleanlib:
	@ant clean-lib

clean:
	@ant clean
	@rm -f $(archiv_name).zip
