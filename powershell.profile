#Reload the current user profile with ". $profile"

$gitRoot = "c:\z\dev\git\"

#Show the current repo status
function x-gs(){git status}
#Commit with given message
function x-gc($message){git commit -a -m $message}
#Add unmonitored files to the index
function x-ga(){git add .}
#Push all branches to remote
function x-gp(){git push}
#Goto thelocal OGUR repo
function x-cdo(){cd $gitRoot"ogur"}
#Revert all changes and delete non-tracked files
function x-g-clean()
{
	git reset --hard
	git clean --force
}

##OGUR Specific Functions
function x-killo(){taskkill /F /IM OGUR.exe /T}
function x-runo()
{
	$i = 0
	while($i -le 5)
	{
		Start-Process "C:\z\dev\git\ogur\wlr\OGUR\OGUR\bin\x86\Debug\OGUR.exe" -WindowStyle Minimized
		$i++
	}
}


Set-Alias x-np "c:/program files (x86)/Notepad++/notepad++.exe"
