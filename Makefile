all: comp run

run: dependencies
	java -Dimport=test.import sth.app.App

dependencies:
	export CLASSPATH=./project/sth-core/sth-core.jar:./project/sth-app/sth-app.jar:./po-uuilib/po-uuilib.jar

comp:
	(cd po-uuilib; make $(MFLAGS) all)
	(cd project; make $(MFLAGS) all)

clean:
	(cd project; make $(MFLAGS) clean)