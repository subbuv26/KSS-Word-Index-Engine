JFLAGS = -g -d
JC = javac
CLASSES = ../classes

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $(CLASSES) $*.java

SOURCES = \
	HomeWindow.java \
	AddWindow.java \
	DeleteWindow.java \
	QueryWindow.java \
	HelpWindow.java \
	Item.java \
	KSSDictionary.java\
	Parse.java

default: classes

classes: $(SOURCES:.java=.class)
clean:
	$(RM) ../classes/*.class
