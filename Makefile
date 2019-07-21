jars:
	sbt master/assembly solver/assembly

clean:
	sbt clean
	rm -f deploy/work/*

.PHONY: jars clean
