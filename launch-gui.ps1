$projectPath = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $projectPath
Start-Process java -ArgumentList "library.LibraryAppGUI" -NoNewWindow
