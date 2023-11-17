# EffortLogger-v2
CSE 360 Team Project

The main file for the prototype is the EffortLogger.java
The project requires the Build path to be configured in order for the prototype to run and function.
Under the EffortLogger-v2 project configure Build Path, the Modulepath requires JavaFX and JRE System Library with JaveSE execution environment.
The Classpath requires an External JAR file which is the h2-2.2.224.jar file present in the lib folder in the EffortLogger-v2 project directory.

The project also requires runtime configurations in Eclipse in order to run and be executed. 
In the run configurations menu, under the (x)= Arguments tab, in the VM arguments text box, add the following VM argument:
--module-path "<path-to-javafx-sdk>/lib" --add-modules javafx.controls,javafx.fxml

Replace the <path-to-javafx-sdk> with the path to JavaFX sdk installed on your local system.

And finally, under the VM arguemnts text box, the box with the "Use the -XstartOnFirstThread argument when launching with SWT" needs to be unticked
in order for the project to compile and run.

Following these tasks will make the project be able to run on your local system.

Once running, the application currently authorizes a test user with the following credentials:
username: team38
password: mssci

