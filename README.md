# pl0-to-jvm
a translator of pl/0 (subset of ibm pl/I) to java bytecode

# how to build
first of all, you'll need to install Apache Ant
after done that, you'll have the following options, detailed in build.xml:

"ant clean": clean
"ant clean all": clean everything compiled, including JARs
"ant compile": compile java to classes
"ant jar": generate jar package after compile

example, in bash:

$ ant clean

clean:
   [delete] Deleting directory /home/felipe/Documentos/pl0-to-jvm/build

BUILD SUCCESSFUL
Total time: 0 seconds
