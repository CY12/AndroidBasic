package com.example.space.life;

/*在Android开发中，Intent想必大家经常用。Intent本意为目的、意向、意图。在Android中，Intent是系统各组件（或应用程序）之间进行数据传递的数据附载者，Intent不仅可以用于应用程序之间的交互，也可以用于应用程序内部的Activity、Service和Broadcast Receiver之间的交互。 解读Android Intent。本文主要说的是Intent的Flag标志。

        Task
        Task就是一个任务栈，里面用来存放Activity，第一个进去的（Activity）处于栈的最下面，而最后创建的（Activity）则处于栈的最上面。从Task中取出（Activity）是从最顶端取出，也就是说先进后出，后进先出。而Activity在Task中的顺序是可以控制的，在Activity跳转时用到Intent Flag可以设置新建Activity的创建方式。

        FLAG_ACTIVITY_BROUGHT_TO_FRONT
        这个Flag的意思，比如我现在有一个A，然后在A中启动B，并设置FLAG_ACTIVITY_BROUGHT_TO_FRONT这个启动标记，那么B就是以FLAG_ACTIVITY_BROUGHT_TO_FRONT启动的。然后在B中启动C，此时栈就是A,B,C。如果这个时候在C中启动B，那么栈的情况会是A,C,B。

        FLAG_ACTIVITY_REORDER_TO_FRONT
        如果在栈中有A,B,C三个Activity，并且是正常启动的，此时在C中启动B的话，还是会变成A,C,B的。 如果使用了标志 FLAG_ACTIVITY_CLEAR_TOP，那这个FLAG_ACTIVITY_REORDER_TO_FRONT标志会被忽略。

        FLAG_ACTIVITY_NEW_TASK
        假设现在有一个栈1，里面是A,B,C。此时，在C中启动D的时候，设置FLAG_ACTIVITY_NEW_TASK标记，此时会有两种情况：

        1.如果D这个Activity在Manifest.xml中的声明中添加了Task Affinity，系统首先会查找有没有和D的Task Affinity相同的Task栈存在，如果有存在，将D压入那个栈
        2.如果D这个Activity在Manifest.xml中的Task Affinity默认没有设置，则会把其压入栈1，变成：A B C D，这样就和标准模式效果是一样的了。
        也就是说，设置了这个标志后，新启动的Activity并非就一定在新的Task中创建，如果A和B在属于同一个package，而且都是使用默认的Task Affinity，那B还是会在A的task中被创建。 所以，只有A和B的Task Affinity不同时，设置了这个标志才会使B被创建到新的Task。注意如果试图从非Activity的非正常途径启动一个Activity，比如从一个Receiver中启动一个Activity，则Intent必须要添加FLAG_ACTIVITY_NEW_TASK标记。

        FLAG_ACTIVITY_CLEAR_TASK
        如果Intent中设置了这个标志，会导致含有待启动Activity的Task在Activity被启动前清空。也就是说，这个Activity会成为一个新的root，并且所有旧的activity都被finish掉。这个标志只能与FLAG_ACTIVITY_NEW_TASK 一起使用。

        FLAG_ACTIVITY_SINGLE_TOP
        这个FLAG就相当于加载模式中的singleTop，比如说原来栈中情况是A,B,C,D在D中启动D，栈中的情况还是A,B,C,D。

        FLAG_ACTIVITY_CLEAR_TOP
        这个FLAG就相当于加载模式中的SingleTask，如果启动的Activity存在当前的栈中，系统会把要启动的Activity之上的Activity全部弹出栈空间，然后把Intent作为一个新的Intent传给这个Activity。

        例如：原来栈中的情况是A,B,C,D这个时候从D中跳转到B，这个时候栈中的情况就是A,B了。上面例子中运行的B activity既可以在onNewIntent()中接收新的Intent，也可以将自己finish掉然后使用新的Intent重启。如果在它的launch mode中设置了"multiple"（默认），并且intent中没有设置 FLAG_ACTIVITY_SINGLE_TOP 标志，那它就会被finish掉然后重新创建。如果是其它的launchMode或者是设置了FLAG_ACTIVITY_SINGLE_TOP 属性，那就会使用现有的实例的OnNewIntent()方法来接受Intent。

        这种启动模式也可以与 FLAG_ACTIVITY_NEW_TASK 一起使用：如果用来启动一个任务的root activity，它会将这个任务中现在运行的实例调到前台，然后将任务清空至只有根Activity的状态。这很有用，例如要从通知中心里启动一个Activity时。

        FLAG_ACTIVITY_NO_HISTORY
        用这个FLAG启动的Activity，一但退出，就不会存在于栈中。栈中是A,B,C 这个时候再C中以这个FLAG启动D的，D再启动E，这个时候栈中情况为A,B,C,E。简而言之，跳转到的activity不压在栈中。

        FLAG_ACTIVITY_NO_USER_ACTION
        如果设置了这个标志，可以在避免用户离开当前Activity时回调到 onUserLeaveHint()。通常，Activity可以通过这个回调表明有明确的用户行为将当前Activity切出前台。这个回调标记了Activity生命周期中的一个恰当的点，可以用来“在用户看过通知之后”将它们清除，如闪烁LED灯。

        如果Activity是由非用户驱动的事件（如电话呼入或闹钟响铃）启动的，那这个标志就应该被传入Context.startActivity，以确保被打断的Activity不会认为用户已经看过了通知。

        以下内容是根据文档和网上查看的FLAG(目的是为了把FLAG总结在一起)
        FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET
        设置这个标志意味着在activity栈中做一个标记，在Task重置的时候栈就把从标记往上的activity都清除。也就是说，下次这个Task被通过FLAG_ACTIVITY_RESET_TASK_IF_NEEDED调到前台时（通常是由于用户从桌面重新启动），这个activity和它之上的activity都会被finish掉，这样用户就不会再回到他们，而是直接回到在它们之前的activity。

        这在应用切换时非常有用。比如，Email应用会需要查看附件，就要调用查看图片的Activity来显示，那这个查看图片的Activity就会成为Email应用任务里的一部分。但是，如果用户离开了Email的任务，过了一会儿由通过Home来选择Email应用，我们会希望它回到查看邮件会话的页面，而不是浏览图片附件的页面，不然就感觉太诡异了。如果在启动查看图片Activity时设置了这个标志，那这个Activity及由它启动的Activity在下一次用户返回邮件时都会被清除。

        FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
        如果设置这个标志，这个Activity就不会在近期任务中显示。

        FLAG_ACTIVITY_FORWARD_RESULT
        如果Activity A 在启动 Activity B时设置了这个标志，那A的答复目标目标会传递给B，这样一来B就可以通过调用setResult(int) 将返回结果返回给A的答复目标。

        FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY
        这个标志通常情况下不会通过应用的代码来设置，而是在通过最近任务启动activity时由系统设置的。

        FLAG_ACTIVITY_NO_ANIMATION
        禁用掉系统默认的Activity切换动画。

        FLAG_ACTIVITY_TASK_ON_HOME
        这个标志可以将一个新启动的任务置于当前的home任务(home activity task)之上（如果有的话）。也就是说，在任务中按back键总是会回到home界面，而不是回到他们之前看到的activity。这个标志只能与FLAG_ACTIVITY_NEW_TASK标志一起用。

        比如，A->B->C->D，如果在C启动D的时候设置了这个标志，那在D中按Back键则是直接回到桌面，而不是C。

        注意:只有D是在新的task中被创建时（也就是D的launchMode是singleInstance时，或者是给D指定了与C不同的taskAffinity并且加了FLAG_ACTIVITY_NEW_TASK标志时），使用 FLAG_ACTIVITY_TASK_ON_HOME标志才会生效。

        感觉实际使用效果和用 FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK 的效果一样。

        FLAG_FROM_BACKGROUND
        可以给调用者用来标识这个Intent是来自后台操作，而不是用户的交互行为。

        FLAG_RECEIVER_FOREGROUND
        当发送广播的时候设置了这个标志，会允许接收者以前台的优先级运行，有更短的时间间隔。正常广播的接受者是后台优先级，不会被自动提升。

        FLAG_RECEIVER_REPLACE_PENDING
        如果在发送广播时设置了这个标志，那新的广播会替换掉那些已存在的相同广播。相同的定义是通过Intent.filterEquals方法对两个广播的Intent处理返回true。 当匹配到相同的，新的广播和对应的接收器会将待发送的广播列表中已存在的替换掉，在列表中保留同样的位置。这个标志通常被粘性广播(Sticky Broadcast)使用，只保证将最新的广播的值传递给接收器。
*/
public class AndroidIntentFlag {
}
