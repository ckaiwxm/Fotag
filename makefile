default:
	@echo "Compiling..."
	@mkdir -p ./out/src
	@cp -r ./src/lib ./out/src
	javac -sourcepath src src/*.java src/Model/*.java src/View/*.java src/View/*/*.java -d out/src
	jar cmf src/manifest.mf out/Fotag.jar -C out/src .

run: default
	@echo "Running..."
	java -jar out/Fotag.jar

clean:
	-@rm -r out/src/*
	-@rm out/*.jar