#
#   This file only have the .sh suffix in order to enable key word coloring in the editor. This file is more specific
#   a .zshrc or .bashrc depending on your terminal...
#
function cmd.grep() {													# function which greps all commands (aliases with comments marked with cmd:) located in this file
	if [ $# -eq 0 ] 												# function
		then													# function
		echo 'ERROR: no command submitted. USAGE: cmd.grep <command>, ex: cmd.grep list'			# function
	else														# function
		less $HOME/.bashrc | grep '# cmd' | grep --color $1							# function
	fi														# function
}															# function
															# function
function filelog() {													# function (git) which will show all commits since on point in time but will not list test files and merge commits
	if [ $# -eq 0 ] 												# function (git)
		then													# function (git)
		git log --name-only --oneline --no-merges | grep -v src/test						# function (git)
	else														# function (git)
		git log --name-only --oneline --no-merges --since=$1 | grep -v src/test					# function (git)
	fi														# function (git)
}															# function (git)
															# function
function grath {													# function grath
	find . | grep $1 | grep /\.git -v | grep /target -v | grep --color $1						# function grath
}															# function grath
															# function
precmd () {														# function which generates git branch name as title on a console window (runned for every command)
		print -Pn "\033]0;$(git rev-parse --abbrev-ref HEAD 2> /dev/null)\007"					# function precmd
}															# function precmd
															# function
function searchJar() {													# function which will search a jar for submitted token
	if [ $# -eq 0 ] 												# function
		then													# function
		echo 'state your search token'										# function
	else														# function
		 find . -name "*.jar" -exec zipgrep "$1" '{}' \; | grep $1						# function
	fi														# function
}															# function
															# function
function wcgrep {													# function wcgrep
	grep -R $1 * | grep -v target | grep -v .git | grep -v "~" | grep -v ".pyc" | grep -v Binary | grep --color -n $1  # function wcgrep
}															# function wcgrep
															# function
# environment variables
export CATALINA_HOME=/Library/Catalina								# var: home folder for tomcat installation
export DEPLOY_LINK=http://localhost:8080/manager/text/deploy					# var: web url for deploy
export JAVA_HOME=$(/usr/libexec/java_home)							# var: home folder for current java installation
export M2_HOME=/Library/maven									# var: home folder for current maven installation
export MAVEN_OPTS="-Xmx1524m"									# var: extra memory for maven
export PATH=$PATH:$M2_HOME/bin									# var: bin folder for current maven installation is made part of path
export WAR_FILE=$WS/hjemme/hjemme-web/target/hjemme.war						# var: file path to hjemme.war
export WS=$HOME/ws										# var: workspace

setopt appendhistory extendedglob allexport automenu promptsubst histsavenodups			# opt: zsh-options...
setopt correctall										# opt: zsh-option som foreslar rettelser av kommandoer som har skriveleif
setopt allexport										# opt: zsh option for a legge til historikk for kommandoer
HISTFILE=~/.histfile										# opt: history file for zsh
HISTSIZE=1000											# opt: size of history file for zsh
SAVEHIST=1000											# opt: the number of commands in the history file for zsh

# key bindings
bindkey "\e[1~" beginning-of-line								# opt: key-binding for zsh
bindkey "\e[4~" end-of-line									# opt: key-binding for zsh
bindkey "\e[5~" beginning-of-history								# opt: key-binding for zsh
bindkey "\e[6~" end-of-history									# opt: key-binding for zsh
bindkey "\e[3~" delete-char									# opt: key-binding for zsh
bindkey "\e[2~" quoted-insert									# opt: key-binding for zsh
bindkey "\e[5C" forward-word									# opt: key-binding for zsh
bindkey "\eOc" emacs-forward-word								# opt: key-binding for zsh
bindkey "\e[5D" backward-word									# opt: key-binding for zsh
bindkey "\eOd" emacs-backward-word								# opt: key-binding for zsh
bindkey "\ee[C" forward-word									# opt: key-binding for zsh
bindkey "\ee[D" backward-word									# opt: key-binding for zsh
bindkey "^H" backward-delete-word								# opt: key-binding for zsh

# completion in the middle of a line
bindkey '^i' expand-or-complete-prefix								# opt: key-binding for zsh

# PROMT text i konsollvindu
PROMPT=$'%B%* %w%b %{\e[32m%}%n@%m (#%h): %{\e[33m%}%3c%{\e[32m%}>%{\e[33m%}%{\e[0m%} '		# opt: promt text i konsollvindu

# Alias, git
alias log="git log --color --graph --pretty=format:'%C(yellow)%h%Creset -%C(magenta bold)%d%Creset %s %Cgreen(%cr) %C(blue bold)<%an>%Creset' --abbrev-commit"				# cmd: logs commits with short hashes and colors ++
alias log.full="git log --color --graph --pretty=format:'%C(yellow)%H%Creset -%C(magenta bold)%d%Creset %s %Cgreen(%cr) %C(blue bold)<%an>%Creset' --abbrev-commit"			# cmd: logs commits with full hashes and colors ++
alias log.date="git log --color --graph --pretty=format:'%C(yellow)%h%Creset -%C(magenta bold)%d%Creset %s %Cgreen(%cD) %C(blue bold)<%an>%Creset' --abbrev-commit"			# cmd: logs commits with short hashes and colors (and real date) ++
alias log.nomerge="git log --color --graph --pretty=format:'%C(yellow)%h%Creset -%C(magenta bold)%d%Creset %s %Cgreen(%cr) %C(blue bold)<%an>%Creset' --abbrev-commit --no-merges"	# cmd: logs commits from with short hashes and colors (no merges) ++
alias branch='git branch'									# cmd: the local feature branches from git repo
alias co='git checkout'										# cmd: checkout branch
alias fetch='git fetch -p'									# cmd: fetch with prune from git server
alias merge='git merge'										# cmd: merge branch
alias pull='git pull --rebase -p'								# cmd: pull and rebase with prune
alias push='git push'										# cmd: push cany commits to origin
alias push.feature='git push -u origin'								# cmd: push new feature branch to origin
alias stash='git stash'										# cmd: call git stash
alias stat='git status'										# cmd: call git status on local git repo

# Navigation:
alias ..='cd .. && la'										# navigation: go to sub folder and show its content
alias apps='cd /Applications && la'								# navigation: go to /Applications and show its content
alias bma='cd $WS/build-matcher && la'								# navigation: go to how to make many asserts with one assertThat and show its content
alias chi='cd $WS/call-hierarchy && la'								# navigation: go to project call-hierarchy and show its content
alias hcl='cd $WS/hjemme/hjemme-client && la'							# navigation: go to project hjemme-client
alias hhb='cd $WS/hjemme/hjemme/hjemme-business && la'						# navigation: go to project hjemme-business and show its content
alias hhf='cd $WS/hjemme/hjemme/hjemme-facade && la'						# navigation: go to project hjemme-facade and show its content
alias hhj='cd $WS/hjemme/hjemme && la'								# navigation: go to project hjemme and show its content
alias hhp='cd $WS/hjemme/hjemme-persistence && la'						# navigation: hjemme-persistence and show its content
alias hhw='cd $WS/hjemme/hjemme-web/ && la'							# navigation: go to project hjemme-web and show its content
alias hhwj='cd $WS/hjemme/hjemme-web-jsf/ && la'						# navigation: go to project hjemme-web-jsf and show its content
alias hje='cd $WS/hjemme && la'									# navigation: go to project hjemme-application and show its content
alias repo='cd $HOME/.m2/repository/nu/hjemme/ && la'						# navigation: maven repo for hjemme and show its content
alias hut='cd $WS/hjemme/utils && la'								# navigation: go to project hjemme/utils and show its content
alias wiki='cd $WS/wiki/ && la'				 					# navigation: wiki sider and show its content
alias ws='cd $WS && la'										# navigation: go to workspace with git repositories and show its content
alias misc='cd $WS/misc && la'									# navigation: go to often used files for development and show its content

# Alias, maven
alias mcc='mvn clean compile'									# cmd: mvn clean compile (maven)
alias mci='mvn clean install'									# cmd: mvn clean install (maven)
alias mcp='mvn clean package'									# cmd: mvn clean package (maven)
alias mcis='mvn clean install -DskipTests'							# cmd: mvn clean install without unit testing (maven)
alias mda='mvn clean dependency:analyze'							# cmd: analasis of dependencies in pom.xml (maven)
alias mda.det='mvn clean dependency:analyze | grep.only.runtime.dependency | grep.warn.err'	# cmd: analasis of dependencies in pom.xml (maven), but will only print warnings and build status
alias mdt='mvn dependency:tree'									# cmd: mvn dependency:tree (maven)
alias mdt.det='mdt | grep.no.down | grep.no.succ | grep.tree'					# cmd: mvn dependency:tree which only prints out the tree (maven)
alias mcs='mvn clean site'									# cmd: mvn clean site (maven)
alias mct='mvn clean test'									# cmd: mvn clean test (maven)
alias mhe='mvn help:effective-pom'								# cmd: show effective pom (maven)
alias mhe.warn='mhe | grep -i -e WARNING -e ERROR'						# cmd: show all warnings when effective pom is generated (maven)
alias mvd='mvn versions:display-property-updates'						# cmd: show all versions eligable for updates (maven)

# Tomcat
alias tom.bin='la $CATALINA_HOME/bin/'								# cmd: show content of Tomcat/bin
alias tom.cp='cp $WAR_FILE $CATALINA_HOME/webapps && echo copying $WAR_FILE to $CATALINA_HOME/webapps'	# cmd: copies war to CATALINA_HOME/webapps
alias tom.restart='tom.stop && tom.start'							# cmd: stops a running apache tomcat and starts it agian
alias tom.start='$CATALINA_HOME/bin/startup.sh'							# cmd: start up apache tomcat
alias tom.stop='$CATALINA_HOME/bin/shutdown.sh'							# cmd: shut down apache tomcat

# Alias, misc:
alias clear="echo '========================= C L E A R =========================' && clear"	# cmd: adds a message for clearing the screen and then clears the screen
alias cmd='less $HOME/.zshrc | grep "# cmd:"'							# cmd: lists all aliases which are commented as a command
alias cmd.git='fun | grep "# function (git)" && cmd | grep git'					# cmd: shows all functions/commands using git
alias conf='less $HOME/.zshrc | grep -e "# opt:" -e "# var:"'					# cmd: lists all aliases which are configuratgion
alias env.grep='env | grep -i'									# cmd: case insensitive greps on environment variables
alias files.hide='defaults write com.apple.finder AppleShowAllFiles NO && killall Finder'	# cmd: skjul "hidden files" fra Finder (Mac Os X)
alias files.show='defaults write com.apple.finder AppleShowAllFiles YES && killall Finder'	# cmd: vis "hidden files" fra Finder (Mac Os X)
alias find.java='find . -name "*.java" | xargs grep --color'					# cmd: searches java files for given text
alias find.txt='find . -name "*.txt" | xargs grep --color'					# cmd: searches txt files for given text
alias find.xml='find . -name "*.xml" | xargs grep --color'					# cmd: searches xml files for given text
alias fun='less $HOME/.zshrc | grep -v fun= | grep "# function"'				# cmd: list all functions (only those commented as one)
alias grep='grep --color'									# cmd: grep words with color
alias grep.around='grep -B 3 -A 3'								# cmd: grep three lines before and three lines after a grep hit
alias grep.nav='grep "# navigation:"'								# cmd: lists all aliases which are commented as a navigation
alias grep.no.down='grep -v \"Downloading'							# cmd: grep all lines not containing "Downloading"
alias grep.no.succ='grep -v "SUCCESS"'								# cmd: grep all lines not containing "SUCCESS"
alias grep.only.runtime.dependency='grep -v :compile | grep -v :test'				# cmd: grep all but runtime dependencies (not test dependencies)
alias grep.tree='grep -e "SNAPSHOT" -e "+" -e "|" -e "-" -e "nu.hjemme"'			# cmd: all strings or characters of interest regarding mvn dependency:tree
alias grep.warn.err='grep -e WARNING -e BUILD -e ERROR'						# cmd: only list lines with WARNING, ERROR, or BUILD
alias la='ls -all -G' 		    	                                   			# cmd: show all content of folder with colors.
alias nav='less $HOME/.zshrc | grep -v grep.nav | grep.nav'					# cmd: lists all aliases which are commented as a navigation
alias nav.to='nav | grep'									# cmd: grep for given navigation
alias rm.dir='rm -r -f'										# cmd: force removal of a folder and its content
alias zsh='source ~/.zshrc && echo "> .zshrc reloaded"'						# cmd: loads .zshrc once more
