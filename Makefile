.PHONY: doc

make:
	ant compile

run: make
	java -jar dest-client/ija-client.jar

doc:
	ant doc

clean:
	ant clean
