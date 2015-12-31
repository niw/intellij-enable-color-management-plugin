IntelliJ IDEA Enable Color Management Plug-in
=============================================

This is a tiny plug-in to hot-fix a color management issue on [IntelliJ IDEA](https://www.jetbrains.com/idea/) (and all [JetBrains](https://www.jetbrains.com/products.html) IDEs using embedded Java on OS X like AppCode.)

The issue is reported as [IDEA-149601](https://youtrack.jetbrains.com/issue/IDEA-149601), when you're using IntelliJ on OS X, all colors used in the windows are rendered more contrasty.

![Intelli J IDEA 15 on embedded Java 8](https://youtrack.jetbrains.com/_persistent/JDK1.8.png?file=74-279069&v=0&c=true&updated=1450611847470)

This is not happening on Apple's JDK 1.6.

![Intelli J IDEA 15 on Apple's Java](https://youtrack.jetbrains.com/_persistent/JDK1.6.png?file=74-279068&v=0&c=true&updated=1450611847470)

Build
-----

Use Gradle to build this plug-in. Required OS X and Xcode or Xcode command line tools since this plug-in includes a native extension.

    ./gradlew buildPlugin
