# ASM JAVA AGENT

A Guide to Java Bytecode Instrumentation/Manipulation with ASM

## What is it?


## Prerequisites
* Java 8

## How to run?
* To build the agent
```mvn clean install```

* To attach the agent to any java based application (**Demo application is provided in the same repo for testing**)
```java -javaagent:target/java-agent.jar -jar demo-app.jar```

## Output
* In the application logs you can see the agent logs been printed whenever a Socket connect is invoked
```$xslt
...
[AGENT] - java/net/Socket connect function invoked [TIME]: 1579344268196
Thread Id: cluster-ClusterId{value='5e22e18c6925a0538c5e50a9', description='null'}-localhost:27001
Thread Name: 16
Thread Group: java.lang.ThreadGroup[name=main,maxpri=10]
Stack trace of current thread using dumpStack() method
java.lang.Throwable
        at java.net.Socket.connect(Socket.java)
        at com.mongodb.internal.connection.SocketStreamHelper.initialize(SocketStreamHelper.java:64)
        at com.mongodb.internal.connection.SocketStream.initializeSocket(SocketStream.java:79)
        at com.mongodb.internal.connection.SocketStream.open(SocketStream.java:65)
        at com.mongodb.internal.connection.InternalStreamConnection.open(InternalStreamConnection.java:128)
        at com.mongodb.internal.connection.DefaultServerMonitor$ServerMonitorRunnable.run(DefaultServerMonitor.java:117)
        at java.lang.Thread.run(Thread.java:748)
[AGENT] - Finished executing java/net/Socket connect function [TIME]: 1579344268243
...

...
[AGENT] - java/net/Socket connect function invoked [TIME]: 1579344268680
Thread Id: main
Thread Name: 1
Thread Group: java.lang.ThreadGroup[name=main,maxpri=10]
Stack trace of current thread using dumpStack() method
java.lang.Throwable
        at java.net.Socket.connect(Socket.java)
        at sun.security.ssl.SSLSocketImpl.connect(SSLSocketImpl.java:673)
        at sun.security.ssl.BaseSSLSocketImpl.connect(BaseSSLSocketImpl.java:173)
        at sun.net.NetworkClient.doConnect(NetworkClient.java:180)
        at sun.net.www.http.HttpClient.openServer(HttpClient.java:463)
        at sun.net.www.http.HttpClient.openServer(HttpClient.java:558)
        at sun.net.www.protocol.https.HttpsClient.<init>(HttpsClient.java:264)
        at sun.net.www.protocol.https.HttpsClient.New(HttpsClient.java:367)
        ...
        at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:742)
        at org.springframework.web.client.RestTemplate.execute(RestTemplate.java:677)
        at org.springframework.web.client.RestTemplate.getForEntity(RestTemplate.java:345)
        at com.sahajsoft.asmdemoapp.AsmDemoAppApplication.run(AsmDemoAppApplication.java:32)
        ...
        at com.sahajsoft.asmdemoapp.AsmDemoAppApplication.main(AsmDemoAppApplication.java:24)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       ..
        at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:52)
[AGENT] - Finished executing java/net/Socket connect function [TIME]: 1579344268788
...


```
