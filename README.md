#Java read and write XML

##DOM
把整个XML文件一次性加载到内存里，形成DOM树，再解析

* 优点：形成树结构，便于编写；树保留在内存中，方便修改
* 缺点：XML文件较大时，内存耗费大，容易内存溢出

##SAX
基于事件的解析，Handler逐个解析结点

* 优点：采用事件驱动，对内存耗费小；适用于只需要处理XML中数据时
* 缺点：不易编码；很难同时访问同一个XML中多处不同数据

