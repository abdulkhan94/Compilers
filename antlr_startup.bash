path=$(pwd)

export CLASSPATH=".:$path/lib/antlr.jar:$CLASSPATH"
alias antlr4='java -Xmx500M -cp "$path/lib/antlr.jar:$CLASSPATH" org.antlr.v4.Tool'
alias grun='java org.antlr.v4.runtime.misc.TestRig'
