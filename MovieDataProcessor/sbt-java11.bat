@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-11
set PATH=%JAVA_HOME%\bin;%PATH%
set SCRIPT_DIR=%~dp0
"%JAVA_HOME%\bin\java" -Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -Dsbt.ivy.home="%SCRIPT_DIR%\.ivy2" -jar "%SCRIPT_DIR%sbt-launch.jar" %*
