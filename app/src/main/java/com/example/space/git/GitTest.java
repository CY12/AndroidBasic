package com.example.space.git;

public class GitTest {
    /**
     * 标签含义
     */
    //黄色代表HEAD, 绿色表示的是你本地分支, 紫色是远程分支,
    // 如果你看到一个标志是黄绿蓝, 表示当前HEAD和你远程还有你本地,都有这个分支.黄色只是表示HEAD的位置,没其它含意
    //如果你看到一个提交只有紫色分支,表示你本地没有这个分支.
    // 如果你看到一个是紫色和绿色,表示这个提交是远程分支并且你本地也有这个分支.
    // 如果你看到一个提交只有绿色,表示这只是你本地的分支提交.

    /**
     * merge rebase 区别
     */
//
//    为什么不要再公共分支使用rebase?
//    因为往后放的这些 commit 都是新的,这样其他从这个公共分支拉出去的人，都需要再 rebase,相当于你 rebase 东西进来，就都是新的 commit 了
//
//1-2-3 是现在的分支状态
//            这个时候从原来的master ,checkout出来一个prod分支
//    然后master提交了4.5，prod提交了6.7
//    这个时候master分支状态就是1-2-3-4-5，prod状态变成1-2-3-6-7
//    如果在prod上用rebase master ,prod分支状态就成了1-2-3-4-5-6-7
//    如果是merge
//1-2-3-6-7-8
//        ........ |4-5|
//    会出来一个8，这个8的提交就是把4-5合进来的提交
//    作者：曹九朵_
//    链接：https://www.jianshu.com/p/4079284dd970
//    来源：简书
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


}
